package com.example.masterand2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.masterand2.ui.theme.MasterAnd2Theme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterAnd2Theme {
                navController = rememberNavController()

                SetupNavGraph(navController = navController)
            }
        }
    }
}