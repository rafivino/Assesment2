package org.d3if3142.assesment2_6706213142.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if3142.assesment2_6706213142.R
import org.d3if3142.assesment2_6706213142.databinding.HitungFragmentBinding
import org.d3if3142.assesment2_6706213142.model.HasilLuas

class HitungFragment: Fragment(){
    private lateinit var binding: HitungFragmentBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = HitungFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener{ luasKeramik()}
                viewModel.getHasil().observe(requireActivity(),{showResult(it)})


    }

    private fun luasKeramik() {
        val input = binding.inputanJumlahSisi.text.toString()
        if(TextUtils.isEmpty(input)) {
            Toast.makeText(context, "Masukkan Sisinya", Toast.LENGTH_SHORT).show()
            return
        }

        val inputan1 = binding.inputanJumlahSisi.text.toString().toFloat()

        val hasilKali = inputan1 * inputan1

        binding.angkaTextView.text = "$hasilKali m"

        viewModel.luasKeramik(
            input.toFloat()
        )
    }

    private fun showResult(result: HasilLuas?){
        if(result == null) return
        binding.angkaTextView.text = getString(R.string.hasil_x, result.hasil)
    }
}