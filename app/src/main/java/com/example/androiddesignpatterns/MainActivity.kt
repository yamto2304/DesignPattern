package com.example.androiddesignpatterns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.annotations.Item
import com.example.annotations.Validator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val item = Item(amount = -1.0f, name = "Bob")
        val item2 = Item(amount = 1.0f, name = "Mel")
        val validator = Validator()
        println("Is instance valid? ${validator.isValid(item)}")
        println("Is instance valid? ${validator.isValid(item2)}")
    }
}