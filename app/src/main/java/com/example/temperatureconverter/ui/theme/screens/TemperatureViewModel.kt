package com.example.temperatureconverter.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.math.RoundingMode


class TemperatureViewModel: ViewModel() {

    var temperature by mutableStateOf("")
    val onValueChange = {text: String -> temperature = text}

    var roundUp by mutableStateOf(false)

    var result by mutableStateOf("")

    fun calculateTemperature(temp: String):String {
        val tempInt = temp.toDouble()
        result = if (roundUp) {
            kotlin.math.ceil(tempInt * 1.8 + 32).toString()
        } else {
            (tempInt * 1.8 + 32).toString()
        }
        return result
    }
}