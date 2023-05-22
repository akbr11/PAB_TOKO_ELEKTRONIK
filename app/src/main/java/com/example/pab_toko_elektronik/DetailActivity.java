package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    TextView namabarang, kodebarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dataHelper = new DataHelper(this);
        namabarang = findViewById(R.id.detailNamaBarang);
        kodebarang = findViewById(R.id.detailKodeBarang);

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE namaBarang = '" +
                getIntent().getStringExtra("namabarang") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            namabarang.setText(cursor.getString(0));
            kodebarang.setText(cursor.getString(1));
        }
    }
}