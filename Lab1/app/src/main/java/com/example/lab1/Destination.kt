package com.example.lab1
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination(val route: String, val label: String) {
    @Serializable
    data object Home : Destination("home", "Home")
    @Serializable
    data object Shopping : Destination("shopping", "Shopping")
    @Serializable
    data object Create : Destination("create", "Create")
    @Serializable
    data object Notification : Destination("notification", "Notification")
    @Serializable
    data object Account : Destination("account", "Account")
    @Serializable
    data object Calendar : Destination("calendar", "Calendar")
    @Serializable
    data object MySport : Destination("my_sport", "My Sport")
    @Serializable
    data object Profile : Destination("profile", "Profile")
    @Serializable
    data object Football : Destination("football", "Football")
    @Serializable
    data object Hockey : Destination("hockey", "Hockey")
    @Serializable
    data object Baseball : Destination("baseball", "Baseball")
    @Serializable
    data object Music : Destination("music", "Music")
}
