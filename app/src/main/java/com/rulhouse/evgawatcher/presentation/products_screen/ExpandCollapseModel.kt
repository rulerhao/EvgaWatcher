package com.rulhouse.evgawatcher.presentation.products_screen

import androidx.lifecycle.ViewModel
import javax.inject.Inject

data class ExpandCollapseModel (
    val title: String,
    var isOpen: Boolean
)