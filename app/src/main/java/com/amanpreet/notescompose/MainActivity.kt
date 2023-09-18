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
    val presses: MutableState<Int> = remember { mutableStateOf(1) }

    val list = remember {
        mutableStateListOf<String>("item1", "item2", "item3")

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Array List CRUD")
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                presses.value +=1
                list.add("Testing ${presses.value}")
                println("Clicked ${list.size}")

            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for(items in 0..list.size-1){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f),
                        text = "${list[items]}"
                    )
                    Icon(Icons.Filled.Clear , contentDescription = "Check mark", modifier = Modifier.clickable {
                        list.removeAt(items)
                    })

//                    Icon(
//                        asset = Icons.Filled.Search,
//                        modifier = Modifier.clickable { // Todo -> handle click },
//                            )
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