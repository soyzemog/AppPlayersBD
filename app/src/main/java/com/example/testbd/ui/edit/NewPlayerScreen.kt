package com.example.testbd.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewPlayerScreen(idPlayer: Int, viewModel: NewPlayerViewModel = viewModel()) {

    var surnameText by remember { mutableStateOf("") }
    var nationText by remember { mutableStateOf("") }
    var teamText by remember { mutableStateOf("") }
    var posText by remember { mutableStateOf("") }
    var wearText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.End
        ) {
            if (idPlayer != 0) {

                val editPlayer = viewModel.loadPlayer(idPlayer)

                surnameText = editPlayer.surname
                nationText = editPlayer.nationality.toString()
                teamText = editPlayer.team?.first() ?: ""
                posText = editPlayer.position?.first() ?: ""
                wearText = editPlayer.wear?.first().toString()

            }

            TextField(
                value = surnameText,
                onValueChange = { surnameText = it },
                label = { Text(text = "Surname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            TextField(
                value = nationText,
                onValueChange = { nationText = it },
                label = { Text(text = "Nationality") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            TextField(
                value = teamText,
                onValueChange = { teamText = it },
                label = { Text(text = "Team") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            TextField(
                value = posText,
                onValueChange = { posText = it },
                label = { Text(text = "Position") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            TextField(
                value = wearText,
                onValueChange = { wearText = it },
                label = { Text(text = "Wear") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Row {
                Button(
                    modifier = Modifier.width(110.dp),
                    onClick = { /* viewModel.savePlayer(
                        Player(
                            1,
                            surnameTextState.text,
                            nationTextState.text,
                            listOf(teamTextState.text),
                            listOf(posTextState.text),
                            listOf(wearTextState.text.toInt())
                        )
                    )*/ }
                ) {
                    Text(text = "Save")
                }

                Spacer(modifier = Modifier.padding(8.dp))

                OutlinedButton(
                    modifier = Modifier.width(110.dp),
                    onClick = {
                        surnameText = ""
                        nationText = ""
                        teamText = ""
                        posText = ""
                        wearText = ""
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        }

    }

}


/** @Preview
@Composable
fun Preview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        NewPlayerScreen()
    }
} **/