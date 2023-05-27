package com.example.pab_toko_elektronik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {
    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    DataHelper dataHelper;
    Button myBarang;
    public static CustomerActivity ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        ca = this;
        dataHelper = new DataHelper(this);
        RefreshList();

        myBarang = findViewById(R.id.myBarang);
        myBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent show = new Intent(CustomerActivity.this, BarangActivity.class);
                startActivity(show);
//                Log.d("DEBUG_ON", "OK");
            }

        });

        ImageView btnlogout = findViewById(R.id.logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Logout berhasil", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(CustomerActivity.this, LoginActivity.class);
                startActivity(logout);
            }
        });
    }

    public void RefreshList() {
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1);
        }
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent detail = new Intent(getApplicationContext(), DetailBarangActivity.class);
                detail.putExtra("namabarang", selection);
                startActivity(detail);
//                final CharSequence[] dialogItem = {"Lihat Barang"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerActivity.this);
//                builder.setTitle("Pilihan");

//                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        switch (item) {
//                            case 0:
//                                Intent detail = new Intent(getApplicationContext(), DetailBarangActivity.class);
//                                detail.putExtra("namabarang", selection);
//                                startActivity(detail);
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
}