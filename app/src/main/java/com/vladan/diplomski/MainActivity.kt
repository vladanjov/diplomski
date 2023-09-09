package com.vladan.diplomski

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vladan.diplomski.navigation.BottomNavItem
import com.vladan.diplomski.ui.articles.ArticlesScreen
import com.vladan.diplomski.ui.cart.CartScreen
import com.vladan.diplomski.ui.login.LoginScreen
import com.vladan.diplomski.ui.register.RegisterScreen
import com.vladan.diplomski.ui.suppliers.SuppliersScreen
import com.vladan.diplomski.ui.theme.DiplomskiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiplomskiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val bottomNavItems = listOf(
                BottomNavItem.Artikli,
                BottomNavItem.Dobavljaci,
                BottomNavItem.Istorija,
                BottomNavItem.Korpa
            )

            if (bottomNavItems.find { it.route == currentDestination?.route } != null) {
                BottomNavigation(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height(64.dp),
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    contentColor = Color.Unspecified
                ) {
                    bottomNavItems.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Box(modifier = Modifier.size(28.dp)) {
                                    Icon(
                                        modifier = Modifier.align(Alignment.TopCenter),
                                        painter = painterResource(id = screen.iconId),
                                        contentDescription = null,
                                        tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) MaterialTheme.colors.secondary else Color.White
                                    )
                                }
                            },
                            label = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(screen.titleId),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) MaterialTheme.colors.secondary else Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Light
                                    ),
                                    maxLines = 1
                                )
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                if (currentDestination?.hierarchy?.any { it.route == screen.route } != true)
                                    navController.navigate(screen.route) {
                                        launchSingleTop = true
                                    }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavItem.Artikli.route,
            Modifier.padding(innerPadding)
        ) {
            addDestinations(navController)
        }

    }
}


fun NavGraphBuilder.addDestinations(navController: NavController) {
    composable("login") {
        LoginScreen(
            viewModel = hiltViewModel(),
            goToRegister = { navController.navigate("register") })
    }
    composable("register") {
        RegisterScreen(viewModel = hiltViewModel())
    }
    composable(BottomNavItem.Artikli.route) { ArticlesScreen(viewModel = hiltViewModel()) }
    composable(BottomNavItem.Dobavljaci.route) { SuppliersScreen(hiltViewModel()) }
    composable(BottomNavItem.Istorija.route) { Text(text = "Istorija") }
    composable(BottomNavItem.Korpa.route) { CartScreen(viewModel = hiltViewModel()) }
}