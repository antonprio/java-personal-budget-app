package com.skripsi.antonprio.pencatatanpersonal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanModel;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.util.List;

/**
 * Created by Anton Prio on 8/2/2016.
 */
public class HomePemasukanAdapter extends RecyclerView.Adapter<HomePemasukanAdapter.MyHolder> {
    private List<PemasukanModel> list;
    private View view;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_pemasukan_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PemasukanModel model = list.get(position);
        String jumlah = PengeluaranService.formatedString(model.getJumlahPemasukan());
        holder.namaPemasukan.setText(model.getNamaPemasukan());
        holder.katagoriPemasukan.setText(model.getKatagoriPemasukan());
        holder.jumlahPemasukan.setText(jumlah);
        holder.tanggalPemasukan.setText(model.getTanggalPemasukan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView namaPemasukan, jumlahPemasukan, katagoriPemasukan, tanggalPemasukan;
        public MyHolder(View view) {
            super(view);
            namaPemasukan = (TextView) view.findViewById(R.id.r_view_nama_pemasukan_home);
            jumlahPemasukan = (TextView) view.findViewById(R.id.r_view_jumlah_pemasukan_home);
            katagoriPemasukan = (TextView) view.findViewById(R.id.r_view_katagori_pemasukan_home);
            tanggalPemasukan = (TextView) view.findViewById(R.id.r_view_tanggal_pemasukan_home);
        }
    }

    public HomePemasukanAdapter(List<PemasukanModel> list) {
        this.list = list;
    }
}
