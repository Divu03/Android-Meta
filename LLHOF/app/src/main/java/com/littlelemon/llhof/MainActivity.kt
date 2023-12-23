package com.littlelemon.llhof

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.littlelemon.llhof.ui.theme.LLHOFTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleUI {timesClicked ->
                Toast.makeText(this,"Clicked $timesClicked",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SimpleUI(onClick: (Int) -> Unit) {
    var timesClicked = 1
    Button(onClick = { onClick(timesClicked++) }) {
        Text(text = "Button")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LLHOFTheme {

    }
}