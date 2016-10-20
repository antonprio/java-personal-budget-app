package com.skripsi.antonprio.pencatatanpersonal.Pengeluaran;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.Adapter.ItemDeviderDecoration;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.PengeluaranAdapter;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Prio on 6/22/2016.
 */
public class PengeluaranActivity extends AppCompatActivity {
    TextView totalPengeluaran;
    PengeluaranService service = new PengeluaranService(this);
    private List<PengeluaranModel> list = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengeluaran_activity);

        totalPengeluaran = (TextView) findViewById(R.id.label_total_pengeluaran);
        recyclerView = (RecyclerView) findViewById(R.id.r_view_pengeluaran);

        RecyclerView.Adapter adapter = new PengeluaranAdapter(service.getAll());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new ItemDeviderDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        int total = service.getTotalPengeluaran();
        String totalFormatted = PengeluaranService.formatedString(total);
        totalPengeluaran.setText(String.valueOf(totalFormatted).toString());
        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            onResume();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_pengeluaran);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PengeluaranActivity.this, InputPengeluaran.class);
                    startActivity(intent);
                }
            });
        }
    }
}