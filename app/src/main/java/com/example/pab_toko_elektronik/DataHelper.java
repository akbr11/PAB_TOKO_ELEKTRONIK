package com.example.pab_toko_elektronik;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "toko_elektronik.db";
    private static final int DATABASE_VERSION = 2;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    private static final String tabel_barang = "create table barang(" +
//            "idBarang integer primary key autoincrement, " +
//            "namaBarang text null, " +
//            "kodeBarang text null, " +
//            "stokBarang integer null, " +
//            "hargaBarang integer null);";
//
//    private static final String tabel_pembelian = "create table pembelian(" +
//            "idPembelian integer primary key autoincrement, " +
//            "namaPembeli text null, " +
//            "namaBarang text null, " +
//            "jumlahBeli integer null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String barangSql = "create table barang(namaBarang text , kodeBarang text ,stokBarang integer, hargaBarang integer);";
        String pembelianSql = "create table pembelian(namaPembeli text , namaBarang text ,jumlahBeli integer);";
        db.execSQL(barangSql);
        db.execSQL(pembelianSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int db1, int db2) {

    }
}
