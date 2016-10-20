package com.skripsi.antonprio.pencatatanpersonal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranModel;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.util.List;

/**
 * Created by Anton Prio on 6/29/2016.
 */
public class PengeluaranAdapter extends RecyclerView.Adapter<PengeluaranAdapter.MyHolder> {
    private List<PengeluaranModel> list;
    private View itemView;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PengeluaranModel model);
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView nama, katagori, jumlah, id, tanggal, waktu;
        public MyHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.r_view_id);
            nama = (TextView) view.findViewById(R.id.r_view_nama);
            tanggal = (TextView) view.findViewById(R.id.r_view_tanggal);
            waktu = (TextView) view.findViewById(R.id.r_view_waktu);
            katagori = (TextView) view.findViewById(R.id.r_view_katagori);
            jumlah = (TextView) view.findViewById(R.id.r_view_jumlah);
        }
    }

    public PengeluaranAdapter(List<PengeluaranModel> list) {
        this.list = list;
        //this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.r_view_pengeluaran_adapter, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final PengeluaranModel model = list.get(position);
        String jumlah = PengeluaranService.formatedString(model.getJumlahPengeluaran());
        holder.id.setText("# "+model.getIdPengeluaran());
        holder.nama.setText(model.getNamaPengeluaran());
        holder.tanggal.setText(model.getTanggalPengeluaran());
        holder.waktu.setText(model.getWaktuPengeluaran());
        holder.katagori.setText(model.getKatagoriPengeluaran());
        holder.jumlah.setText(jumlah);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                PengeluaranService service = new PengeluaranService(context);
                PengeluaranModel model1 = service.getById(model.getIdPengeluaran());

                Toast.makeText(context, "no id "+model1.getIdPengeluaran()+ " terpilih", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(context, InputPengeluaran.class);
                //intent.putExtra("Button Visibility", "tampil");
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
