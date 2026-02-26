package com.example.questiongenerator.ui.Home.history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.questiongenerator.data.dto.HistoryItem
import com.example.questiongenerator.data.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val firestoreRepo: FirestoreRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var historyList = mutableStateOf<List<HistoryItem>>(emptyList())
    var error = mutableStateOf<String?>(null)

    fun loadHistory() {

        firebaseAuth.currentUser?.uid?.let { uid ->

            isLoading.value = true
            error.value = null

            firestoreRepo.getHistory(uid) {
                historyList.value = it
                isLoading.value = false
            }

        } ?: run {
            error.value = "User not logged in"
        }
    }
}