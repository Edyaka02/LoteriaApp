package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModel: ViewModel() {

    private val _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun generateLotoNumbers() {
        viewModelScope.launch {
            _isLoading.value = true
            _lotoNumbers.value = emptyList()
            val numeros = mutableSetOf<Int>()

            while (numeros.size < 6) {
                val nuevoNum = (1..60).random()
                if (nuevoNum !in numeros) {
                    numeros.add(nuevoNum)
                    _lotoNumbers.value = numeros.toList()
                    delay(2000)
                }
            }

            _isLoading.value = false
        }
    }

}