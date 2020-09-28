package com.example.coroutineexam01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val RESULT_2: String = "Result #2"
    private val RESULT_1: String = "Result #1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRun.setOnClickListener {

            CoroutineScope(IO).launch {
                fakeApiCall()
            }
        }
    }

    private suspend fun fakeApiCall(){
        val result1 = getResult1FromApi()
//
//        updateUi(result1)

        val result2 = getResult2FromApi(result1)

        updateUi(result2)
    }

    private suspend fun updateUi(result1: String) {
        withContext(Main) {
            textContent.text = textContent.text.toString() + "\n$result1"
        }
    }

    private suspend fun getResult1FromApi() : String {

        delay(1000)

        return RESULT_1
    }


    private suspend fun getResult2FromApi(txt : String) : String {

        delay(1000)

        return txt + "\n" + RESULT_2
    }
}