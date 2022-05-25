package org.d3if4017.pengeluaran.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if4017.pengeluaran.R
import org.d3if4017.pengeluaran.databinding.AboutFragmentBinding

class AboutFragment : Fragment (){

    private lateinit var binding : AboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.about_fragment, container,false)
    }
}