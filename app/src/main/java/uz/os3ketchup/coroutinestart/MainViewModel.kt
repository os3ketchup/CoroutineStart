package uz.os3ketchup.coroutinestart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

private val coroutineScope = CoroutineScope(Dispatchers.Main+CoroutineName("My coroutine Scope"))
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }

        coroutineScope.launch {
            val number = value.toLong()
            val result = withContext(Dispatchers.Default) {
                factorial(number)
            }
            _state.value = Factorial(result)
            Log.d("SCOPE", "calculate: ${coroutineScope.toString()}")
        }


    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}



private fun factorial(number: Long): String {
    var result = BigInteger.ONE
    for (i in 1..number) {
        result = result.multiply(BigInteger.valueOf(i))
    }
    return result.toString()


}



/*
    private suspend fun factorial(number: Long): String {
        return suspendCoroutine {
            thread {
                var result = BigInteger.ONE
                for (i in 1..number) {
                    result = result.multiply(BigInteger.valueOf(i))
                }
                it.resumeWith(Result.success(result.toString()))
            }
        }

    }
*/

