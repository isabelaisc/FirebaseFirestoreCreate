package com.example.appfirestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

private val bd_firestore = FirebaseFirestore.getInstance()

//classe principal MainActivity
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = " FORMULÁRIO ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(vertical = 25.dp)
                    )



                    val nome = remember { mutableStateOf("") }
                    val endereco = remember { mutableStateOf("") }
                    val bairro = remember { mutableStateOf("") }
                    val cep = remember { mutableStateOf("") }
                    val cidade = remember { mutableStateOf("") }
                    val estado = remember { mutableStateOf("") }

                    // Variável para desmarcar o cursor (tira o foco da caixa de texto) cont. no button criar registro
                    val focusManager = LocalFocusManager.current

                    OutlinedTextField(
                        value = nome.value,
                        onValueChange = { nome.value = it },
                        label = { Text("Nome") }
                    )
                    OutlinedTextField(
                        value = endereco.value,
                        onValueChange = { endereco.value = it },
                        label = { Text("Endereço") }
                    )
                    OutlinedTextField(
                        value = bairro.value,
                        onValueChange = { bairro.value = it },
                        label = { Text("Bairro") }
                    )
                    OutlinedTextField(
                        value = cep.value,
                        onValueChange = { cep.value = it },
                        label = { Text("CEP") }
                    )
                    OutlinedTextField(
                        value = cidade.value,
                        onValueChange = { cidade.value = it },
                        label = { Text("Cidade") }

                    )
                    OutlinedTextField(
                        value = estado.value,
                        onValueChange = { estado.value = it },
                        label = { Text("Estado") }
                    )

                    Button(
                        onClick = {
                            val data = hashMapOf(
                                "nome" to nome.value,
                                "endereco" to endereco.value,
                                "bairro" to bairro.value,
                                "cep" to cep.value,
                                "cidade" to cidade.value,
                                "estado" to estado.value
                            )

                            bd_firestore.collection("registros")
                                .add(data)
                                .addOnSuccessListener {
                                    // Registro criado com sucesso
                                    // Limpar registro anterior
                                    nome.value = ""
                                    endereco.value = ""
                                    bairro.value = ""
                                    cep.value = ""
                                    cidade.value = ""
                                    estado.value = ""
                                    // Desmarcar Cursor
                                    focusManager.clearFocus()
                                }
                                .addOnFailureListener {
                                    // Ocorreu um erro ao criar o registro
                                }
                        },
                        modifier = Modifier.padding(vertical = 16.dp),
                                colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text("Criar Registro")
                    }
                }
            }

        }
    }

}




