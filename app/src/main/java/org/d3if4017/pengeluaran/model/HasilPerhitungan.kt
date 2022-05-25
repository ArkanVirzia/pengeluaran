package org.d3if4017.pengeluaran.model

import android.provider.Settings.Global.getString
import org.d3if4017.pengeluaran.R

data class HasilPerhitungan(
    val bmi: Float,
    val kategori: KategoriPerhitungan
)

