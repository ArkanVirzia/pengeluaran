package org.d3if4017.pengeluaran

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if4017.pengeluaran.model.HasilPerhitungan
import org.d3if4017.pengeluaran.model.KategoriPerhitungan

class MainViewModel : ViewModel() {

    private val HasilPerhitungan = MutableLiveData<HasilPerhitungan?>()
    fun pengeluaranUang(Pemasukan: Float, Pengeluaran: Float,  isMale: Boolean){
        val bmi = Pemasukan
        val jumlah = Pengeluaran * 30
        val hasil = jumlah / 2
        val kategori = getKategori(bmi, isMale, jumlah, hasil)
        HasilPerhitungan.value = HasilPerhitungan(bmi, kategori)
    }
    private fun getKategori(
        bmi: Float,
        isMale: Boolean,
        jumlah: Float,
        hasil: Float
    ): KategoriPerhitungan {
        val kategori = if (isMale) {
            when {
                jumlah >= bmi -> KategoriPerhitungan.BOROS
                jumlah < bmi -> KategoriPerhitungan.HEMAT
                else -> KategoriPerhitungan.IDEAL
            }
        } else {
            when {
                bmi >= hasil -> KategoriPerhitungan.HEMAT
                bmi < hasil -> KategoriPerhitungan.BOROS
                else -> KategoriPerhitungan.IDEAL
            }
        }
        return kategori
    }
    fun getHasilPerhitungan(): LiveData<HasilPerhitungan?> = HasilPerhitungan
}