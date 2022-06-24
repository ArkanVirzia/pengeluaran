package org.d3if4017.pengeluaran

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if4017.pengeluaran.databinding.ActivityMainBinding
import org.d3if4017.pengeluaran.model.HasilPerhitungan
import org.d3if4017.pengeluaran.model.KategoriPerhitungan
import org.d3if4017.pengeluaran.util.MyTimer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myTimer: MyTimer
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    companion object {
        const val CHANNEL_ID = "updater"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { pengeluaranUang() }
        myTimer = MyTimer()
        viewModel.getHasilPerhitungan().observe(this, { showResult(it) })
        viewModel.scheduleUpdater(MainActivity().application)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = getString(R.string.channel_desc)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            manager.createNotificationChannel(channel)
        }
        fun onStart() {
            super.onStart()
            myTimer.startTimer()
            Log.i("MainActivity", "onStart dijalankan")
        }
        fun onResume() {
            super.onResume()
            Log.i("MainActivity", "onResume dijalankan")
        }
        fun onPause() {
            Log.i("MainActivity", "onPause dijalankan")
            super.onPause()
        }
        fun onStop() {
            Log.i("MainActivity", "onStop dijalankan")
            myTimer.stopTimer()
            super.onStop()
        }
        fun onDestroy() {
            Log.i("MainActivity", "onDestroy dijalankan")
            super.onDestroy()
        }

    }

    private fun pengeluaranUang() {
        val Pemasukan = binding.pemasukanuangInp.text.toString()
        if (TextUtils.isEmpty(Pemasukan)) {
            Toast.makeText(this, R.string.pemasukan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val Pengeluaran = binding.pengeluaranuangInp.text.toString()
        if (TextUtils.isEmpty(Pengeluaran)) {
            Toast.makeText(this, R.string.pengeluaran_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.matauang_invalid, Toast.LENGTH_LONG).show()
            return
        }
            viewModel.pengeluaranUang(
            Pemasukan.toFloat(),
            Pengeluaran.toFloat(),
            selectedId == R.id.dollarRadioButton
        )
    }
    private fun getKategoriLabel(kategori: KategoriPerhitungan): String {
        val stringRes = when (kategori) {
            KategoriPerhitungan.HEMAT -> R.string.Hemat
            KategoriPerhitungan.IDEAL -> R.string.ideal
            KategoriPerhitungan.BOROS -> R.string.Boros
        }
        return getString(stringRes)
    }

    private fun showResult(result: HasilPerhitungan?) {
        if (result == null) return
        binding.pengeluaranTextView.text = getString(R.string.bmi_x, result.bmi)
        binding.kategoriTextView.text = getString(R.string.kategori_x,
            getKategoriLabel(result.kategori))
    }
}