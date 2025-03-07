package com.example.hanyarunrun.data.model

data class IndeksPendidikanResponse(
    val id: Int,
    val kode_provinsi: Int,
    val nama_provinsi: String,
    val kode_kabupaten_kota: Int,
    val nama_kabupaten_kota: String,
    val indeks_pendidikan: Float,
    val satuan: String,
    val tahun: Int
)

data class IndeksPendidikanWrapper(
    val data: List<IndeksPendidikanResponse> // Menyesuaikan struktur JSON
)
