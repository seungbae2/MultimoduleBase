package sb.multimodulebase.feature.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sb.multimodulebase.core.designsystem.R
import com.sb.multimodulebase.core.designsystem.component.DynamicAsyncImage
import com.sb.multimodulebase.core.model.Article
import sb.multimodulebase.feature.news.util.formatToDateTimeString

@Composable
fun ArticleCard(
    article: Article
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DynamicAsyncImage(
                imageUrl = article.urlToImage?: "",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = article.publishedAt?.formatToDateTimeString() ?: "",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}