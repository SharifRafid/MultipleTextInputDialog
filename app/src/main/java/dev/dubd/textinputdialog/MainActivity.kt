package dev.dubd.textinputdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun testOnClick(view: View) {
        val textInputDialog = TextInputDialog(
            this,
            arrayListOf("Label 1","Label 2","Label 3","Label 4"),
            "Test Dialog Title",
            "Submit",
            false,
            object : OnSubmitListener{
                override fun onCancelled() {
                    Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
                }

                override fun onSubmit(values: ArrayList<String>) {
                    values.map {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        )
        textInputDialog.show()
    }
}