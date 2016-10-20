package com.skripsi.antonprio.pencatatanpersonal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;
import com.skripsi.antonprio.pencatatanpersonal.Rekap.RekapModel;

import java.util.List;

/**
 * Created by Anton Prio on 9/1/2016.
 */
public class RekapSaldoLengkap extends RecyclerView.Adapter<RekapSaldoLengkap.MyHolder> {
    private List<RekapModel> list;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rekap_saldo_lengkap_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        RekapModel model = list.get(position);
        String jumlah = PengeluaranService.formatedString(model.getRekapJumlah());
        holder.rekapNama.setText(model.getRekapNama());
        holder.rekapJumlah.setText(jumlah);
        holder.rekapTanggal.setText(model.getRekapTanggal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView rekapJumlah, rekapTanggal, rekapNama;
        public MyHolder(View view) {
            super(view);
            rekapNama = (TextView) view.findViewById(R.id.adapter_nama_saldo_lengkap);
            rekapJumlah = (TextView) view.findViewById(R.id.adapter_jumlah_saldo_lengkap);
            rekapTanggal = (TextView) view.findViewById(R.id.adapter_tanggal_saldo_lengkap);
        }
    }

    public RekapSaldoLengkap(List<RekapModel> list) {
        this.list = list;
    }
}
