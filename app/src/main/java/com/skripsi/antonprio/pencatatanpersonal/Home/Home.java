package com.skripsi.antonprio.pencatatanpersonal.Home;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.HomePemasukanAdapter;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.HomePengeluaranAdapter;
import com.skripsi.antonprio.pencatatanpersonal.Adapter.ItemDeviderDecoration;
import com.skripsi.antonprio.pencatatanpersonal.Anggaran.AnggaranActivity;
import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanActivity;
import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanService;
import com.skripsi.antonprio.pencatatanpersonal.Pengaturan.PengaturanActivity;
import com.skripsi.antonprio.pencatatanpersonal.Pengaturan.Tentang;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;
import com.skripsi.antonprio.pencatatanpersonal.R;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranActivity;
import com.skripsi.antonprio.pencatatanpersonal.Rekap.RekapActivity;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView saldo, totalPemasukan, totalPengeluaran, aktivitas, labelPemasukan, labelPengeluaran;
    RecyclerView recyclerViewPemasukan, recyclerViewPengeluaran;
    HomeService service;
    PemasukanService pemasukanService;
    PengeluaranService pengeluaranService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saldo = (TextView) findViewById(R.id.text_saldo);
        totalPemasukan = (TextView) findViewById(R.id.text_total_pemasukan);
        totalPengeluaran = (TextView) findViewById(R.id.text_total_pengeluaran);
        aktivitas = (TextView) findViewById(R.id.label_aktivitas);
        labelPemasukan = (TextView) findViewById(R.id.label_total_pemasukan);
        labelPengeluaran = (TextView) findViewById(R.id.label_total_pengeluaran);
        service = new HomeService(this);
        pemasukanService = new PemasukanService(this);
        pengeluaranService = new PengeluaranService(this);

        recyclerViewPemasukan = (RecyclerView)  findViewById(R.id.r_view_home_pemasukan);
        RecyclerView.Adapter adapter = new HomePemasukanAdapter(service.getPemasukan());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPemasukan.addItemDecoration(new ItemDeviderDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPemasukan.setLayoutManager(layoutManager);
        recyclerViewPemasukan.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPemasukan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(adapter.getItemCount() == 0) {
            aktivitas.setText("Tidak Ada Aktivitas");
            labelPemasukan.setVisibility(View.GONE);
            totalPemasukan.setVisibility(View.GONE);
            labelPengeluaran.setVisibility(View.GONE);
            totalPengeluaran.setVisibility(View.GONE);
        }

        recyclerViewPengeluaran = (RecyclerView) findViewById(R.id.r_view_home_pengeluaran);
        RecyclerView.Adapter adapter2 = new HomePengeluaranAdapter(service.getPengeluaran());
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerViewPengeluaran.addItemDecoration(new ItemDeviderDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPengeluaran.setLayoutManager(layoutManager2);
        recyclerViewPengeluaran.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPengeluaran.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        int totalSaldo = service.getTotalSaldo();
        String formatted = PengeluaranService.formatedString(totalSaldo);
        saldo.setText(String.valueOf("RP. "+formatted));

        int totalSaldoPengeluaran = pengeluaranService.getTotalPengeluaran();
        String pengeluaranFormatted = PengeluaranService.formatedString(totalSaldoPengeluaran);
        totalPengeluaran.setText(String.valueOf("Rp. "+pengeluaranFormatted));

        int totalSaldoPemasukan = pemasukanService.getTotalPemasukan();
        String pemasukanFormatted = PengeluaranService.formatedString(totalSaldoPemasukan);
        totalPemasukan.setText(String.valueOf("Rp. "+pemasukanFormatted));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Anda Yakin Ingin Keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getIntent().getBooleanExtra("Exit", false)) {
                            System.exit(0);
                        } else {
                            finish();
                        }
                    }
                }).setNegativeButton("Tidak", null).show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pemasukan) {
            Intent intent = new Intent(Home.this, PemasukanActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pengeluaran) {
            Intent intent = new Intent(Home.this, PengeluaranActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_anggaran) {
            Intent intent = new Intent(Home.this, AnggaranActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_rekap) {
            Intent intent = new Intent(Home.this, RekapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tentang) {
            Intent intent = new Intent(Home.this, Tentang.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(Home.this, PengaturanActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
