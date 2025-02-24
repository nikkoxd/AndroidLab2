package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentArtwork by remember { mutableIntStateOf(0) }
            val artworks = arrayOf(
                ArtworkData(painterResource(R.drawable.photo_2), "Artwork 1", "Artist 1", "2025"),
                ArtworkData(painterResource(R.drawable.photo_3), "Artwork 2", "Artist 2", "2024"),
            )

            Lab2Theme {
                Scaffold { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(innerPadding).fillMaxSize()
                    ) {
                        Artwork(
                            photo = artworks[currentArtwork].artwork,
                            description = null,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                        ArtworkDescription(
                            title = artworks[currentArtwork].title,
                            artist = artworks[currentArtwork].artist,
                            year = artworks[currentArtwork].year,
                        )
                        DisplayController(
                            displayPrevious = {
                                if (currentArtwork > 0) {
                                    currentArtwork--
                                }
                            },
                            displayNext = {
                                if (currentArtwork < 1) {
                                    currentArtwork++
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        )
                    }
                }
            }
        }
    }
}

class ArtworkData(var artwork: Painter, var title: String, var artist: String, var year: String)

@Composable
fun Artwork(photo: Painter, description: String?, modifier: Modifier = Modifier) {
    Surface(
        shadowElevation = 16.dp,
        color = Color.White,
        modifier = modifier
    ) {
        Image(
            painter = photo,
            contentDescription = description,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ArtworkDescription(title: String, artist: String, year: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
        )
        Row {
            Text(
                text = artist,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.year, year),
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun DisplayController(displayPrevious: () -> Unit, displayNext: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Button(
            onClick = { displayPrevious() },
            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier.width(128.dp)
        ) {
           Text(stringResource(R.string.previous))
        }
        Button(
            onClick = { displayNext() },
            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier.width(128.dp)
        ) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    var currentArtwork = 1
    val artworks: Array<ArtworkData> = arrayOf(
        ArtworkData(painterResource(R.drawable.photo_2), "Artwork 1", "Artist 1", "2025"),
        ArtworkData(painterResource(R.drawable.photo_3), "Artwork 2", "Artist 2", "2024"),
    )

    Lab2Theme {
        Scaffold { innerPadding ->
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(innerPadding).fillMaxSize()
            ) {
                Artwork(
                    photo = artworks[currentArtwork].artwork,
                    description = null,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                ArtworkDescription(
                    title = artworks[currentArtwork].title,
                    artist = artworks[currentArtwork].artist,
                    year = artworks[currentArtwork].year,
                )
                DisplayController(
                    displayPrevious = {
                        if (currentArtwork > 0) {
                            currentArtwork--
                        }
                    },
                    displayNext = {
                        if (currentArtwork < 1) {
                            currentArtwork++
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
            }
        }
    }
}