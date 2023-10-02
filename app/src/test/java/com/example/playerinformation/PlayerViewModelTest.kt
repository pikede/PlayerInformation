package com.example.playerinformation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.playerinformation.models.Player
import com.example.playerinformation.models.Players
import com.example.playerinformation.network.PlayerService
import com.example.playerinformation.playerInformation.PlayerViewModel
import kotlinx.coroutines.delay
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class PlayerViewModelTest {
    private var service = PlayerService
    private var viewModel: PlayerViewModel? = null
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = PlayerViewModel(service)
    }

    @Test
    fun handleErrorResponse_playerListIsEmpty() = kotlinx.coroutines.test.runTest {
        val response = Response.error<Players>(
            400, ResponseBody.create(
                MediaType.get("askm/*"),
                "response"
            )
        )

        viewModel?.handleResponse(response)

        delay(2000)
        viewModel?.players?.observeForever {
            assertTrue(it.isEmpty())
        }
    }

    @Test
    fun handleSuccessResponse_playerListIsNotEmpty() = kotlinx.coroutines.test.runTest {
        val player =
            Player("21", "Lakers", "nuja", "Basketball", "11/11/2000", "93", "$2938", "dsjn")
        val response = Response.success(200, Players(listOf(player)))

        viewModel?.handleResponse(response)

        delay(2000)
        viewModel?.players?.observeForever {
            assertTrue(it.isNotEmpty())
            assertTrue(it[0] == player)
        }
    }
}