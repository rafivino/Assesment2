package org.d3if3142.assesment2_6706213142.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.d3if3142.assesment2_6706213142.R
import org.d3if3142.assesment2_6706213142.databinding.ItemHistoriBinding
import org.d3if3142.assesment2_6706213142.db.HasilEntity
import org.d3if3142.assesment2_6706213142.model.hitungsisi
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter: ListAdapter<HasilEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<HasilEntity>() {
                override fun areItemsTheSame(
                    oldData: HasilEntity, newData: HasilEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: HasilEntity, newData: HasilEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ): RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: HasilEntity) = with(binding) {
            val hasil = item.hitungsisi()
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            dataTextView.text = root.context.getString(R.string.hasil_x,
            hasil.sisi)
        }
    }
}