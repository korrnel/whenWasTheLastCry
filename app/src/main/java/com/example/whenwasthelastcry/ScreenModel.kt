package com.example.whenwasthelastcry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class ScreenModel() : ViewModel() {

    val cryBaby = MutableLiveData("")
    val confirmationState = MutableLiveData(false)
    val prevousAttempts = MutableLiveData<List<String>>(emptyList())
    val lastCryText = MutableLiveData("")
    private var lastCry: Long = parseDateToMillis("2024-08-10 17:10:10")

    fun showConfirm(){
        this.confirmationState.value=true
    }
    fun setLastCry(){
       lastCry= System.currentTimeMillis()
       val attempts =  prevousAttempts.value!!.toMutableList()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        attempts.add(dateFormat.format(Date(lastCry))+ " "+ cryBaby.value)
        if (attempts.size>6) attempts.removeFirst()
        prevousAttempts.value=attempts
        cryBaby.value=""
    }

    fun hideConfirm() {
        confirmationState.value=false
    }

    fun setCryBaby(it: String) {
       cryBaby.value=it
    }

    init {
        viewModelScope.launch {
            while (true) {


                val timeDifference: Long = System.currentTimeMillis() - lastCry
                // Calculate days, hours, minutes, and seconds
                val days = TimeUnit.MILLISECONDS.toDays(timeDifference)
                val hours = TimeUnit.MILLISECONDS.toHours(timeDifference) % 24
                val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDifference) % 60

                // Format the elapsed time
                lastCryText.postValue(
                    when {
                        days > 0 -> "$days nap, $hours óra, $minutes perc, $seconds"
                        hours > 0 -> "$hours óra, $minutes perc, $seconds"
                        minutes > 0 -> "$minutes perc, $seconds"
                        else -> "$seconds"
                    }
                 + " másodperc\ntelt el az utolsó hiszti óta!!")

                delay(1000L) // Update every second

            }
        }
    }
}
// Utility function to parse date string to milliseconds
private fun parseDateToMillis(dateStr: String): Long {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return format.parse(dateStr)?.time ?: 0L
}