package com.example.penjualan_khotijatuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.penjualan_khotijatuzz.Room.Constant
import com.example.penjualan_khotijatuzz.Room.dbPenjualan
import com.example.penjualan_khotijatuzz.Room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy {dbPenjualan(this)}
    private var tbbrngId:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolperintah()
        setupView()
        tbbrngId= intent.getIntExtra("intent_id",tbbrngId)
        Toast.makeText(this,tbbrngId.toString(),Toast.LENGTH_SHORT).show()
    }
    fun setupView(){
        val intentType= intent.getIntExtra("intent_type",0)
        when(intentType){
            Constant.TYPE_CREATE -> {
            }
            Constant.TYPE_READ -> {
                btnSave.visibility = View.GONE
                btnUpdate.visibility= View.GONE
                id_Brng.visibility= View.GONE
                tampilBarang()
            }
            Constant.TYPE_UPDATE -> {
                btnSave.visibility = View.GONE
                id_Brng.visibility = View.GONE
                tampilBarang()
            }
        }
    }

    fun tombolperintah(){
        btnSave.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
               db.tbBarangDao().addtbBarang(
                   tb_barang(id_Brng.text.toString().toInt(),
                   nm_Brng.text.toString(),
                   harga_Brng.text.toString().toInt(),
                   stok_brng.text.toString().toInt())
               )
                finish()
            }
        }
        btnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBarangDao().updatetbBarang(
                    tb_barang(tbbrngId,
                    nm_Brng.text.toString(),
                    harga_Brng.text.toString().toInt(),
                    stok_brng.text.toString().toInt())
                )
                finish()
            }
        }
    }

    fun tampilBarang(){
        tbbrngId= intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val barang = db.tbBarangDao().tampilBarang(tbbrngId)[0]
            val dataId:String= barang.id_brg.toString()
            val dataStok:String= barang.stok.toString()
            val dataHarga:String= barang.hrg_brg.toString()
                id_Brng.setText(dataId)
                nm_Brng.setText(barang.nm_brg)
                harga_Brng.setText(dataHarga)
                stok_brng.setText(dataStok)
        }
    }

}