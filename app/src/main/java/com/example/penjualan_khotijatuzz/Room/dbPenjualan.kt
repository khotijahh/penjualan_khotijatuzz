package com.example.penjualan_khotijatuzz.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [tb_barang::class], version = 1)

 abstract class dbPenjualan :RoomDatabase(){
     abstract fun tbBarangDao():tbBarangDAO

     companion object{
         @Volatile private var instance:dbPenjualan?= null
         private val LOCK = Any()

         operator fun invoke(context: Context)= instance?: synchronized(LOCK){
             instance?:buildDatabase(context).also{
                 instance = it
             }
         }
         private fun buildDatabase(context: Context)=Room.databaseBuilder(context.applicationContext,
         dbPenjualan::class.java,
         "note12345.db"
         ).build()
     }
 }