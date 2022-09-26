package nl.becu.dewi.student656552

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.becu.dewi.student656552.presentation.util.Navigation
import nl.becu.dewi.student656552.presentation.detail_screen.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()

            /*
            Student656552Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldPractice()
                }
            }
            */
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DetailScreen(articleId = 134067, navController = null)


    //Student656552Theme { }
}
