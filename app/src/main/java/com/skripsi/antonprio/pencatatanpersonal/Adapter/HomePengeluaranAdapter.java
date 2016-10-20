package com.skripsi.antonprio.pencatatanpersonal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranModel;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.util.List;

/**
 * Created by Anton Prio on 8/3/2016.
 */
public class HomePengeluaranAdapter extends RecyclerView.Adapter<HomePengeluaranAdapter.MyHolder> {
    private List<PengeluaranModel> list;

    public HomePengeluaranAdapter(List<PengeluaranModel> list) {
        this.list = list;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView namaPengeluaran, jumlahPengeluaran, tanggalPengeluaran, katagoriPengeluaran;
        public MyHolder(View view) {
            super(view);
            namaPengeluaran = (TextView) view.findViewById(R.id.r_view_nama_pengeluaran_home);
            jumlahPengeluaran = (TextView) view.findViewById(R.id.r_view_jumlah_pengeluaran_home);
            tanggalPengeluaran = (TextView) view.findViewById(R.id.r_view_tanggal_pengeluaran_home);
            katagoriPengeluaran = (TextView) view.findViewById(R.id.r_view_katagori_pengeluaran_home);
        }
    }

    @Override
    public HomePengeluaranAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_pengeluaran_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PengeluaranModel model = list.get(position);
        String jumlah = PengeluaranService.formatedString(model.getJumlahPengeluaran());
        holder.namaPengeluaran.setText(model.getNamaPengeluaran());
        holder.jumlahPengeluaran.setText(jumlah);
        holder.tanggalPengeluaran.setText(model.getTanggalPengeluaran());
        holder.katagoriPengeluaran.setText(model.getKatagoriPengeluaran());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
