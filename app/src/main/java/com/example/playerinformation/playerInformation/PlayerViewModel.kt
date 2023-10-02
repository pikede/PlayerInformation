package com.example.playerinformation.playerInformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playerinformation.models.Player
import com.example.playerinformation.models.Players
import com.example.playerinformation.network.PlayerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PlayerViewModel(private val playerService: PlayerService) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val players: LiveData<List<Player>> get() = _players
    private val _players = MutableLiveData<List<Player>>()
    private val api by lazy { playerService.getPlayerApi() }

    fun getPlayers(playerName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val playerResponse = api.getPlayer(playerName)
                handleResponse(playerResponse)
            } catch (e: Exception) {
                errorGettingPlayers(e.message ?: e.toString())
            }
        }
    }

    fun handleResponse(playerResponse: Response<Players>) {
        if (playerResponse.isSuccessful) {
            updateSuccessfulPlayers(playerResponse)
        } else {
            playerResponse.errorBody()?.let { errorGettingPlayers(it.toString()) }
        }
    }

    private fun updateSuccessfulPlayers(playerResponse: Response<Players>) {
        playerResponse.body()?.players?.let {
            _players.postValue(it)
        }
    }

    private fun errorGettingPlayers(errorDisplayMessage: String) {
        _players.postValue(emptyList())
        errorMessage.postValue(errorDisplayMessage)
    }
}