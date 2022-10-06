package nl.becu.dewi.student656552.articles.presentation.main_screen.components

import android.R.id
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.Category
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import java.security.AccessController.getContext
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleTab(
    article: Article,
    navController: NavController?
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            navController?.navigate(Screen.DetailScreen.withArgs(article.Id))
        }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Icon(
                imageVector = if (article.IsLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.favorite_icon),
                modifier = Modifier
                    .padding(4.dp)
            )
            AsyncImage(
                model = article.Image,
                contentDescription = stringResource(R.string.article_image_content_description),
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