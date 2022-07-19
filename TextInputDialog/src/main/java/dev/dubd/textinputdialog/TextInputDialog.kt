package dev.dubd.textinputdialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class TextInputDialog(val context : Context,
                      val labels: ArrayList<String>,
                      val title: String,
                      val submitText: String,
                      val allowEmptyInputs: Boolean,
                      val onSubmitListener: OnSubmitListener) {

    // Creates an alert dialog
    val alertDialog = AlertDialog.Builder(context).create()

    fun show(){
        // Inflates the custom view for the dialog
        val alertDialogView = LayoutInflater.from(context).inflate(R.layout.text_input_dialog_layout, null)

        // Array for holding the edittexts and fetching their values later on
        val editTexts = ArrayList<EditText>()

        // LinearLayout that will contain the edittexts
        val viewGroupMain = alertDialogView.findViewById(R.id.edit_text_container_linear_layout) as ViewGroup

        // TODO Make this part more efficient maybe ? \__(`^`)__/
        labels.map {
            // Create a view
            val editTextView = LayoutInflater.from(context).inflate(R.layout.edittext_view, null)
            // And find the edittext
            val editText = editTextView.findViewById<EditText>(R.id.main_edit_text_1)
            // Use the label as the hint of that ET
            editText.hint = it
            // Add the edittext to the edittexts array
            editTexts.add(editText)
            // Add the edittext to the viewgroup or the linear layout
            viewGroupMain.addView(editTextView)
        }

        // For removing the default bg of the dialog and making it transparent
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        // In case the user cancels the dialog by clicking outside the window
        // Or the user clicks the backbutton or stuff
        // Then the cancellation of the dialog will call the on cancelled function of the listener
        alertDialog.setOnCancelListener {
            onSubmitListener.onCancelled()
        }

        // Set the title of the dialog
        val titleTextView = alertDialogView.findViewById<TextView>(R.id.title_text_view)
        titleTextView.text = title

        // Find the submit button
        val submitButton = alertDialogView.findViewById<Button>(R.id.submit_button)
        // Set the text
        submitButton.text = submitText

        // Add a click listener
        submitButton.setOnClickListener {
            // Array for storing the input values
            val inputs = ArrayList<String>()

            // Check if empty inputs are allowed or not
            if(allowEmptyInputs){
                // Map the edittexts array
                editTexts.map {
                    // and add all the values
                    inputs.add(it.text.toString())
                }
            }else{
                // No empty inputs allowed
                // Map the edittexts array
                editTexts.map {
                    // Check for any text if empty or not
                    val text = it.text.toString()
                    if(text.isEmpty()){
                        // If empty return the function and let the user know
                        Toast.makeText(context, "No field can be empty", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }else{
                        // else add the values
                        inputs.add(text)
                    }
                }
            }
            // Final check to make sure all the edittexts values are passed
            if(inputs.size == editTexts.size){
                // Call the on submit listener
                onSubmitListener.onSubmit(inputs)
                // Dismiss the dialog
                alertDialog.dismiss()
            }
        }

        // Set the dialog custom view
        alertDialog.setView(alertDialogView)

        // Showing the alert dialog
        alertDialog.show()
    }

}