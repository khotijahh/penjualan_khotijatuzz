package com.example.penjualan_khotijatuzz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan_khotijatuzz.Room.tb_barang
import kotlinx.android.synthetic.main.activity_barang_adapter.view.*
import kotlinx.android.synthetic.main.activity_edit.view.*
import kotlinx.android.synthetic.main.activity_main.view.*

class barangAdapter(private val barang:ArrayList<tb_barang>,private val Listener:onAdapterListener )
    :RecyclerView.Adapter<barangAdapter.BarangViewHolder>(){


    class BarangViewHolder (val view: View):RecyclerView.ViewHolder(view){

    }

    interface onAdapterListener {
        fun onClik(tbBarang: tb_barang)
        fun onUpdate(tbBarang: tb_barang)
        fun onDelete(tbBarang: tb_barang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_barang_adapter,
                parent,false)
        )
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val brng= barang[position]
        holder.view.text_nama.text = brng.nm_brg
        holder.view.text_harga.text= brng.hrg_brg.toString()
        holder.view.cvBarang.setOnClickListener{
            Listener.onClik(brng)
        }
        holder.view.ic_edit.setOnClickListener{
            Listener.onUpdate(brng)
        }
        holder.view.ic_delete.setOnClickListener {
            Listener.onDelete(brng)
        }
    }

    override fun getItemCount()= barang.size

    fun setData(list: List<tb_barang>){
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }
}

