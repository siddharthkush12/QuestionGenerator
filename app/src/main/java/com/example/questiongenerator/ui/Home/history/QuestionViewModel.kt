package com.example.questiongenerator.ui.Home.history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.questiongenerator.data.dto.HistoryItem
import com.example.questiongenerator.data.dto.UnitMcq
import com.example.questiongenerator.data.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val firestoreRepo: FirestoreRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var error = mutableStateOf<String?>(null)
    var historyItem = mutableStateOf<HistoryItem?>(null)

    fun loadMcq(docId: String) {

        firebaseAuth.currentUser?.uid?.let { uid ->

            isLoading.value = true
            error.value = null

            firestoreRepo.getMcqById(uid, docId) { result ->

                if (result == null) {
                    error.value = "MCQ not found"
                } else {
                    historyItem.value = result
                }

                isLoading.value = false
            }

        } ?: run {
            error.value = "User not logged in"
        }
    }
}