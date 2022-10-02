package nl.becu.dewi.student656552.articles.presentation.main_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.presentation.util.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleTab(
    article: Article,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { navController.navigate(Screen.DetailScreen.withArgs(article.Id)) }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = article.Image,
                contentDescription = "Image of news item",
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp),
                placeholder = painterResource(R.drawable.default_image_thumbnail)
            )

            Text(
                text = article.Title,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }


}