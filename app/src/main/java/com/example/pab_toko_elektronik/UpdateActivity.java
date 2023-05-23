package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnedit;
    ImageView btnback;
    EditText namabarang, kodebarang, stockbarang, hargabarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dataHelper = new DataHelper(this);
        namabarang = findViewById(R.id.namabarang);
        kodebarang = findViewById(R.id.kodebarang);
        stockbarang = findViewById(R.id.stockbarang);
        hargabarang = findViewById(R.id.hargabarang);
        btnedit = findViewById(R.id.btnedit);

        btnback = findViewById(R.id.backBeranda);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(pindah);
            }
        });

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE namaBarang = '" +
                getIntent().getStringExtra("updatenamabarang") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            namabarang.setText(cursor.getString(1));
            kodebarang.setText(cursor.getString(2));
            stockbarang.setText(cursor.getString(3));
            hargabarang.setText(cursor.getString(4));
        }
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("UPDATE barang SET namaBarang = '" +
                        namabarang.getText().toString() + "', kodeBarang = '" +
                        kodebarang.getText().toString() + "', stokBarang = '" +
                        stockbarang.getText().toString() + "', hargaBarang = '" +
                        hargabarang.getText().toString() + "' WHERE namaBarang = '" +
                        getIntent().getStringExtra("updatenamabarang") + "'");
                Toast.makeText(UpdateActivity.this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}