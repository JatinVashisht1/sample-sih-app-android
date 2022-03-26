package com.example.demosihappandroidpart.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.AppRegistration
import androidx.compose.material.icons.outlined.People
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String = "", val icon: ImageVector?, val iconSelected: ImageVector? = null){
    object RegisterScreen: Screen(route = "registerscreen", label = "Register", icon = Icons.Outlined.AppRegistration, iconSelected = Icons.Filled.AppRegistration)
    object AllWorkersScreen: Screen(route = "allworkers", label = "All Workers", icon = Icons.Outlined.People, iconSelected = Icons.Filled.People)
}
