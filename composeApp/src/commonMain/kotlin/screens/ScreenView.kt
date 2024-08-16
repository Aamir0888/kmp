package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeView(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home")

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Go to Detail screen",
            modifier = Modifier.clickable {
                onNavigate(AppScreen.Detail.route)
            }
        )
    }
}

@Composable
fun ReelsView(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Reels")

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Go to Detail screen",
            modifier = Modifier.clickable {
                onNavigate(AppScreen.Detail.route)
            }
        )
    }
}

@Composable
fun ProfileView(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile")

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Go to Detail screen",
            modifier = Modifier.clickable {
                onNavigate(AppScreen.Detail.route)
            }
        )
    }
}

@Composable
fun DetailsView(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .clickable {

            }
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Details")

        Text(
            text = "Back",
            modifier = Modifier.clickable {
//                onNavigate(AppConstants.BACK_CLICK_ROUTE)
                onNavigate("123")
            }
        )
    }
}