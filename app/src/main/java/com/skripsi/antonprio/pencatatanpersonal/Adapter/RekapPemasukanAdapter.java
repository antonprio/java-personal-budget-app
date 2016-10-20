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
public class RekapPemasukanAdapter extends RecyclerView.Adapter<RekapPemasukanAdapter.MyHolder> {
    private List<PemasukanModel> list;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rekap_pemasukan_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PemasukanModel model = list.get(position);
        String jumlah = PengeluaranService.formatedString(model.getJumlahPemasukan());
        holder.rekapJumlah.setText(jumlah);
        holder.rekapTanggal.setText(model.getTanggalPemasukan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView rekapJumlah, rekapTanggal;
        public MyHolder(View view) {
            super(view);
            rekapJumlah = (TextView) view.findViewById(R.id.rp_rekap_jumlah);
            rekapTanggal = (TextView) view.findViewById(R.id.rp_rekap_tanggal);
        }
    }

    public RekapPemasukanAdapter(List<PemasukanModel> list) {
        this.list = list;
    }
}
