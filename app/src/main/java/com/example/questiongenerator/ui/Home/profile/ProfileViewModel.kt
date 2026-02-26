package com.example.questiongenerator.ui.Home.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.questiongenerator.data.repository.AuthRepository
import com.google.firebase.auth.userProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject




@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var message = mutableStateOf<String?>(null)

    val user get() = authRepository.currentUser()


    fun signOut() {
        authRepository.logout()
    }
}