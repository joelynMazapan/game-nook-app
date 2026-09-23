package com.example.nookapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nookapp.data.models.GameModel
import com.example.nookapp.data.repository.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GamesViewModel(
    private val repository: PokeRepository = PokeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<GameModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<GameModel>>> = _uiState.asStateFlow()

    init { loadGames() }

    fun loadGames() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                UiState.Success(repository.getGamesList())
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error al cargar los juegos")
            }
        }
    }
}