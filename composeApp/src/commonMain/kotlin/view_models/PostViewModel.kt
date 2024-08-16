package post

import utils.Graph
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import models.response.post.Post
import repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.Response

class HomeViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {
    private val _postState = MutableStateFlow(PostState())
    val postState = _postState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getPostList().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _postState.update {
                            it.copy(
                                isLoading = true, error = null
                            )
                        }
                    }

                    is Response.Success -> {
                        _postState.update {
                            it.copy(
                                isLoading = false, error = null,
                                posts = result.data
                            )
                        }
                    }

                    is Response.Error -> {
                        _postState.update {
                            it.copy(
                                isLoading = false, error = result.error?.message
                            )
                        }
                    }
                }
            }
        }
    }


}

data class PostState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)