package com.example.pab_toko_elektronik;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "toko_elektronik.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String tabel_barang = "create table barang(" +
            "idBarang integer primary key autoincrement, " +
            "namaBarang text null, " +
            "kodeBarang text null, " +
            "stokBarang integer null, " +
            "hargaBarang integer null);";
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table barang(namaBarang text null, kodeBarang text null, stokBarang integer null, hargaBarang integer null);";
        Log.d("Data", "onCreate: " + tabel_barang);
        db.execSQL(tabel_barang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {

    }
}
