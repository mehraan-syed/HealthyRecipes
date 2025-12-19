package com.uog.healthyrecipes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Help / About", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        Text(
            "Healthy eating made easy!",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(12.dp))

        Text("How to use:", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text("• Tap a recipe to view details.", style = MaterialTheme.typography.bodyMedium)
        Text("• Tap ★ to add/remove favourites.", style = MaterialTheme.typography.bodyMedium)
        Text("• Use the Favourites page to remember the delicious ones.", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))
        Text("Data Storage:", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text("• Never miss shopping with offline saving.", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))
        Text("Version:", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text("• v1.0", style = MaterialTheme.typography.bodyMedium)
    }
}
