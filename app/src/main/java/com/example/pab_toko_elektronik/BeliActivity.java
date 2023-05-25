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
import android.widget.TextView;
import android.widget.Toast;

public class BeliActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    EditText namaPembeli, namaBarang, jumlahBarang, priceSell;
    TextView totalHarga;
    Button btnBeliBarang, checkout;
    public static BeliActivity ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beli);
        namaPembeli = findViewById(R.id.pembeli);
        namaBarang = findViewById(R.id.namaBarang);
        priceSell = findViewById(R.id.priceSell);
        jumlahBarang = findViewById(R.id.jumlahBarang);
        totalHarga = findViewById(R.id.hargabarang);

        ba = this;
        dataHelper = new DataHelper(this);
        barang();

        checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((priceSell.getText().length()>0) && (jumlahBarang.getText().length()>0))
                {
//                    double angka1 = Double.parseDouble(priceSell.getText().toString());
//                    double angka2 = Double.parseDouble(jumlahBarang.getText().toString());
//                    double result = angka1 * angka2;
//                    totalHarga.setText(Double.toString(result));
                    int price = Integer.parseInt(priceSell.getText().toString());
                    int totalBarang = Integer.parseInt(jumlahBarang.getText().toString());
                    int total = price * totalBarang;
                    totalHarga.setText(Integer.toString(total));
                }
                else {
                    Toast toast = Toast.makeText(BeliActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btnBeliBarang = findViewById(R.id.btnbelibarang);
        btnBeliBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("INSERT INTO pembelian(namaPembeli, namaBarang, jumlahBeli) values('" +
                        namaPembeli.getText().toString() + "', '" +
                        namaBarang.getText().toString() + "', '" +
                        jumlahBarang.getText().toString() + "')");
                Toast.makeText(BeliActivity.this, "Barang berhasil dibeli! lihat barangmu di beranda.", Toast.LENGTH_SHORT).show();
                Intent customer = new Intent(BeliActivity.this, CustomerActivity.class);
                startActivity(customer);
            }
        });
    }

    public void barang() {
        Intent terimaBarang = getIntent();
        String valueBarang = terimaBarang.getStringExtra("namabarang");
        String valueHarga = terimaBarang.getStringExtra("hargabarang");
        namaBarang.setText(valueBarang);
        priceSell.setText(valueHarga);
    }
}