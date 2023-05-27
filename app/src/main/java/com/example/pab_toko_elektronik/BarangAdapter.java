package com.example.pab_toko_elektronik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BarangAdapter extends BaseAdapter {
    private List<Barang> barangList;

    private Barang[] brg;
    private Context context;
    private LayoutInflater inflater;

    public BarangAdapter(Context ctx, List<Barang> barangList){
        this.barangList = barangList;
        this.context = ctx;
        inflater = (LayoutInflater.from(ctx));
    }

    @Override
    public int getCount() {
        return barangList.size();
    }

    @Override
    public Object getItem(int i) {

        return barangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.barang_item, null);
        TextView detailNamaPembeli = (TextView) view.findViewById(R.id.detailNamaPembeli);
        TextView detailNamaBarang = (TextView) view.findViewById(R.id.detailNamaBarang);
        TextView detailStokBarang = (TextView) view.findViewById(R.id.detailStokBarang);
        TextView detailTotal = (TextView) view.findViewById(R.id.detailTotal);

        detailNamaPembeli.setText(barangList.get(i).getNamaPembeli());
        detailNamaBarang.setText(barangList.get(i).getNamaBarang());
        detailStokBarang.setText("" + barangList.get(i).getJumlahBeli());
        detailTotal.setText("" + barangList.get(i).getTotalHarga());

        return view;
    }
}
