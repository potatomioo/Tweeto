package com.potatomioo.tweeto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.potatomioo.tweeto.Screens.categoryScreen
import com.potatomioo.tweeto.Screens.tweetScreen
import com.potatomioo.tweeto.api.TweetoApi
import com.potatomioo.tweeto.ui.theme.TweetoTheme
import com.potatomioo.tweeto.viewModel.CategoryViewModel
import com.potatomioo.tweeto.viewModel.TweetViewModel
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.multibindings.IntKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun names(){
    val categoryViewModel : CategoryViewModel = viewModel()
    val tweets = categoryViewModel._categories.collectAsState()
    //It was providing a flow so used collectAsState to get a state return.

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(tweets.value.distinct()){ item ->
            Text(item)
        }
    }
}

@Composable
fun tweety() {
    val tweetViewModel : TweetViewModel = viewModel()
    val tweets = tweetViewModel._tweets.collectAsState()
    //It was providing a flow so used collectAsState to get a state return.

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(tweets.value){ item ->
            Text(item.content)
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "CategoryScreen"){
        composable("CategoryScreen"){
            categoryScreen{
                navController.navigate("TweetScreen/$it")
            }
        }
        composable("TweetScreen/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            )
            ){
            tweetScreen()
        }
    }
}