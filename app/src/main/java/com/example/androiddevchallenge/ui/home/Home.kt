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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.Screen

@Composable
fun Home(puppies: List<Puppy>?, navigateTo: (Screen) -> Unit) {
    PuppySimpleSection(puppies = puppies, navigateTo = navigateTo)
}

@Composable
private fun PuppySimpleSection(puppies: List<Puppy>?, navigateTo: (Screen) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Puppy Finder") }) }) { innerPadding ->
        LazyColumn(
            Modifier.padding(innerPadding),
            content = {
                item { PuppyTopSection() }
                item { PuppyList(puppies = puppies, navigateTo = navigateTo) }
            }
        )
    }
}

@Composable
private fun PuppyList(puppies: List<Puppy>?, navigateTo: (Screen) -> Unit) {
    Column {
        puppies?.forEach {
            PuppyCardSimple(
                dog = it,
                navigateTo = navigateTo
            )
            ListDivider()
        }
    }
}

@Composable
private fun PuppyTopSection() {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        text = "Planning to Adopt a Pet?",
        style = MaterialTheme.typography.subtitle1
    )

    Spacer(modifier = Modifier.height(16.dp))

    Image(
        painter = painterResource(id = R.drawable.rehome_and_adoption), null,
        modifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun ListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}
