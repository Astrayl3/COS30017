/*
package com.example.week8

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.week8.ui.theme.Week8Theme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week8Theme {
                FileWriterScreen()
            }
        }
    }

    // Function to write to the file
    private fun writeToFile(text: String) {
*/
/*        val file = File(Environment.getExternalStorageDirectory(), "/Documents/test.txt")
        file.createNewFile()
        file.writeText("Hello External Storage")*//*

        try {
            val fileOutput = openFileOutput("text.txt", Context.MODE_PRIVATE)
            fileOutput.write(text.toByteArray())  // Write user input
            fileOutput.close()
            Toast.makeText(this, "File Saved!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Function to read from the file and return as String
    private fun readFromFile(context: Context): String {
*/
/*
        val file = File(Environment.getExternalStorageDirectory(), "/Documents/test.txt").exists()

*//*



        return try {
            val fileInput = openFileInput("text.txt")
            val content = fileInput.bufferedReader().use { it.readText() } // Properly read all content
            fileInput.close()
            content
        } catch (e: Exception) {
            e.printStackTrace()
            "Error reading file"
        }
    }



    @Composable
    fun FileWriterScreen() {
        val context = LocalContext.current
        var textInput by remember { mutableStateOf("") }
        var fileContent by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Enter Text:")
            Spacer(modifier = Modifier.height(8.dp))

            BasicTextField(
                value = textInput,
                onValueChange = { textInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (textInput.isNotEmpty()) {
                    writeToFile(context, textInput)
                    Toast.makeText(context, "Text Saved!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Enter some text first!", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save to File")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                fileContent = readFromFile(context)
            }) {
                Text("Read from File")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("File Content: $fileContent")
        }
    }

    // Updated writeToFile function to accept input
    private fun writeToFile(context: Context, text: String) {
        try {
            val file = File(context.filesDir, "text.txt")
            file.writeText(text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
*/


package com.example.week8

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.week8.ui.theme.Week8Theme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week8Theme {
                FileWriterScreen()
            }
        }

        // Request Storage Permissions
        requestStoragePermissions()
    }

    // Function to request storage permissions
    private fun requestStoragePermissions() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
            )
        }
    }

    // Function to write to Internal Storage
    private fun writeToInternalStorage(context: Context, text: String) {
        try {
            val file = File(context.filesDir, "text.txt") // Internal storage file
            file.writeText(text)
            Toast.makeText(context, "Saved to Internal Storage!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error writing to Internal Storage", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to read from Internal Storage
    private fun readFromInternalStorage(context: Context): String {
        return try {
            val file = File(context.filesDir, "text.txt")
            if (file.exists()) file.readText() else "No file found in Internal Storage!"
        } catch (e: Exception) {
            e.printStackTrace()
            "Error reading from Internal Storage"
        }
    }

    // Function to write to External Storage
    private fun writeToExternalStorage(context: Context, text: String) {
        val externalFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "test.txt")

        if (!isExternalStorageWritable()) {
            Toast.makeText(context, "External Storage Not Available", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            externalFile.writeText(text)
            Toast.makeText(context, "Saved to External Storage!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error writing to External Storage", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to read from External Storage
    private fun readFromExternalStorage(context: Context): String {
        val externalFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "test.txt")

        if (!isExternalStorageReadable()) {
            return "External Storage Not Available"
        }

        return try {
            if (externalFile.exists()) externalFile.readText() else "No file found in External Storage!"
        } catch (e: Exception) {
            e.printStackTrace()
            "Error reading from External Storage"
        }
    }

    // Check if external storage is available for writing
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Check if external storage is available for reading
    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    @Composable
    fun FileWriterScreen() {
        val context = LocalContext.current
        var textInput by remember { mutableStateOf("") }
        var fileContent by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Enter Text:")
            Spacer(modifier = Modifier.height(8.dp))

            BasicTextField(
                value = textInput,
                onValueChange = { textInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button to Save to Internal Storage
            Button(onClick = {
                if (textInput.isNotEmpty()) {
                    writeToInternalStorage(context, textInput)
                } else {
                    Toast.makeText(context, "Enter some text first!", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save to Internal Storage")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button to Save to External Storage
            Button(onClick = {
                if (textInput.isNotEmpty()) {
                    writeToExternalStorage(context, textInput)
                } else {
                    Toast.makeText(context, "Enter some text first!", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save to External Storage")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button to Read from Internal Storage
            Button(onClick = {
                fileContent = readFromInternalStorage(context)
            }) {
                Text("Read from Internal Storage")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button to Read from External Storage
            Button(onClick = {
                fileContent = readFromExternalStorage(context)
            }) {
                Text("Read from External Storage")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display File Content
            Text("File Content: $fileContent")
        }
    }
}
