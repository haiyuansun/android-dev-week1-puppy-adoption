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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.puppy1
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.ThemedPreview

@Composable
fun PuppyCardSimple(dog: Puppy, navigateTo: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { navigateTo(Screen.Detail(puppyId = dog.id)) })
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        PuppyAvatar(puppy = dog, Modifier.padding(16.dp))
        Column {
            PuppyTitle(puppy = dog)
            PuppyDescText(puppy = dog)
        }
    }
}

@Composable
fun PuppyAvatar(puppy: Puppy, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(puppy.imageThumbId),
        contentDescription = null, // decorative
        modifier = modifier
            .size(60.dp, 60.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun PuppyTitle(puppy: Puppy) {
    Text(puppy.name, style = MaterialTheme.typography.subtitle1)
}

@Composable
fun PuppyDescText(puppy: Puppy) {
    Text(text = "Breed: ${puppy.breed}", style = MaterialTheme.typography.subtitle2)
    Text(text = "Sex: ${puppy.sex}", style = MaterialTheme.typography.subtitle2)
    Text(text = "Age: ${puppy.age}", style = MaterialTheme.typography.subtitle2)
}

@Preview("Simple puppy card ")
@Composable
fun SimpleCardPreview() {
    ThemedPreview {
        PuppyCardSimple(dog = puppy1, navigateTo = { /*TODO*/ })
    }
}
