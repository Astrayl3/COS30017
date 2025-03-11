package com.example.threat

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.threat.ui.theme.ThreatTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    lateinit var txtDisplay: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callThread()
        txtDisplay = findViewById(R.id.txtDisplay)
    }

/*    private fun callThread() {
        CoroutineScope(IO).launch {

            var startTime = System.currentTimeMillis()
            txtDisplay.setText("Time Processing" + (System.currentTimeMillis()-startTime).toString())

            var job1 = launch {
                val result1 = Thread1()
            }

            var job2 = launch {
                val time1 = measureTimeMillis {
                    txtDisplay.setText("debug: launching Thread2" + Thread.currentThread().name)
                    val result1 = Thread2()
                }
            }

            job1.invokeOnCompletion {
                Log.d("T1", "Thread1 finished")
            }
        }
    }*/

    fun callThread(){
        CoroutineScope(IO).launch {
            var executeTime = measureTimeMillis {
                val job1: Deferred<String> = async {
                    Thread1()
                }
                val job2: Deferred<String> = async {
                    Thread2()
                }
                var result1 = job1.await()
                var result2 = job2.await()
/*                var result1 = Thread1()
                var result2 = Thread2()*/
                var result = result1 + result2
                Log.d("T1", "Result" + result)
            }
            Log.d("T1", "Execute Time" + executeTime.toString())
        }
    }

/*    fun Thread1(): String {
        for (i in 0 .. 1000)
            Log.d("T1", "Thread1" + i.toString())
        return "Thread 1"
    }
    fun Thread2(): String {
        for (i in 0 .. 1000)
            Log.d("T2", "Thread2" + i.toString())
        return "Thread 2"
    }*/

    suspend fun Thread2(): String{
        Log.d("T1", "Start Thread 2")
        kotlinx.coroutines.delay(1000)
        Log.d("T1", "Finish Thread 2")
        return "Thread 2"
    }
    suspend fun Thread1(): String {
        Log.d("T1", "Start Thread 1")
        kotlinx.coroutines.delay(1700)
        Log.d("T1", "Finish Thread 1")
        return "Thread 1"
    }
    }
