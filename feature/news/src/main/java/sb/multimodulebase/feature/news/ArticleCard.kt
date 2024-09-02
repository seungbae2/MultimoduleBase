package sb.multimodulebase.feature.news

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sb.multimodulebase.core.designsystem.component.DynamicAsyncImage
import com.sb.multimodulebase.core.model.Article
import com.sb.multimodulebase.core.model.Source
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import sb.multimodulebase.feature.news.util.formatToDateTimeString

@Composable
fun ArticleCard(
    article: Article,
    onClickArticle: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable {
                onClickArticle()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            // Source name
            Text(
                text = article.source.name ?: "",
                style = MaterialTheme.typography.titleSmall,
            )

            // Title
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            article.urlToImage?.let { imageUrl ->
                DynamicAsyncImage(
                    imageUrl = imageUrl
                )
                Spacer(modifier = Modifier.height(8.dp))
            } ?: run {
                Text(
                    text = article.content ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(
                text = article.publishedAt?.formatToDateTimeString() ?: "",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreviewWithImage() {
    val article = Article(
        author = "John Doe",
        content = "This is a sample article content with image.",
        description = "A sample article description.",
        publishedAt = Clock.System.now(),
        source = Source(id = "1", name = "Sample News"),
        title = "Sample Article with Image",
        url = "https://example.com",
        urlToImage = "https://via.placeholder.com/150"
    )
    ArticleCard(article = article, onClickArticle = {})
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreviewWithoutImage() {
    val article = Article(
        author = "Jane Doe",
        content = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
        description = "Another sample article description.",
        publishedAt = Clock.System.now(),
        source = Source(id = "2", name = "Another News Source"),
        title = "Sample Article without Image",
        url = "https://example.com",
        urlToImage = null // 이미지가 없는 경우
    )
    ArticleCard(article = article, onClickArticle = {})
}