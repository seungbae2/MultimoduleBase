package com.sb.multimodulebase.feature.disney

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sb.multimodulebase.core.designsystem.component.DynamicAsyncImage
import com.sb.multimodulebase.core.model.DisneyCharacter
import kotlinx.datetime.Instant
import com.sb.multimodulebase.core.designsystem.R as designR

@Composable
fun CharacterCard(
    character: DisneyCharacter
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
                imageUrl = character.imageUrl,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CharacterCardPreview() {

    val character = DisneyCharacter(
         _id = 308,
         films = listOf("Tangled","Tangled: Before Ever After"),
         shortFilms = listOf("Tangled Ever After","Hare Peace"),
         tvShows = listOf("Once Upon a Time","Tangled: The Series"),
         videoGames = listOf("Disney Princess Enchanting Storybooks","Hidden Worlds","Disney Crossy Road","Kingdom Hearts III"),
         parkAttractions = listOf("Celebrate the Magic","Jingle Bell, Jingle BAM!"),
         allies = listOf(),
         enemies = listOf(),
         sourceUrl = "https://disney.fandom.com/wiki/Queen_Arianna",
         name = "Queen Arianna",
         imageUrl = "https://static.wikia.nocookie.net/disney/images/1/15/Arianna_Tangled.jpg/revision/latest?cb=20160715191802",
         createdAt = Instant.parse("2021-04-12T01:33:34.458Z"),
         updatedAt = Instant.parse("2021-04-12T01:33:34.458Z"),
         url = "https://api.disneyapi.dev/characters/308",
         __v = 0,
    )

    CharacterCard(character)
}


