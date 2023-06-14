package org.d3if3142.assesment2_6706213142.ui.kategoriUbin

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if3142.assesment2_6706213142.R
import org.d3if3142.assesment2_6706213142.databinding.FragmentKategoriUbinBinding
import org.d3if3142.assesment2_6706213142.network.ApiStatus

class UbinFragment : Fragment(){
    private val viewModel: UbinViewModel by lazy {
        ViewModelProvider(this)[UbinViewModel::class.java]
    }

    private lateinit var binding: FragmentKategoriUbinBinding
    private lateinit var myAdapter: UbinAdapter
    private var isLinearLayout = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKategoriUbinBinding.inflate(layoutInflater, container, false)
        myAdapter = UbinAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL)
            )

            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }
}