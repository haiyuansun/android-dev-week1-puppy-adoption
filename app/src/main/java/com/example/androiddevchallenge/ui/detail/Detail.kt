/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.getPuppy
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.ThemedPreview

private val defaultSpacerSize = 16.dp

@Composable
fun Detail(puppyId: String, onBack: () -> Unit, isFavorite: Boolean, onToggleFavorite: () -> Unit) {
    val puppy = getPuppy(puppyId = puppyId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rescued by: Red Fox Service Center",
                        style = MaterialTheme.typography.subtitle2,
                        color = LocalContentColor.current
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            DetailContent(puppy, modifier)
        },
        bottomBar = {
            BottomBar(
                puppy = puppy,
                onUnimplementedAction = { },
                isFavorite = isFavorite,
                onToggleFavorite = onToggleFavorite
            )
        }
    )
}

@Composable
private fun DetailContent(puppy: Puppy, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.height(defaultSpacerSize))
            DetailHeaderImage()
            Spacer(Modifier.height(defaultSpacerSize))
        }

        item {
            content(puppy, modifier = modifier)
        }
    }
}

@Composable
private fun content(puppy: Puppy, modifier: Modifier) {
    Column(modifier = Modifier.padding(14.dp, 0.dp)) {
        Text(modifier = Modifier.fillMaxWidth(), text = "${puppy.name}", style = MaterialTheme.typography.h5, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(defaultSpacerSize))
        Divider(
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
        Spacer(modifier = Modifier.height(defaultSpacerSize))

        Text(modifier = Modifier.fillMaxWidth(), text = "${puppy.location}   |   ${puppy.sex}", style = MaterialTheme.typography.subtitle1, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(defaultSpacerSize))

        Divider(
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
        Spacer(modifier = Modifier.height(defaultSpacerSize))

        Text(modifier = Modifier.fillMaxWidth(), text = "About", style = MaterialTheme.typography.subtitle1, color = Color.Gray)
        Spacer(modifier = Modifier.height(10.dp))
        Text(modifier = Modifier.fillMaxWidth(), text = "${puppy.description}", style = MaterialTheme.typography.body1)
    }
}

@Composable
private fun DetailHeaderImage() {
    val imageModifier = Modifier
        .heightIn(min = 180.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.medium)
    Image(
        painter = painterResource(R.drawable.puppy_detail),
        null, imageModifier, contentScale = ContentScale.Crop
    )
}

@Composable
private fun DetailPuppyImage(modifier: Modifier, puppy: Puppy) {
    val imageModifier = Modifier
        .size(60.dp, 60.dp)
        .clip(shape = MaterialTheme.shapes.small)
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        puppy.imageList.forEach {
            DetailImage(it, modifier = imageModifier)
        }
    }
}

@Composable
private fun DetailImage(res: Int, modifier: Modifier) {
    Image(painter = painterResource(res), contentDescription = null, modifier = modifier, contentScale = ContentScale.Crop)
}

@Composable
private fun BottomBar(
    puppy: Puppy,
    onUnimplementedAction: () -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    Surface(elevation = 2.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = onUnimplementedAction) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "favorite"
                )
            }

            IconButton(onClick = { onUnimplementedAction }) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "ask"
                )
            }

            IconButton(onClick = { onUnimplementedAction }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "share"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetail() {
    ThemedPreview {
        Detail(
            puppyId = "1",
            onBack = { },
            isFavorite = false,
            onToggleFavorite = { /*TODO*/ }
        )
    }
}
