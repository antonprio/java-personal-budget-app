package com.skripsi.antonprio.pencatatanpersonal.Pengaturan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.skripsi.antonprio.pencatatanpersonal.R;

/**
 * Created by Anton Prio on 8/22/2016.
 */
public class Tentang extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang);
        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
