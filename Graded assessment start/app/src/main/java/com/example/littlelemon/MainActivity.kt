package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
                var orderMenuItems by remember { mutableStateOf(false) }
                var menuItems by remember { mutableStateOf(databaseMenuItems) }
                var searchPhrase by remember { mutableStateOf(TextFieldValue("")) }


                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.padding(50.dp)
                    )

                    Button(onClick = {
                        orderMenuItems = true
                    }) {
                        Text("Tap to Order By Name")
                    }

                    OutlinedTextField(
                        value = searchPhrase,
                        onValueChange = { searchPhrase = it },
                        label = { Text("Search") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    )
                    LaunchedEffect(databaseMenuItems) {
                        menuItems = databaseMenuItems
                    }
                    LaunchedEffect(searchPhrase) {
                        menuItems = databaseMenuItems.filter {
                            it.title.contains(searchPhrase.text, ignoreCase = true)
                        }
                    }

                    MenuItemsList(
                        items = if (orderMenuItems) menuItems.sortedBy { it.title }
                        else menuItems
                    )
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItemsNetwork = fetchMenu()
                saveMenuToDatabase(menuItemsNetwork)
            }
        }
    }



    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val url ="https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json"
        return try {
            val jsonString = httpClient.get(url).body<String>()
            val menuNetwork = Json.decodeFromString<MenuNetwork>(jsonString)
            menuNetwork.menu
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(menuItem.title)
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Right,
                        text = "%.2f".format(menuItem.price)
                    )
                }
            }
        )
    }
}
