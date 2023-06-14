package org.d3if3142.assesment2_6706213142.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3142.assesment2_6706213142.R
import org.d3if3142.assesment2_6706213142.databinding.HitungFragmentBinding
import org.d3if3142.assesment2_6706213142.db.HasilDb
import org.d3if3142.assesment2_6706213142.model.HasilLuas

class HitungFragment: Fragment(){
    private lateinit var binding: HitungFragmentBinding
    private val viewModel: HitungViewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_ubin -> {
                findNavController().navigate(R.id.action_hitungFragment_to_ubinFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.i("HitungFragment", "onCreateView dijalankan")
        binding = HitungFragmentBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.i("HitungFragment", "onViewCreated dijalankan")
        binding.button.setOnClickListener{ luasKeramik()}
        binding.shareButton.setOnClickListener{shareData()}

        viewModel.getHasil().observe(requireActivity(),{showResult(it)})
    }

    override fun onStart() {
        super.onStart()
        Log.i("HitungFragmnet", "onStart dijalankan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("HitungFragment", "onResume dijalankan")
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template,
        binding.angkaTextView.text)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if(shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun luasKeramik() {
        val sisi = binding.inputanJumlahSisi.text.toString()
        if(TextUtils.isEmpty(sisi)) {
            Toast.makeText(context, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }

        //val inputan1 = binding.inputanJumlahSisi.text.toString().toFloat()
        //val hasilKali = inputan1 * inputan1
        //binding.angkaTextView.text = "$hasilKali m"

        viewModel.hitungSisi(
            sisi.toFloat()
        )
    }

    private fun showResult(result: HasilLuas?){
        if(result == null) return
        binding.angkaTextView.text = getString(R.string.hasil_x, result.sisi)
    }
}