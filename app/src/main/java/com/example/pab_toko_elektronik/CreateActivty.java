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
    EditText namabarang, kodebarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activty);
        dataHelper = new DataHelper(this);
        namabarang = findViewById(R.id.namabarang);
        kodebarang = findViewById(R.id.kodebarang);
        btnsimpan = findViewById(R.id.btnsimpan);
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("INSERT INTO barang(namaBarang, kodeBarang) values('" +
                        namabarang.getText().toString() + "', '" +
                        kodebarang.getText().toString() + "')");
                Toast.makeText(CreateActivty.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}