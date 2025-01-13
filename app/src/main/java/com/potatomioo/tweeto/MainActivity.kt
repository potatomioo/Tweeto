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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tweetoApi : TweetoApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            var response = tweetoApi.getCategories()
            Log.d("All category",response.body().toString())
            var result = tweetoApi.getCategories()
            Log.d("distinct",result.body()?.distinct().toString())
        }
        enableEdgeToEdge()
        setContent {
            TweetoTheme {

            }
        }
    }
}
