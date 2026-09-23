package com.example.nookapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nookapp.data.models.PokemonModel
import com.example.nookapp.data.repository.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val repository: PokeRepository = PokeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<PokemonModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<PokemonModel>>> = _uiState.asStateFlow()

    init { loadPokemon() }

    fun loadPokemon() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                UiState.Success(repository.getPokemonList(limit = 40))
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error al cargar la Pokédex")
            }
        }
    }
}