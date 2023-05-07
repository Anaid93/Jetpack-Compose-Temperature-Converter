package com.example.temperatureconverter.ui.theme.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.temperatureconverter.R
import java.text.NumberFormat
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview(showSystemUi = true)
@Composable
fun PreviewScreen() {
    HomeScreen()
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    temperatureViewModel: TemperatureViewModel = viewModel()
){
    MainScreen(
        roundUp = temperatureViewModel.roundUp,
        onRoundUpChanged = {temperatureViewModel.roundUp = it},
        calculateTemperature = {temperatureViewModel.calculateTemperature(it)},
        temperature = temperatureViewModel.temperature,
        onValueChange = temperatureViewModel.onValueChange,
        result = temperatureViewModel.result
    )
}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    roundUp:Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    calculateTemperature:(String) -> Unit,
    temperature: String,
    onValueChange: (String) -> Unit,
    result: String
){

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            stringResource(R.string.temperature_converter),
            fontSize = 24.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

        //Spacer(modifier = modifier.height(16.dp))

        EditNumberField(
            label = R.string.celsius,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            value = temperature,
            onValueChange = onValueChange
        )

        RoundTheResultRow(
            roundUp = roundUp,
            onRoundUpChanged = onRoundUpChanged
        )

        //Spacer(Modifier.height(24.dp)


        Text(
            text = stringResource(R.string.fahrenheit, result),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Button(onClick = { calculateTemperature(temperature) }) {
            Text(text = "Convert Temperature")
        }
    }
}

@Composable
fun EditNumberField(
    @StringRes label:Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}


@Composable
fun RoundTheResultRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .size(48.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = "Round up?")
        Switch(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray)
        )
    }
}