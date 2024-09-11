package com.example.celciustofahrenheit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.celciustofahrenheit.ui.theme.CelciusToFahrenheitTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CelciusToFahrenheitTheme {
                Screen()

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun CelsiusSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit) {
    Slider (
        modifier = Modifier.padding(10.dp),
        valueRange = 0f..100f,
        value = sliderPosition,
        onValueChange = {onPositionChange(it)}

    )
}

@Composable
fun FahrenheitSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit) {
    Slider (
        modifier = Modifier.padding(10.dp),
        valueRange = 0f..212f,
        value = sliderPosition,
        onValueChange = {onPositionChange(it)}

    )
}

@Composable
fun Screen() {
    var sliderPosition by remember { mutableStateOf(20f) }

    var FsliderPosition by remember { mutableStateOf((sliderPosition * 9/5) + 32) }
    val handlePositionChange = { position: Float ->
        sliderPosition = position
        FsliderPosition = (position * 9/5) + 32
    }

    val FhandlePositionChange = { position: Float ->
        if (position <32) {
            FsliderPosition = 32f
        } else {
            FsliderPosition = position
        }
        sliderPosition = (FsliderPosition-32) * 5/9
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Text(style = MaterialTheme.typography.headlineMedium,
            text = "Celsius"
        )
        CelsiusSlider(
            sliderPosition = sliderPosition,
            onPositionChange = handlePositionChange
        )
        val df = DecimalFormat("#.##")
        Text(style = MaterialTheme.typography.headlineMedium,
            text = df.format(sliderPosition).toString()
        )
        Spacer(modifier = Modifier.height(75.dp))
        Text(style = MaterialTheme.typography.headlineMedium,
            text = "Fahrenheit"
        )
        FahrenheitSlider(
            sliderPosition = FsliderPosition,
            onPositionChange = FhandlePositionChange
        )
        Text(style = MaterialTheme.typography.headlineMedium,
            text = df.format(FsliderPosition).toString()
        )
        var interesting_text = ""
        if (sliderPosition <= 20) {
            interesting_text = "I wish it were warmer."
        } else {
            interesting_text = "I wish it were colder."
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(style = MaterialTheme.typography.headlineMedium,
            text = interesting_text
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CelciusToFahrenheitTheme {
        Screen()
    }
}