package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val menuItemsLiveData = MutableLiveData<List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val menuItems = getMenu("Salads")

            runOnUiThread {
                menuItemsLiveData.value = menuItems
            }
        }

        setContent {
            LittleLemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val items = menuItemsLiveData.observeAsState(emptyList())
                        MenuItems(items.value)
                    }
                }
            }
        }
    }

    private suspend fun getMenu(category: String): List<String> {
        // TODO:
        val urlString = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json"

        try {
            val menuCategory = fetchDataFromUrl(urlString)
            //println("Menu Category: ${menuCategory.menu}")
            Log.d("menucatagary", menuCategory.toString())

            // Access individual menu items
            for (menuItem in menuCategory.menu) {
                //println("Name: ${menuItem.name}, Price: ${menuItem.price}")
                Log.d("divua", menuItem)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            }
        val response: Map<String, MenuCategory> =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json")
               .body()
        Log.d("divu", response.toString())
        return response[category]?.menu ?: listOf()
    }
}

@Composable
fun MenuItems(
    items: List<String> = emptyList(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        LazyColumn {
            itemsIndexed(items) { _, item ->
                MenuItemDetails(item)
            }
        }
    }
}

@Composable
fun MenuItemDetails(menuItem: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = menuItem)
    }
}

fun fetchDataFromUrl(urlString: String): MenuCategory {
    val url = URL(urlString)
    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

    return try {
        // Check if the request was successful (status code 200)
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuilder()
            var line: String?

            // Read the response line by line
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }

            // Parse JSON using Gson
            val gson = Gson()
            gson.fromJson(response.toString(), MenuCategory::class.java)
        } else {
            throw RuntimeException("Failed to retrieve data. HTTP Status Code: ${connection.responseCode}")
        }
    } finally {
        connection.disconnect()
    }
}