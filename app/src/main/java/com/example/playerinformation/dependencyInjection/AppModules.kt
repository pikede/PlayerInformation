package com.example.playerinformation.dependencyInjection

import com.example.playerinformation.network.PlayerService
import com.example.playerinformation.playerHistory.PlayerHistoryViewModel
import com.example.playerinformation.playerInformation.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModules = module {
    factory { PlayerService }
    viewModel { PlayerViewModel(get()) }
    viewModel { PlayerHistoryViewModel(get()) }
}