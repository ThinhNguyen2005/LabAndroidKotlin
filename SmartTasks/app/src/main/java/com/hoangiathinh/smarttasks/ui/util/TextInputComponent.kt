package com.hoangiathinh.smarttasks.ui.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
@Composable
fun TextInputComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: MutableState<Boolean> = mutableStateOf(false)
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(placeholder) },
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (!isPasswordVisible.value && isPasswordToggleDisplayed) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (isPasswordToggleDisplayed) {
                val visibilityIcon = if (isPasswordVisible.value)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                val description =
                    if (isPasswordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = {
                    isPasswordVisible.value = !isPasswordVisible.value
                }) {
                    Icon(
                        imageVector = visibilityIcon,
                        contentDescription = description,
                        tint = Color(0xFFa1a5a9)
                    )
                }
            }
        },


        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFFd6d6d7),
            unfocusedIndicatorColor = Color(0xFFd6d6d7),
            focusedContainerColor = Color(0xFFf9f8fd),
            unfocusedContainerColor = Color(0xFFf9f8fd),
            focusedTextColor = Color(0xFF646262),
            unfocusedTextColor = Color(0xFF646262),
            unfocusedLeadingIconColor = Color(0xFFa1a5a9),
            focusedLeadingIconColor = Color(0xFFa1a5a9),

        ),
        shape = MaterialTheme.shapes.medium

    )
}