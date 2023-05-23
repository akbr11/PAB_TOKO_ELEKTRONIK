package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivty extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnsimpan;
    EditText namabarang, kodebarang, stockbarang, hargabarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activty);
        dataHelper = new DataHelper(this);
        namabarang = findViewById(R.id.namabarang);
        kodebarang = findViewById(R.id.kodebarang);
        stockbarang = findViewById(R.id.stockbarang);
        hargabarang = findViewById(R.id.hargabarang);
        btnsimpan = findViewById(R.id.btnsimpan);
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaBarang = namabarang.getText().toString();
                String kodeBarang = kodebarang.getText().toString();
                String stockBarang = stockbarang.getText().toString();
                String hargaBarang = hargabarang.getText().toString();

                if (namaBarang.equals("")) {
                    namabarang.setError("Nama barang harus diisi!");
                } else if(kodeBarang.equals("")){
                    kodebarang.setError("Kode barang harus diisi!");
                } else if(stockBarang.equals("")){
                    stockbarang.setError("Stock barang harus diisi!");
                } else if(hargaBarang.equals("")){
                    hargabarang.setError("Harga barang harus diisi!");
                } else {
                    SQLiteDatabase db = dataHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO barang(namaBarang, kodeBarang, stokBarang, hargaBarang) values('" +
                            namabarang.getText().toString() + "', '" +
                            kodebarang.getText().toString() + "', '" +
                            stockbarang.getText().toString() + "', '" +
                            hargabarang.getText().toString() + "')");
                    Toast.makeText(CreateActivty.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    MainActivity.ma.RefreshList();
                    finish();
                }
            }
        });
    }
}