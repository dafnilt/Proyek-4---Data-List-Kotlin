package com.example.hanyarunrun.data.network

import com.example.hanyarunrun.data.model.RataRataLamaSekolahResponse
import com.example.hanyarunrun.data.model.RataRataLamaSekolahWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface JabarApi {
//    @GET("od_17046_rata_rata_lama_sekolah_berdasarkan_kabupatenkota")
//    suspend fun getRataRataLamaSekolah(): List<RataRataLamaSekolahResponse>




    @GET("od_17046_rata_rata_lama_sekolah_berdasarkan_kabupatenkota/{id}")
    suspend fun getRataRataLamaSekolahById(@Path("id") id: Int): RataRataLamaSekolahResponse

    @GET("od_17046_rata_rata_lama_sekolah_berdasarkan_kabupatenkota")
    suspend fun getRataRataLamaSekolah(): RataRataLamaSekolahWrapper
}