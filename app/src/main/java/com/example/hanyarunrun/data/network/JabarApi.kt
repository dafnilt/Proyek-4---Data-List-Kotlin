package com.example.hanyarunrun.data.network

import com.example.hanyarunrun.data.model.IndeksPendidikanResponse
import com.example.hanyarunrun.data.model.IndeksPendidikanWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface JabarApi {

    @GET("od_15045_indeks_pendidikan_berdasarkan_kabupatenkota/{id}")
    suspend fun getIndeksPendidikanById(@Path("id") id: Int): IndeksPendidikanResponse

    @GET("od_15045_indeks_pendidikan_berdasarkan_kabupatenkota")
    suspend fun getIndeksPendidikan(): IndeksPendidikanWrapper
}