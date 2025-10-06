package com.flindigital.watermeter.pages.customers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flindigital.watermeter.data.DummyCustomers
import com.flindigital.watermeter.data.model.Customer

private val LightGray = Color(0xFFF1F5F9)
private val WarningOrange = Color(0xFFFF8A00)

enum class CustomerTab { NotRecorded, Recorded }

@Composable
fun CustomerListScreen(
    selectedTab: CustomerTab,
    query: String,
    onRecordClick: (Customer) -> Unit = {}
) {
    val all = DummyCustomers.customers
    val filtered = all.filter { c ->
        (selectedTab == CustomerTab.NotRecorded && !c.isRecorded ||
                selectedTab == CustomerTab.Recorded && c.isRecorded) &&
                (c.userName.contains(query, ignoreCase = true) ||
                        c.userId.contains(query, ignoreCase = true) ||
                        c.fullAddress.contains(query, ignoreCase = true))
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filtered) { customer ->
            CustomerItem(customer = customer, onRecordClick = onRecordClick)
        }
    }
}

@Composable
private fun CustomerItem(customer: Customer, onRecordClick: (Customer) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFEFD5)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (customer.isRecorded) Icons.Outlined.Star else Icons.Outlined.Star,
                        contentDescription = null,
                        tint = WarningOrange,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ID - ${customer.userId}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { onRecordClick(customer) }, shape = RoundedCornerShape(8.dp)) {
                    Text("Catat")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = customer.userName, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = customer.fullAddress,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF70757A),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFF3E7)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = WarningOrange,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

