package com.rulhouse.evgawatcher.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

data class ExpandCollapseModel (
    val id: Int,
    val title: String,
    val needsPlusButton: Boolean,
    var isOpen: Boolean
)