package nl.becu.dewi.student656552.articles.presentation.util

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen("detail_screen")
    object LoginScreen: Screen("login_screen")
    object LogoutScreen: Screen("logout_screen")
    object RegisterScreen: Screen("register_screen")
    object FavScreen: Screen("fav_screen")

    fun withOptionalAuthArgs(vararg args: String?): String {
        return buildString{
            append(route)
            args.forEach {
                    arg -> if (arg != null) append("?authToken=$arg")
            }
        }
    }

    fun withArgs(vararg args: String): String {
        return buildString{
            append(route)
            args.forEach {
                arg ->  append("/$arg")
            }
        }
    }

    fun withArgs(vararg args: Int): String {
        return buildString{
            append(route)
            args.forEach {
                    arg -> append("/${arg.toString()}")
            }
        }
    }

}
