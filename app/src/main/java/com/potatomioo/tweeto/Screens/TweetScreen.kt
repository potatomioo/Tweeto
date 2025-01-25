package com.potatomioo.tweeto.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.potatomioo.tweeto.models.tweetsItem
import com.potatomioo.tweeto.viewModel.TweetViewModel
import kotlinx.coroutines.flow.count

@Composable
fun tweetScreen(modifier: Modifier = Modifier) {
    val tweetViewModel : TweetViewModel = viewModel()
    val tweet = tweetViewModel._tweets.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(tweet.value){
            TweetScreenItem(it)
        }
    }

}

@Composable
fun TweetScreenItem(tweet : tweetsItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(7.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                tweet.content,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            Text(
                tweet.category,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }
    }
}