package com.example.penjualan_khotijatuzz

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan_khotijatuzz.Room.Constant
import com.example.penjualan_khotijatuzz.Room.dbPenjualan
import com.example.penjualan_khotijatuzz.Room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { dbPenjualan(this) }
    lateinit var BarangAdapter: barangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setUpRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
        fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val Barang = db.tbBarangDao().gettbBarang()
            Log.d("MainActivity","dbResponce :$Barang")
            withContext(Dispatchers.Main) {
                BarangAdapter.setData(Barang)
            }
        }
    }
    fun intentEdit(tbbrngId:Int,intentType:Int){
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_id",tbbrngId)
                .putExtra("intent_type",intentType)
        )
    }

    private fun halEdit(){
        BtnInput.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    fun setUpRecyclerView(){
        BarangAdapter = barangAdapter(arrayListOf(),object :
        barangAdapter.onAdapterListener{
            override fun onClik(tbBarang: tb_barang) {
           intentEdit(tbBarang.id_brg,Constant.TYPE_READ)
            }

            override fun onUpdate(tbBarang: tb_barang) {
                intentEdit(tbBarang.id_brg,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBarang: tb_barang) {
                hapusBarang(tbBarang)
            }
        })
        //id recyclerView
        List_Note.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter=BarangAdapter
        }
    }
    private fun hapusBarang(tbBarang: tb_barang){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Delete confirmation")
            setMessage("Sure Delete ${tbBarang.id_brg}")
            setNegativeButton("cencel"){dialogInterface,i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Delete"){dialogInterface,i->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbBarangDao().deletetbBarang(tbBarang)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}