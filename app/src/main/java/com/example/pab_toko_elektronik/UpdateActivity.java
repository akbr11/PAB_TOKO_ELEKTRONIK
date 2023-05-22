package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnedit;
    EditText namabarang, kodebarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dataHelper = new DataHelper(this);
        namabarang = findViewById(R.id.namabarang);
        kodebarang = findViewById(R.id.kode);
        btnedit = findViewById(R.id.btnedit);

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE namaBarang = '" +
                getIntent().getStringExtra("updatenamabarang") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            namabarang.setText(cursor.getString(0).toString());
            kodebarang.setText(cursor.getString(1).toString());
        }
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("UPDATE barang SET namaBarang = '" +
                        namabarang.getText().toString() + "', kodeBarang = '" +
                        kodebarang.getText().toString() + "' WHERE namaBarang = '" +
                        getIntent().getStringExtra("updatenamabarang") + "'");
                Toast.makeText(UpdateActivity.this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}