package com.example.myexpensetracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpenseRow(expense: Expense) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(text = expense.category)
            Text(text = expense.date)
        }

        Text(
            text = "${expense.currency} ${expense.amount}",
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseRowPreview() {

    ExpenseRow(
        Expense(
            id = 1,
            amount = 20.0,
            currency = "USD",
            category = "Food",
            date = "2026-11-01"
        )
    )
}

@Composable
fun TotalSection(expenses: List<Expense>) {

    val total = expenses.sumOf { it.amount }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text("This Month's Total")

            Text(
                "$%.2f".format(total),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun CategorySection(expenses: List<Expense>) {

    val categoryTotals =
        expenses.groupBy { it.category }
            .mapValues {
                it.value.sumOf { expense ->
                    expense.amount
                }
            }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            "Spending By Category",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        categoryTotals.forEach { (category, total) ->

            Column {

                Text("$category : $$total")

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width((total * 5).dp)
                        .background(Color.Blue)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ExpenseList(expenses: List<Expense>) {

    LazyColumn {

        items(expenses) { expense ->

            ExpenseRow(expense)

            Divider()
        }
    }
}