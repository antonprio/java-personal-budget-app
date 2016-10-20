package com.skripsi.antonprio.pencatatanpersonal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Anggaran.AnggaranModel;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.util.List;

/**
 * Created by Anton Prio on 8/18/2016.
 */
public class AnggaranAdapter extends RecyclerView.Adapter<AnggaranAdapter.MyHolder> {
    private List<AnggaranModel> list;

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView idAnggaran, namaAnggaran, jumlahAnggaran, tanggalAnggaran, katagoriAnggaran
                ,statusAnggaran;
        public MyHolder(View view) {
            super(view);
            idAnggaran = (TextView) view.findViewById(R.id.adapter_id_anggaran);
            namaAnggaran = (TextView) view.findViewById(R.id.adapter_nama_anggaran);
            jumlahAnggaran = (TextView) view.findViewById(R.id.adapter_jumlah_anggaran);
            tanggalAnggaran = (TextView) view.findViewById(R.id.adapter_tanggal_anggaran);
            katagoriAnggaran = (TextView) view.findViewById(R.id.adapter_katagori_anggaran);
            statusAnggaran = (TextView) view.findViewById(R.id.adapter_status_anggaran);
        }
    }

    public AnggaranAdapter(List<AnggaranModel> list) {
        this.list = list;
    }

    @Override
    public AnggaranAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.anggaran_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnggaranAdapter.MyHolder holder, int position) {
        AnggaranModel model = list.get(position);
        String status;
        if(!model.isStatusAnggaran()) {
            status = "Status: Belum Terpenuhi";
        } else {
            status = "Status: Terpenuhi";
        }
        String jumlah = PengeluaranService.formatedString(model.getJumlahAnggaran());
        holder.idAnggaran.setText("# "+ model.getIdAnggaran());
        holder.namaAnggaran.setText(model.getNamaAnggaran());
        holder.jumlahAnggaran.setText(jumlah);
        holder.tanggalAnggaran.setText(model.getTanggalAnggaran());
        holder.katagoriAnggaran.setText(model.getKatagoriAnggaran());
        holder.statusAnggaran.setText(status);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
