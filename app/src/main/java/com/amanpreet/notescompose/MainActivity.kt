package com.amanpreet.notescompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amanpreet.notescompose.ui.theme.NotesComposeTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    var list = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //   Greeting("Android")
                    ScaffoldExample()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /* Text(
         text = "Hello $name!",
         modifier = modifier
     )*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var text: MutableState<String> = remember { mutableStateOf("") }
    val list = remember { mutableStateListOf<String>("Row 1", "Row 2", "Row 3") }
    val showDialog = remember { mutableStateOf(false) }
    val position = remember { mutableStateOf(-1) }

    if (showDialog.value) {
        CustomDialog(value = text.value, position = position.value, setShowDialog = {
            showDialog.value = it
        }, returnValue = { returnText, index ->
            if (index > -1) {
                list[index] = returnText
            } else {
                list.add(returnText)
            }
            position.value = -1
            text.value = ""
        })
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(color = Color.White ,text = "App Bar")
                },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)

            )

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                position.value = -1
                showDialog.value = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for (index in 0 until list.size) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                            .clickable {
                                text.value = list[index]
                                position.value = index
                                showDialog.value = true
                            },
                        text = "${list[index]}",
                        style = TextStyle(fontSize = 30.sp)
                    )
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Check mark",
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 8.dp)
                            .clickable {
                            list.removeAt(index)
                        })

                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesComposeTheme {
//        Greeting("Android")
        ScaffoldExample()
    }
}