package com.farmmanager.app.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmmanager.app.ui.egg.EggViewModel
import com.farmmanager.app.ui.feed.FeedViewModel
import com.farmmanager.app.ui.transaction.TransactionViewModel
import com.farmmanager.app.data.entity.TransactionType
import com.farmmanager.app.util.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    eggViewModel: EggViewModel,
    feedViewModel: FeedViewModel,
    transactionViewModel: TransactionViewModel
) {
    val eggRecords by eggViewModel.records.collectAsState()
    val feedRecords by feedViewModel.records.collectAsState()
    val transactions by transactionViewModel.transactions.collectAsState()

    val totalEggsCollected = eggRecords.sumOf { it.quantityCollected }
    val totalEggsBroken = eggRecords.sumOf { it.quantityBroken }
    val totalFeedCost = feedRecords.sumOf { it.cost }
    val totalFeedKg = feedRecords.sumOf { it.quantityKg }
    val totalIncome = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
    val totalExpense = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
    val netProfit = totalIncome - totalExpense

    Scaffold(topBar = { TopAppBar(title = { Text("Reports") }) }) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { ReportSectionTitle("Egg Production") }
            item { ReportRow("Total collected", "$totalEggsCollected eggs") }
            item { ReportRow("Total broken", "$totalEggsBroken eggs") }

            item { ReportSectionTitle("Feed") }
            item { ReportRow("Total feed used", "%.1f kg".format(totalFeedKg)) }
            item { ReportRow("Total feed cost", "%.2f".format(totalFeedCost)) }

            item { ReportSectionTitle("Finances") }
            item { ReportRow("Total income", "%.2f".format(totalIncome)) }
            item { ReportRow("Total expenses", "%.2f".format(totalExpense)) }
            item { ReportRow("Net profit", "%.2f".format(netProfit), highlight = true) }
        }
    }
}

@Composable
private fun ReportSectionTitle(title: String) {
    Text(title, style = MaterialTheme.typography.titleLarge)
}

@Composable
private fun ReportRow(label: String, value: String, highlight: Boolean = false) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.bodyLarge)
            Text(
                value,
                style = if (highlight) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge
            )
        }
    }
}
