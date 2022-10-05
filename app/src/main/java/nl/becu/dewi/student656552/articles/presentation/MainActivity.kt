package nl.becu.dewi.student656552.articles.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import nl.becu.dewi.student656552.articles.presentation.detail_screen.DetailScreen
import nl.becu.dewi.student656552.articles.presentation.fav_screen.FavScreen
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginScreen
import nl.becu.dewi.student656552.articles.presentation.logout_screen.LogoutScreen
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainScreen
import nl.becu.dewi.student656552.articles.presentation.register_screen.RegisterScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //TODO cleanup share preferenced residue
    //TODO change api call of getting all liked articles

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedPreferencesManager.init(this)

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
                composable(
                    route = Screen.MainScreen.route) {
                    MainScreen(navController = navController) }

                composable(
                    route = Screen.DetailScreen.route + "/{articleId}",
                    arguments = listOf(
                        navArgument("articleId") {
                            type = NavType.IntType
                            nullable = false
                        }
                    )
                ) { entry ->
                    DetailScreen(articleId = entry.arguments?.getInt("articleId"), navController = navController)
                }
                
                composable(
                    route = Screen.LoginScreen.route
                ) { LoginScreen(navController = navController)
                }

                composable(
                    route = Screen.LogoutScreen.route
                ) { LogoutScreen(navController = navController)
                }

                composable(
                    route = Screen.RegisterScreen.route
                ) { RegisterScreen(navController = navController)
                }

                composable(
                    route = Screen.FavScreen.route
                ) { FavScreen(navController = navController)
                }
            }
        }
    }

    /*
    @Preview(showBackground = true)
    @Composable
    fun trying(){
        ArticleTab(article = Article(
            134067,
            1,
            "Politie vindt groot crystal meth-lab, drie personen aangehouden",
            "Leden van de Dienst Speciale Interventie (DSI) hebben vrijdagavond een groot drugslab met crystal meth (methamfetamine) aangetroffen in Achter-Drempt in Gelderland, meldt de <a href=\"https://www.politie.nl/nieuws/2020/mei/10/11-drie-personen-aangehouden-bij-vondst-groot-drugslab-in-achter-drempt.html\" target=\"_blank\">politie</a>. Drie personen zijn aangehouden.",
            PublishDate = Date("1"),
            Image = "https://media.nu.nl/m/w64x40kai08w_sqr256.jpg/politie-vindt-groot-crystal-meth-lab-drie-personen-aangehouden.jpg",
            Url = "https://www.nu.nl/algemeen/6050327/politie-vindt-groot-crystal-meth-lab-drie-personen-aangehouden.html",
            Related = listOf(
                "https://nu.nl/binnenland/6049390/recordbedrag-van-125-miljoen-euro-contant-geld-ontdekt-in-huis-eindhoven.html",
                "https://nu.nl/coronavirus/6047602/politiechef-drugshandel-verplaatst-zich-in-coronatijd-naar-parkeerplaatsen.html",
                "https://nu.nl/tech/6040682/europol-criminelen-maken-slim-gebruik-van-coronacrisis.html"
            ),
            Categories = listOf(
                Category(4266,  "Algemeen"),
                Category(4267,  "Binnenland")
            ),
            IsLiked = true
        ), navController = null)
    }

     */
}