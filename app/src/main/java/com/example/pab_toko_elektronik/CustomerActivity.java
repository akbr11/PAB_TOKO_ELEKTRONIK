package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerActivity extends AppCompatActivity {
    LinearLayout allProduk;
    TextView barang;
    Button lihat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        allProduk = findViewById(R.id.allProduct);
        barang = findViewById(R.id.barang);
        lihat = findViewById(R.id.lihat);
    }
}