package com.example.questiongenerator.ui.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.questiongenerator.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel@Inject constructor(
    private val repository: AuthRepository
): ViewModel(){

    var isLoading= mutableStateOf(false)
    var error=mutableStateOf<String?>(null)
    var user=mutableStateOf(repository.currentUser())

    fun login(email:String,password:String) {
        isLoading.value=true
        error.value=null
        repository.login(email,password){success,message->
            isLoading.value=false
            if(success){
                user.value=repository.currentUser()
            }
            else{
                error.value=message
            }
        }
    }

    fun register(email:String,fullName:String,password:String) {
        isLoading.value=true
        error.value=null
        repository.register(email, password,fullName) { success, message ->
            isLoading.value=false
            if(success){
                user.value=repository.currentUser()
            }
            else{
                error.value=message
            }
        }
    }



}