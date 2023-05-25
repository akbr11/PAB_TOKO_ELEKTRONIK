package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailBarangActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    String[] daftar;
    TextView namabarang, kodebarang, stockbarang, hargabarang;
    ImageView btnback;
    Button btnbeli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);
        dataHelper = new DataHelper(this);
        namabarang = findViewById(R.id.detailNamaBarang);
        kodebarang = findViewById(R.id.detailKodeBarang);
        stockbarang = findViewById(R.id.detailStokBarang);
        hargabarang = findViewById(R.id.detailHargaBarang);
        btnback = findViewById(R.id.back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(DetailBarangActivity.this, CustomerActivity.class);
                startActivity(pindah);
            }
        });

        btnbeli = findViewById(R.id.btnbeli);
        btnbeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(DetailBarangActivity.this, BeliActivity.class);
                pindah.putExtra("namabarang", namabarang.getText().toString());
                pindah.putExtra("hargabarang", hargabarang.getText().toString());
                startActivity(pindah);
            }
        });

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE namaBarang = '" +
                getIntent().getStringExtra("namabarang") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            namabarang.setText(cursor.getString(0));
            kodebarang.setText(cursor.getString(1));
            stockbarang.setText(cursor.getString(2));
            hargabarang.setText(cursor.getString(3));
        }
    }
}