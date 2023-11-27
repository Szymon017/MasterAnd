package com.example.masterand

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.masterand.ui.theme.MasterAndTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterAndTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ProfileScreenInitial()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenInitial() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var n_of_colors by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var nOfColorsError by remember { mutableStateOf(false) }

    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedUri ->
            if (selectedUri != null) {
                profileImageUri = selectedUri
            }
        })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Box {
            if (profileImageUri != null) {
                // Jeżeli obraz został wybrany, użyj go
                AsyncImage(
                    model = profileImageUri!!,
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            } else {
                // W przeciwnym razie, wyświetl obrazek z pytajnikiem
                Image(
                    painterResource(id = R.drawable.ic_baseline_question_mark_24),
                    contentDescription = "Profile photo",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            IconButton(
                onClick = {
                    imagePicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Image(
                    painterResource(id = R.drawable.baseline_image_search_24),
                    contentDescription = "ChangeButton",
                    modifier = Modifier.offset(10.dp, 0.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
                nameError = it.isEmpty()
            },
            label = { Text("Name") },
            singleLine = true,
            isError = nameError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            trailingIcon = {
                if (nameError) {
                    Image(
                        painterResource(id = R.drawable.baseline_error_24),
                        contentDescription = "ErrorIcon",
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            supportingText = { if (nameError) Text("Name can't be empty") else null }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                emailError = it.isEmpty()
            },
            label = { Text("E-mail") },
            singleLine = true,
            isError = emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = { if (emailError) Text("E-mail can't be empty") else null }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = n_of_colors,
            onValueChange = {
                n_of_colors = it
                nOfColorsError = it.isEmpty()
            },
            label = { Text("Enter number of colors") },
            singleLine = true,
            isError = nOfColorsError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = { if (nOfColorsError) Text("Number of colors cannot be empty") else null }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Dodaj walidację pól i ustawianie błędów
            nameError = name.isEmpty()
            emailError = email.isEmpty()
            nOfColorsError = n_of_colors.isEmpty()

            // Tutaj możesz umieścić kod obsługujący kliknięcie przycisku Submit
            if (!nameError && !emailError && !nOfColorsError) {
                // Jeżeli brak błędów, możesz kontynuować z procesem submit
                // Tutaj możesz dodać kod do obsługi przycisku Submit
            }
        }) {
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MasterAndTheme {
        ProfileScreenInitial()
    }
}
