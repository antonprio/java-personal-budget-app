package com.skripsi.antonprio.pencatatanpersonal.Rekap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Adapter.ItemDeviderDecoration;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.RekapAdapter;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.RekapSaldoLengkap;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;

/**
 * Created by Anton Prio on 9/1/2016.
 */
public class HasilSaldoLengkap extends AppCompatActivity {
    RecyclerView recyclerView;
    RekapService service = new RekapService(this);
    TextView labelJudulDari, labelJudulSampai, labelTotal;
    String dari, sampai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rekap_saldo_lengkap);
        labelJudulDari = (TextView) findViewById(R.id.sl_label_judul_rekap);
        labelJudulSampai = (TextView) findViewById(R.id.sl_label_judul_rekap2);
        labelTotal = (TextView) findViewById(R.id.sl_label_total_rekap);
        Bundle bundle = this.getIntent().getExtras();
        dari = bundle.getString("Param 1");
        sampai = bundle.getString("Param 2");
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int totalRekap = service.getTotalRekap(dari, sampai);

        labelJudulDari.setText("Hasil Rekap Saldo Dari "+dari);
        labelJudulSampai.setText("Sampai Dengan "+sampai);
        labelTotal.setText("Total Transaksi Rp. "+ PengeluaranService.formatedString(totalRekap));

        recyclerView = (RecyclerView) findViewById(R.id.sl_rview_hasil_rekap);
        RecyclerView.Adapter adapter = new RekapSaldoLengkap(service.getRekapLengkap(dari, sampai));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new ItemDeviderDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
