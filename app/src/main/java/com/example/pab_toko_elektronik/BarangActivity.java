package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BarangActivity extends AppCompatActivity {
    List<Barang> barangs = new ArrayList<>();
    protected Cursor cursor;
    ListView list;
    DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);
        list = (ListView) findViewById(R.id.listView);

        dataHelper = new DataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pembelian", null);
        Log.d(DataHelper.DEBUG, String.valueOf(cursor.getCount()));
        while (cursor.moveToNext()){
            Barang bb = new Barang();
            bb.setNamaPembeli(cursor.getString(0));
            bb.setNamaBarang(cursor.getString(1));
            bb.setJumlahBeli(cursor.getInt(2));
            bb.setTotalHarga(cursor.getInt(3));
            barangs.add(bb);
        }

        BarangAdapter adapter = new BarangAdapter(getApplicationContext(), barangs);

        list.setAdapter(adapter);
    }
}