package com.example.questiongenerator.data.repository

import com.example.questiongenerator.data.dto.HistoryItem
import com.example.questiongenerator.data.dto.UnitMcq


import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import javax.inject.Inject



class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun saveMcq(
        uid: String,
        title: String,
        units: List<UnitMcq>
    ) {

        val data = hashMapOf(
            "title" to title,
            "units" to units,
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("users")
            .document(uid)
            .collection("history")
            .add(data)
    }

    fun getHistory(
        uid: String,
        onResult: (List<HistoryItem>) -> Unit
    ) {
        firestore.collection("users")
            .document(uid)
            .collection("history")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->

                val list = result.documents.map { doc ->

                    val units = doc.get("units") as? List<Map<String, Any>> ?: emptyList()

                    val parsedUnits = units.map { unitMap ->
                        UnitMcq(
                            unit = unitMap["unit"] as? String ?: "",
                            title = unitMap["title"] as? String ?: "",
                            mcqs = unitMap["mcqs"] as? String ?: ""
                        )
                    }

                    HistoryItem(
                        id = doc.id,   // ✅ VERY IMPORTANT
                        title = doc.getString("title") ?: "",
                        timestamp = doc.getLong("timestamp") ?: 0L,
                        units = parsedUnits
                    )
                }

                onResult(list)
            }
    }


    fun getMcqById(
        uid: String,
        docId: String,
        onResult: (HistoryItem?) -> Unit
    ) {
        firestore.collection("users")
            .document(uid)
            .collection("history")
            .document(docId)
            .get()
            .addOnSuccessListener { doc ->

                if (!doc.exists()) {
                    onResult(null)
                    return@addOnSuccessListener
                }

                val unitsRaw = doc.get("units") as? List<Map<String, Any>> ?: emptyList()

                val parsedUnits = unitsRaw.map {
                    UnitMcq(
                        unit = it["unit"] as? String ?: "",
                        title = it["title"] as? String ?: "",
                        mcqs = it["mcqs"] as? String ?: ""
                    )
                }

                onResult(
                    HistoryItem(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        timestamp = doc.getLong("timestamp") ?: 0L,
                        units = parsedUnits
                    )
                )
            }
    }
}