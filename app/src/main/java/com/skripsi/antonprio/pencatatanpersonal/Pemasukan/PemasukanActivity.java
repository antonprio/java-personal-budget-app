package com.skripsi.antonprio.pencatatanpersonal.Pemasukan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Adapter.ItemDeviderDecoration;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.PemasukanAdapter;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;

public class PemasukanActivity extends AppCompatActivity {
    TextView totalPemasukan;
    RecyclerView recyclerView;
    PemasukanService service = new PemasukanService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pemasukan_activity);

        recyclerView = (RecyclerView) findViewById(R.id.r_view_pemasukan);
        RecyclerView.Adapter adapter = new PemasukanAdapter(service.getAll());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new ItemDeviderDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        totalPemasukan = (TextView) findViewById(R.id.label_total_pemasukan);
        int total = service.getTotalPemasukan();
        String totalFormatted = PengeluaranService.formatedString(total);
        totalPemasukan.setText(String.valueOf(totalFormatted).toString());

        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_pemasukan);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PemasukanActivity.this, InputPemasukan.class);
                    startActivity(intent);
                }
            });
        }
    }
}
