package com.example.demosihappandroidpart.presentation.components

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.demosihappandroidpart.core.Screen

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    items: List<Screen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Log.d("HomeScreen", "Destination is  ${currentDestination?.route.toString()}")
    if (currentDestination != null) {
            BottomNavigation(
                backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color(
                    0xFF7C25FA
                )
            ) {
                items.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        icon = {
                            if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                Icon(
                                    imageVector = screen.iconSelected!!,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            } else {
                                Icon(
                                    imageVector = screen.icon!!,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }

                        },
                        label = { Text(text = screen.label, color = Color.White) },
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        alwaysShowLabel = false,
                    )
                }
            }
    }
}