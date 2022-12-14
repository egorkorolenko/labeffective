package ru.korolenkoe.labeffective.screens.mainscreen.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.network.MarvelApiStatus
import ru.korolenkoe.labeffective.repository.CharacterRepositoryApi
import java.io.IOException

class ViewModelGetHeroesApi : ViewModel() {

    private val repositoryApi = CharacterRepositoryApi()

    private val _heroes = MutableStateFlow<List<Character>>(emptyList())
    val heroes: StateFlow<List<Character>> = _heroes

    private val _status = MutableStateFlow(MarvelApiStatus.LOADING)
    val status: StateFlow<MarvelApiStatus> = _status

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
            _status.value = MarvelApiStatus.LOADING
            try {
                withContext(Dispatchers.Main) {
                    _heroes.value = repositoryApi.getCharacters().data.results
                }
                _status.value = MarvelApiStatus.DONE
            } catch (e: IOException) {
                Log.e(e.message, "Error")
                _status.value = MarvelApiStatus.ERROR
                _heroes.value = listOf()
            } catch (e: HttpException) {
                Log.e(e.message, "Error")
                _status.value = MarvelApiStatus.ERROR
                _heroes.value = listOf()
            }
        }
    }
}
