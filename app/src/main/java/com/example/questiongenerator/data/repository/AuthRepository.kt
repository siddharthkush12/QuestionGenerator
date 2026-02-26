package com.example.questiongenerator.data.repository

import android.R.attr.name
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private var firebaseAuth: FirebaseAuth,
){

    fun login(
        email: String,
        password: String,
        onComplete: (Boolean,String?) -> Unit
    ){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                onComplete(true,null)
            }
            .addOnFailureListener {
                onComplete(false,it.message)
            }
    }

    fun register(
        email: String,
        password: String,
        fullName:String,
        onComplete: (Boolean,String?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser

                    val updates = userProfileChangeRequest {
                        displayName = fullName
                    }

                    user?.updateProfile(updates)
                        ?.addOnSuccessListener {
                            onComplete(true, null)
                        }
                        ?.addOnFailureListener {
                            onComplete(false, it.message)
                        }

                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }

    fun logout()=firebaseAuth.signOut()

    fun currentUser()=firebaseAuth.currentUser

}