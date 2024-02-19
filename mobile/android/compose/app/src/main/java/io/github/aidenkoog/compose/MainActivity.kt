package io.github.aidenkoog.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aidenkoog.compose.ui.theme.ComposeTheme

/**
 * ui compose elements == composable function == composable.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Greeting("Android")
                        Row {
                            GreetingTest(name = "Tester")
                            Text(text = "Tester2")
                        }
                        AddTexts()
                        CustomColumnText()
                        TextViewWithBtn()
                        ButtonWithRemember()
                    }
                }
            }
        }
    }
}

@Composable
private fun ButtonWithRemember() {
    val expanded = remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(30.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Hello")
                Text("Tester 2")
            }
            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show" else "Hidden")
            }
        }
    }
}

@Composable
private fun TextViewWithBtn() {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello")
                Text(text = "Tester 1")
            }
            ElevatedButton(onClick = {}) {
                Text("CLICK")
            }
        }
    }
}

@Composable
private fun CustomColumnText() {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(text = "TEST 111111")
            Text(text = "TEST 222222")
        }
    }
}

@Composable
private fun AddTexts() {
    val names = listOf("Test1", "Test2")
    for (name in names) {
        Text(text = name)
    }
}

/**
 * Greeting Test function generates ui hierarchy which shows name string.
 * Text is provided from library.
 */
@Composable
private fun GreetingTest(name: String) {
    Surface(color = MaterialTheme.colorScheme.primary) {
        Text(text = "Hello $name Test !")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        Greeting("Android")
    }
}