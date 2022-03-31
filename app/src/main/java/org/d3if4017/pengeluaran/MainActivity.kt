package org.d3if4017.pengeluaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if4017.pengeluaran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { pengeluaranUang() }

    }
    private fun pengeluaranUang() {
        val Pemasukan = binding.pemasukanuangInp.text.toString()
        if (TextUtils.isEmpty(Pemasukan)) {
            Toast.makeText(this, R.string.pemasukan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val  Pengeluaran= binding.pengeluaranuangInp.text.toString()
        if (TextUtils.isEmpty(Pengeluaran)) {
            Toast.makeText(this, R.string.pengeluaran_invalid, Toast.LENGTH_LONG).show()
            return
        }


        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.matauang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val isMale = selectedId == R.id.dollarRadioButton
        val bmi = Pemasukan.toFloat()
        val jumlah = Pengeluaran.toFloat() * 30
        val hasil = jumlah / 2
        val kategori = getKategori(bmi, isMale, jumlah, hasil)


        binding.pengeluaranTextView.text = getString(R.string.bmi_x, jumlah)
        binding.kategoriTextView.text = getString(R.string.kategori_x, kategori)
    }
    private fun getKategori(bmi: Float, isMale: Boolean, jumlah: Float, hasil: Float): String {
        val stringRes = if (isMale) {
            when {
                jumlah >= bmi -> R.string.Boros
                jumlah < bmi -> R.string.Hemat
                else -> R.string.ideal
            }
        }
        else {
            when {
                bmi >= hasil -> R.string.Hemat
                bmi < hasil -> R.string.Boros
                else -> R.string.ideal
            }
        }
        return getString(stringRes)
    }
}