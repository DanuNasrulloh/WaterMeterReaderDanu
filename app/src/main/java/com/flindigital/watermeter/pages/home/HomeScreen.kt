package com.flindigital.watermeter.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flindigital.watermeter.pages.customers.CustomerListScreen
import com.flindigital.watermeter.pages.customers.CustomerTab

private val HeaderGreen = Color(0xFF14B8A6)

@Composable
fun HomeScreen(onNavigate: () -> Unit) {
    var selectedTab by remember { mutableStateOf(CustomerTab.NotRecorded) }
    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(color = HeaderGreen) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Daftar Pelanggan",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Surface(
                        color = Color(0xFF0EA5A3),
                        contentColor = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Sept 2025", fontSize = 12.sp)
                            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null, modifier = Modifier.size(18.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                SegmentedTabs(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    placeholder = { Text("Cari…") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // List below the search
        CustomerListScreen(selectedTab = selectedTab, query = query)
    }
}

@Composable
private fun SegmentedTabs(
    selectedTab: CustomerTab,
    onTabSelected: (CustomerTab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0EA5A3), shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Segment(
            text = "Belum Dicatat",
            selected = selectedTab == CustomerTab.NotRecorded,
            onClick = { onTabSelected(CustomerTab.NotRecorded) },
            modifier = Modifier.weight(1f)
        )
        Segment(
            text = "Sudah Dicatat",
            selected = selectedTab == CustomerTab.Recorded,
            onClick = { onTabSelected(CustomerTab.Recorded) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun Segment(text: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val background = if (selected) Color.White else Color(0xFF0EA5A3)
    val content = if (selected) Color(0xFF0EA5A3) else Color.White
    Surface(color = background, shape = RoundedCornerShape(10.dp), modifier = modifier) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = text, color = content)
        }
    }
}