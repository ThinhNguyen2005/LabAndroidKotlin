package com.example.lab1.labthuyettrinh.tabNavigation4_4

import kotlinx.serialization.Serializable

@Serializable
sealed class TabDestination(val route: String, val label: String) {
    @Serializable
    data object Notication : TabDestination("notication", "Notication")
    @Serializable
    data object CS1 : TabDestination("cs1", "CS1")
    @Serializable
    data object CS2 : TabDestination("cs2", "CS2")
    @Serializable
    data object CS3 : TabDestination("cs3","CS3")

}
