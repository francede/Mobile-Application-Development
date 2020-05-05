package com.example.gamebacklog.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.database.GameRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val reminderRepository = GameRepository(application.applicationContext)

    val games: LiveData<List<Game>> = reminderRepository.getAllGames()

    fun insertGame(game: Game){
        ioScope.launch {
            reminderRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game){
        ioScope.launch {
            reminderRepository.deleteGame(game)
        }
    }

    fun deleteAllGames(){
        ioScope.launch{
            reminderRepository.deleteAllGames()
        }
    }

}
