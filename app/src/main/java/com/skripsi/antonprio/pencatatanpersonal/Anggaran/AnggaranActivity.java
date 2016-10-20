package com.skripsi.antonprio.pencatatanpersonal.Anggaran;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skripsi.antonprio.pencatatanpersonal.Adapter.AnggaranAdapter;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.ItemDeviderDecoration;
import com.skripsi.antonprio.pencatatanpersonal.R;

/**
 * Created by Anton Prio on 8/11/2016.
 */
public class AnggaranActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    AnggaranService service = new AnggaranService(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anggaran_activity);
        recyclerView = (RecyclerView) findViewById(R.id.r_view_anggaran);
        RecyclerView.Adapter adapter = new AnggaranAdapter(service.getAll());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new ItemDeviderDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab_add_anggaran);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnggaranActivity.this, InputAnggaran.class);
                    startActivity(intent);
                }
            });
        }
    }
}
