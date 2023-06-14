package org.d3if3142.assesment2_6706213142.ui.kategoriUbin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3142.assesment2_6706213142.R
import org.d3if3142.assesment2_6706213142.data.Ubin
import org.d3if3142.assesment2_6706213142.databinding.ListKategoriBinding
import org.d3if3142.assesment2_6706213142.network.UbinApi

class UbinAdapter : RecyclerView.Adapter<UbinAdapter.ViewHolder>() {
    private val data = mutableListOf<Ubin>()

    fun updateData(newData: List<Ubin>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListKategoriBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(ubin: Ubin) = with(binding){
            kategoriTextview.text = ubin.jenisUbin
            penjelasanTextview.text = ubin.penjelasan
            Glide.with(imageView.context)
                .load(UbinApi.getUbinUrl(ubin.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, ubin.jenisUbin)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListKategoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}