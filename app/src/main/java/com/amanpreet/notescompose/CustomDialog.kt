package com.amanpreet.notescompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.amanpreet.notescompose.ui.theme.NotesComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    value: String,
    position: Int, setShowDialog: (Boolean) -> Unit,
    returnValue: (String, Int) -> Unit
) {
    var isError = rememberSaveable { mutableStateOf(false) }
    var textValue = remember { mutableStateOf(value) }
    var showError = remember { mutableStateOf(false) }
    var titleText = "Add Value"
    if (position > -1) {
        titleText = "Update Value"
    }

    fun validate(text: String) {
        isError.value = text.isEmpty()
    }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = titleText,
                    )
                    OutlinedTextField(
                        value = textValue.value,
                        onValueChange = {
                            textValue.value = it
                            validate(textValue.value)
                        },
                        label = { Text(text = titleText) },
                        isError = isError.value,
                        supportingText = {
                            if (isError.value) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Enter text",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        trailingIcon = {
                            if (isError.value)
                                Icon(
                                    Icons.Rounded.Info,
                                    "error",
                                    tint = MaterialTheme.colorScheme.error
                                )
                        },
                        keyboardActions = KeyboardActions { validate(textValue.value) },
                    )
                    Button(
                        modifier = Modifier
                            .padding(8.dp),
                        onClick = {
                            if (textValue.value.isEmpty()) {
                                validate(textValue.value)
                            } else {
                                validate(textValue.value)
                                returnValue(textValue.value, position)
                                setShowDialog(false)
                            }
                        }) {
                        Text(text = titleText)
                    }
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    NotesComposeTheme {
        CustomDialog(position = -1, value = "", setShowDialog = {}) { _, _ -> }
    }
}