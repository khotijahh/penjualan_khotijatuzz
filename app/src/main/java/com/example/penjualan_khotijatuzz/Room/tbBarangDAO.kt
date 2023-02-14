package com.example.penjualan_khotijatuzz.Room

import androidx.room.*

@Dao
interface tbBarangDAO {
    @Insert
    fun addtbBarang(tbBarang: tb_barang)
    @Update
    fun updatetbBarang(tbBarang: tb_barang)
    @Delete
    fun deletetbBarang(tbBarang: tb_barang)
    @Query("SELECT * FROM tb_barang")
    fun gettbBarang():List<tb_barang>
    @Query("select*from tb_barang WHERE id_brg=:tbBrng")
    fun tampilBarang(tbBrng :Int):List<tb_barang>
}