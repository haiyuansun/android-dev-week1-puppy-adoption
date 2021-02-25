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
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
val imgList = arrayListOf(R.drawable.gema_01, R.drawable.gema_02, R.drawable.gema_01)

val puppy1 = Puppy(
    "15",
    "Tom",
    "Male",
    "Labrador Retrieverr",
    "3 months",
    "Cheerful Gentle Friendly Intelligent",
    "Beverly Hills, CA",
    R.drawable.puppy_labrador,
    imgList
)

val puppy2 = Puppy(
    "2",
    "Angel",
    "Female",
    "German Shepherd",
    "3.5 months",
    "Courageous Intelligent Loyal Watchful",
    "Boston, MA",
    R.drawable.puppy_german_shepherd,
    imgList
)

val puppy3 = Puppy(
    "3",
    "Smith",
    "Male",
    "Canary Dog",
    "3 years",
    "Good with dogs: Yes, with proper intros\n" +
        "Good with cats: Unknown\n" +
        "Good with kids: Unknown, likely older kids \n" +
        "Crate trained: Yes\n" +
        "House trained: Yes",
    "71 Commercial St, #184 Boston, MA 02113",
    R.drawable.puppy_canary_dog,
    imgList
)

val puppy4 = puppy1.copy()

val puppy5 = puppy2.copy()

val puppy6 = puppy3.copy()

val sample = arrayListOf(puppy1, puppy2, puppy3, puppy4, puppy5, puppy6)

fun getPuppy(puppyId: String) = sample.find { it.id == puppyId } ?: puppy1
