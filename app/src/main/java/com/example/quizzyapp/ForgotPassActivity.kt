package com.example.quizzyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizzyapp.databinding.ActivityForgotBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnForgotBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnForgotSend.setOnClickListener {
            val email = binding.forgotEmail.text.toString()
            if (email.isNotEmpty()){
                Toast.makeText(this, "Enter your email address", Toast.LENGTH_SHORT).show()
            }else{
                binding.btnForgotSend.isEnabled = false
            }

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Email not sent", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}