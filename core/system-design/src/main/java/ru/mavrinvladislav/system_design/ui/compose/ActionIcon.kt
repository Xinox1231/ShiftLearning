package ru.mavrinvladislav.system_design.ui.compose
data class ActionIcon(
    val iconResId: Int,
    val contentDescription: String? = null,
    val onClick: () -> Unit
)
