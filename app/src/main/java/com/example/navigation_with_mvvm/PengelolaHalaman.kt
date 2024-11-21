package com.example.navigation_with_mvvm

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.model.DataJK
import com.example.navigation.ui.view.DetailMahasiswaView
import com.example.navigation.ui.view.FormMahasiswaView
import com.example.navigation.ui.viewmodel.MahasiswaViewModel
import com.example.navigation_with_mvvm.model.JenisKelamin
import com.example.navigation_with_mvvm.view.DetailMahasiswa
import com.example.navigation_with_mvvm.view.FormMahasiswa
import com.example.navigation_with_mvvm.viewmodel.MahasiswaViewModel

enum class Halaman{
    Formulir,
    Detail,
}

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    viewModel: MahasiswaViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
){
    Scaffold { isipadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            modifier = modifier.padding(isipadding),
            navController = navHostController, startDestination = Halaman.Formulir.name
        ){
            composable(route = Halaman.Formulir.name){
                val konteks = LocalContext.current
                FormMahasiswa(
                    listJK = JenisKelamin.listJK.map {
                            isi -> konteks.resources.getString(isi)
                    },
                    onSubmitClicked = {
                        viewModel.saveDataMahasiswa(it)
                        navHostController.navigate(Halaman.Detail.name)
                    }
                )
            }
            composable(route = Halaman.Detail.name) {
                DetailMahasiswa(
                    uiStateMahasiswa = uiState,
                    onBackButton = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    }
}