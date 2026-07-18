package com.farmmanager.app.ui.flock

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmmanager.app.data.entity.Flock
import com.farmmanager.app.util.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlockScreen(viewModel: FlockViewModel) {
    val flocks by viewModel.flocks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Flocks") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add flock")
            }
        }
    ) { padding ->
        if (flocks.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No flocks yet. Tap + to add one.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(flocks, key = { it.id }) { flock ->
                    FlockCard(flock = flock, onDelete = { viewModel.deleteFlock(flock) })
                }
            }
        }
    }

    if (showDialog) {
        AddFlockDialog(
            onDismiss = { showDialog = false },
            onConfirm = { name, breed, qty, cost, notes ->
                viewModel.addFlock(name, breed, qty, DateUtils.now(), cost, notes)
                showDialog = false
            }
        )
    }
}

@Composable
private fun FlockCard(flock: Flock, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(flock.name, style = MaterialTheme.typography.titleMedium)
                Text("${flock.breed} • ${flock.quantity} birds", style = MaterialTheme.typography.bodyMedium)
                Text("Acquired ${DateUtils.formatDate(flock.acquisitionDate)}", style = MaterialTheme.typography.labelSmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddFlockDialog(
    onDismiss: () -> Unit,
    onConfirm: (name: String, breed: String, qty: Int, cost: Double, notes: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Flock") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Flock name") })
                OutlinedTextField(value = breed, onValueChange = { breed = it }, label = { Text("Breed") })
                OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity") })
                OutlinedTextField(value = cost, onValueChange = { cost = it }, label = { Text("Acquisition cost") })
                OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notes") })
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(
                    name,
                    breed,
                    quantity.toIntOrNull() ?: 0,
                    cost.toDoubleOrNull() ?: 0.0,
                    notes
                )
            }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
