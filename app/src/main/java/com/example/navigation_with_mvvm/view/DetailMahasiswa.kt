package com.example.navigation_with_mvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navigation_with_mvvm.model.DataMahasiswa

@Composable
fun DetailMahasiswa(
    modifier: Modifier,
    uiStateMahasiswa: DataMahasiswa,
    onBackButton: () -> Unit
) {
    val listDataMhs = listOf(
        Pair("Nama", uiStateMahasiswa.nama),
        Pair("Gender", uiStateMahasiswa.gender),
        Pair("Alamat", uiStateMahasiswa.alamat),
        Pair("NIM", uiStateMahasiswa.nim)
    )
    Column(
        modifier = modifier
            .padding(start = 8.dp)
    ) {

        Button(
            onClick = { onBackButton() },
        ) {
            Text("Back")
        }

        Column {
            listDataMhs.forEach { item ->
                CardSection(
                    judulParam = item.first,
                    isiParam = item.second
                )
            }
        }
    }
}

@Composable
fun CardSection(judulParam: String, isiParam: String) {
    Column() {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = judulParam,
                modifier = Modifier.weight(0.8f)
            )
            Text(
                text = ":",
                modifier = Modifier.weight(0.2f)
            )
            Text(
                text = "$isiParam",
                modifier = Modifier.weight(2f)
            )
        }
    }
}