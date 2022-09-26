package nl.becu.dewi.student656552.presentation.util

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString{
            append(route)
            args.forEach {
                arg -> append("/$arg")
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
