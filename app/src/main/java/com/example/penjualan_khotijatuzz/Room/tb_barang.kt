package com.example.penjualan_khotijatuzz.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class tb_barang(
    @PrimaryKey
 val id_brg:Int,
 val nm_brg:String,
 val hrg_brg:Int,
 val stok:Int
)

