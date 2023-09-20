package com.amanpreet.notescompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(value: String, setShowDialog : (Boolean) -> Unit, returnValue: (String) -> Unit) {
    var textFieldError = remember{ mutableStateOf("") }
    var textValue = remember { mutableStateOf(value)}
    var showError = remember { mutableStateOf(false) }

    Dialog(onDismissRequest = {setShowDialog(false)}) {
        Surface( shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            Box(contentAlignment = Alignment.Center){
                Column {
                    Text(text = "Add Update Value")
                    OutlinedTextField(
                        value = textValue.value,
                        onValueChange = { textValue.value = it },
                        label = { Text(text = "Add Update Value") },
                        isError = false
                    )
                    Button(onClick = {}) {
                        if(textValue.value.isEmpty()){
                            showError.value = true
                        }else{
                            showError.value = false
                            returnValue(textValue.value)
                            setShowDialog(false)
                        }
                    }
                }
            }

        }
        
    }
}