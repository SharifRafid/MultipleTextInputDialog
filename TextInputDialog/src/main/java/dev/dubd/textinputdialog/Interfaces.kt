package dev.dubd.textinputdialog

interface OnSubmitListener{
    fun onCancelled()
    fun onSubmit(values : ArrayList<String>)
}