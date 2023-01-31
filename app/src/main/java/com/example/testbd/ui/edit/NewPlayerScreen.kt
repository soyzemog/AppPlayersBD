package com.example.testbd.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testbd.data.domain.Player


@Composable
fun NewPlayerScreen(viewModel: NewPlayerViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn() {

            item {

                Column(
                    horizontalAlignment = Alignment.End
                ) {

                    TextField(
                        value = viewModel.surname,
                        onValueChange = { viewModel.editSurname(it) },
                        label = { Text(text = "Surname") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(12.dp))

                    TextField(
                        value = viewModel.nation,
                        onValueChange = { viewModel.editNation(it) },
                        label = { Text(text = "Nationality") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(12.dp))

                    TextField(
                        value = viewModel.team,
                        onValueChange = { viewModel.editTeam(it) },
                        label = { Text(text = "Team") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(12.dp))

                    TextField(
                        value = viewModel.position,
                        onValueChange = { viewModel.editPosition(it) },
                        label = { Text(text = "Position") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(12.dp))

                    TextField(
                        value = viewModel.wear,
                        onValueChange = { viewModel.editWear(it) },
                        label = { Text(text = "Wear") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(12.dp))

                    Row {
                        Button(
                            modifier = Modifier.width(110.dp),
                            onClick = { viewModel.savePlayer() }
                        ) {
                            Text(text = "Save")
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        OutlinedButton(
                            modifier = Modifier.width(110.dp),
                            onClick = { viewModel.cleanFields() }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
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