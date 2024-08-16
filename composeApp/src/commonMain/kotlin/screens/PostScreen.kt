package post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import models.response.post.Post

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    postViewModel: HomeViewModel = viewModel()
) {
    val postState by postViewModel.postState.collectAsState()
    Column(modifier) {
        if (postState.isLoading) {
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        if (postState.error != null) {
            Text(postState.error.orEmpty())
        }

        LazyColumn {
            items(postState.posts.size) { index ->
                PostItem(post = postState.posts[index])
            }
        }
    }
}

@Composable
fun PostItem(
    modifier: Modifier = Modifier, post: Post
) {
    Card(modifier.padding(5.dp)) {
        Column(
            modifier.padding(10.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                post.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                post.body,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(8.dp),
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}









