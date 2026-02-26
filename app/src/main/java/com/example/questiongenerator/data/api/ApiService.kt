package com.example.questiongenerator.data.api

import com.example.questiongenerator.data.dto.McqResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("api/mcq/generate-pdf")
    suspend fun uploadPdf(@Part pdf: MultipartBody.Part): McqResponse

}