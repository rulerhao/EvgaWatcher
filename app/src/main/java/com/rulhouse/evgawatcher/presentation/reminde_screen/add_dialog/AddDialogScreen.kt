package com.rulhouse.evgawatcher.presentation.reminde_screen.add_dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.presentation.reminde_screen.add_dialog.composable.RadioButtonsArea

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddDialogScreen(

) {
    val context = LocalContext.current
    Column {
        RadioButtonsArea()

    }
}