package com.potatomioo.tweeto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.potatomioo.tweeto.api.TweetoApi
import com.potatomioo.tweeto.ui.theme.TweetoTheme
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.multibindings.IntKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetoapi: TweetoApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            var response = tweetoapi.getCateories()
            Log.d("hey", "onCreate: ${response.body()?.distinct().toString()}")

            var sportTweets = tweetoapi.getTweets("tweets[?(@.category == 'sport')]")
            var content = sportTweets.body()?.toString()
            Log.d("tweets","$content")
        }
        enableEdgeToEdge()
        setContent {
            TweetoTheme {
                Text("Testing")
            }
        }
    }
}
