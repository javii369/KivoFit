package com.KivoFit.navigation

sealed class Route(val route: String) {
    data object Login : Route("login")
    data object Register : Route("register")
    data object RecoverPassword : Route("recover_password")
    data object Inicio : Route("inicio")
    data object History : Route("history")
    data object Calendar : Route("calendar")
    data object Chat : Route("chat")
    data object Notifications : Route("notifications")
    data object Profile : Route("profile")
    data object PlanForm : Route("plan_form")
    data object Routine : Route("routine")
}
