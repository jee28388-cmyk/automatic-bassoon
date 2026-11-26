package com.aicontrolhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Black = Color(0xFF090909)
private val Gold = Color(0xFFD6AD55)
private val Panel = Color(0xFF191919)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HubScreen() }
    }
}

@Composable
fun HubScreen() {
    var stopped by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Dashboard") }

    MaterialTheme(colorScheme = darkColorScheme(
        background = Black,
        surface = Panel,
        primary = Gold,
        onBackground = Color.White,
        onSurface = Color.White
    )) {
        Column(
            Modifier.fillMaxSize()
                .background(Black)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "AI CONTROL HUB",
                color = Gold,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )

            Text("MASTER CONTROL • PROTOTYPE", color = Color.LightGray)

            Spacer(Modifier.height(22.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Panel),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("EMERGENCY STOP", color = Gold)
                    Text(if (stopped) "Local preview: STOP enabled"
                         else "System not connected")
                    Switch(
                        checked = stopped,
                        onCheckedChange = { stopped = it }
                    )
                    Text(
                        "Demo switch only; real services are not connected.",
                        color = Color.LightGray,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf("Dashboard", "Trading", "YouTube", "Settings").forEach {
                    FilterChip(
                        selected = selected == it,
                        onClick = { selected = it },
                        label = { Text(it) }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Panel),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(18.dp)) {
                    Text(selected, color = Gold, fontSize = 20.sp)

                    Spacer(Modifier.height(12.dp))

                    when (selected) {
                        "Trading" -> {
                            Text("Today's P/L: --")
                            Text("Total balance: --")
                            Text("Today's trades: --")
                            Text("Mode: Demo (not connected)")
                            Text("Signal: NO TRADE")
                        }
                        "YouTube" -> {
                            Text("English Moral Stories: Pending")
                            Text("Urdu Shorts: Pending")
                            Text("Automation: Not connected")
                        }
                        "Settings" -> {
                            Text("Language: Roman Urdu")
                            Text("Theme: Black + Gold")
                            Text("Accounts: Not connected")
                        }
                        else -> {
                            Text("Trading Department: Inactive")
                            Text("YouTube Department: Inactive")
                            Text("Cloud Server: Not connected")
                            Text("Daily report: 7:00 AM PKT (planned)")
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Starter UI only. No live trading, uploads, WhatsApp or cloud automation.",
                color = Color.LightGray,
                fontSize = 12.sp
            )
        }
    }
}
