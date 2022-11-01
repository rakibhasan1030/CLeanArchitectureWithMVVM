package rakib.hasan.cleanwithmvvm.features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import rakib.hasan.cleanwithmvvm.core.base.BaseResult
import rakib.hasan.cleanwithmvvm.features.domain.entity.TodoEntity
import rakib.hasan.cleanwithmvvm.features.domain.usecase.FetchTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
  private val fetchTodosUseCase: FetchTodosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainActivityState>(MainActivityState.Init)
    val state : StateFlow<MainActivityState> get() = _state

    private val _todos = MutableStateFlow(mutableListOf<TodoEntity>())
    val todos : StateFlow<List<TodoEntity>> get() = _todos

    private fun setLoading(){
        _state.value = MainActivityState.IsLoading(true)
    }
    private fun hideLoading(){
        _state.value = MainActivityState.IsLoading(false)
    }
    private fun showToast(message: String){
        _state.value = MainActivityState.ShowToast(message)
    }

    fun fetchTodos(){
        viewModelScope.launch {
            fetchTodosUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch { e ->
                    hideLoading()
                    showToast(e.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> {
                            _todos.value = result.data as MutableList<TodoEntity>
                        }
                        is BaseResult.Error -> {
                            // 0 means no internet connection
                            if (result.error.code != 0){
                                val mgs = "${result.error.message} [${result.error.code}]"
                                showToast(mgs)
                            }
                        }
                    }
                }
        }
    }


}

sealed class MainActivityState {
    object Init : MainActivityState()
    data class ShowToast(val message : String) : MainActivityState()
    data class IsLoading(val isLoading: Boolean) : MainActivityState()
}