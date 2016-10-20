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
 * Created by Anton Prio on 9/1/2016.
 */
public class RekapPemasukanLengkap extends RecyclerView.Adapter<RekapPemasukanLengkap.MyHolder> {
    private List<PemasukanModel> list;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rekap_pemasukan_lengkap_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PemasukanModel model = list.get(position);
        String jumlah = PengeluaranService.formatedString(model.getJumlahPemasukan());
        holder.rekapNama.setText(model.getNamaPemasukan());
        holder.rekapJumlah.setText(jumlah);
        holder.rekapTanggal.setText(model.getTanggalPemasukan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView rekapJumlah, rekapTanggal, rekapNama;
        public MyHolder(View view) {
            super(view);
            rekapNama = (TextView) view.findViewById(R.id.adapter_nama_pemasukan_lengkap);
            rekapJumlah = (TextView) view.findViewById(R.id.adapter_jumlah_pemasukan_lengkap);
            rekapTanggal = (TextView) view.findViewById(R.id.adapter_tanggal_pemasukan_lengkap);
        }
    }

    public RekapPemasukanLengkap(List<PemasukanModel> list) {
        this.list = list;
    }
}
