package com.potatomioo.tweeto.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.potatomioo.tweeto.models.tweetsItem
import com.potatomioo.tweeto.repository.repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(
    private val repository: repository,
    val savedStateHandle: SavedStateHandle
) : ViewModel(){

    val _tweets : StateFlow<List<tweetsItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "sport"
            repository.getTweets(category)
        }
    }
}