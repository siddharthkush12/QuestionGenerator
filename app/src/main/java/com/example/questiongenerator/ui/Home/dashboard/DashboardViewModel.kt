package com.example.questiongenerator.ui.Home.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questiongenerator.data.api.ApiService
import com.example.questiongenerator.data.dto.UnitMcq
import com.example.questiongenerator.data.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: ApiService,
    private val firestoreRepo: FirestoreRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var error = mutableStateOf<String?>(null)

    var courseTitle = mutableStateOf("")
    var units = mutableStateOf<List<UnitMcq>>(emptyList())

    fun uploadPdf(file: File) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                error.value = null

                val requestFile = file.asRequestBody("application/pdf".toMediaType())

                val body = MultipartBody.Part.createFormData(
                    "pdf",
                    file.name,
                    requestFile
                )

                val response = apiService.uploadPdf(body)

                if (response.success) {

                    // ✅ Update UI State
                    courseTitle.value = response.title
                    units.value = response.units

                    // ✅ Save to Firestore
                    firebaseAuth.currentUser?.uid?.let { uid ->

                        firestoreRepo.saveMcq(
                            uid = uid,
                            title = response.title,
                            units = response.units
                        )
                    }

                } else {
                    error.value = "MCQ generation failed"
                }

            } catch (e: Exception) {
                error.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}