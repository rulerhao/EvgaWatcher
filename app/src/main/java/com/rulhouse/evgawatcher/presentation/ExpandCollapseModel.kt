package com.rulhouse.evgawatcher.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

data class ExpandCollapseModel (
    val title: String,
    var isOpen: Boolean
)