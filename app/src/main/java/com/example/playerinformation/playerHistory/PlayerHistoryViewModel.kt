package com.example.playerinformation.playerHistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playerinformation.models.FormerTeam
import com.example.playerinformation.models.FormerTeams
import com.example.playerinformation.network.PlayerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PlayerHistoryViewModel(private val playerService: PlayerService) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val formerTeams: LiveData<List<FormerTeam>> get() = _formerTeams
    private val _formerTeams = MutableLiveData<List<FormerTeam>>()
    private val api by lazy { playerService.getPlayerApi() }

    fun getPlayerFormerTeams(playerId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val previousTeamResponse = api.getPlayerPreviousTeams(playerId)
                handleResponse(previousTeamResponse)
            } catch (e: Exception) {
                previousTeamError(e.message ?: e.toString())
            }
        }
    }

    private fun handleResponse(previousTeamResponse: Response<FormerTeams>) {
        if (previousTeamResponse.isSuccessful) {
            previousTeamSuccess(previousTeamResponse)
        } else {
            previousTeamResponse.errorBody()?.let {
                previousTeamError(it.toString())
            }
        }
    }

    private fun previousTeamSuccess(formerTeam: Response<FormerTeams>) {
        formerTeam.body()?.let {
            it.formerteams?.let { teams ->
                _formerTeams.postValue(teams)
            }
        }
    }

    private fun previousTeamError(errorDisplayMessage: String) {
        errorMessage.postValue(errorDisplayMessage)
    }
}