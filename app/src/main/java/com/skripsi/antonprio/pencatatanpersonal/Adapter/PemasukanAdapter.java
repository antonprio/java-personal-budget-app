package com.skripsi.antonprio.pencatatanpersonal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanModel;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.util.List;

/**
 * Created by Anton Prio on 7/14/2016.
 */
public class PemasukanAdapter extends RecyclerView.Adapter<PemasukanAdapter.MyHolder> {
    private List<PemasukanModel> list;
    private View view;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PemasukanModel model);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView idPemasukan, namaPemasukan, tanggalPemasukan, katagoriPemasukan,
                jumlahPemasukan, waktuPemasukan;
        public MyHolder(View view) {
            super(view);
            idPemasukan = (TextView) view.findViewById(R.id.r_view_id_pemasukan);
            namaPemasukan = (TextView) view.findViewById(R.id.r_view_nama_pemasukan);
            tanggalPemasukan = (TextView) view.findViewById(R.id.r_view_tanggal_pemasukan);
            waktuPemasukan = (TextView) view.findViewById(R.id.r_view_waktu_pemasukan);
            katagoriPemasukan = (TextView) view.findViewById(R.id.r_view_katagori_pemasukan);
            jumlahPemasukan = (TextView) view.findViewById(R.id.r_view_jumlah_pemasukan);
        }
    }

    public PemasukanAdapter(List<PemasukanModel> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.r_view_pemasukan_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PemasukanModel model = list.get(position);
        String jumlah = PengeluaranService.formatedString(model.getJumlahPemasukan());
        holder.idPemasukan.setText("# "+model.getIdPemasukan());
        holder.namaPemasukan.setText(model.getNamaPemasukan());
        holder.tanggalPemasukan.setText(model.getTanggalPemasukan());
        holder.waktuPemasukan.setText(model.getWaktuPemasukan());
        holder.jumlahPemasukan.setText(jumlah);
        holder.katagoriPemasukan.setText(model.getKatagoriPemasukan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
