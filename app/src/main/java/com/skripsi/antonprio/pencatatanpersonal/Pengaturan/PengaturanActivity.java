package com.skripsi.antonprio.pencatatanpersonal.Pengaturan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skripsi.antonprio.pencatatanpersonal.Home.Home;
import com.skripsi.antonprio.pencatatanpersonal.R;

/**
 * Created by Anton Prio on 8/21/2016.
 */
public class PengaturanActivity extends AppCompatActivity implements View.OnClickListener {
    TextView hapusTransaksi, labelPassword, labelKonfirm;
    EditText newPassword, confirmPassword;
    CheckBox passwordCheckbox;
    Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengaturan_activity);
        hapusTransaksi = (TextView) findViewById(R.id.hapus_transaksi);
        labelPassword = (TextView) findViewById(R.id.label_new_password);
        labelKonfirm = (TextView) findViewById(R.id.label_konfirm);
        newPassword = (EditText) findViewById(R.id.input_password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        passwordCheckbox =(CheckBox) findViewById(R.id.password_checkbox);
        simpan = (Button) findViewById(R.id.simpan_password);
        if(simpan != null){
            simpan.setOnClickListener(this);
        }


        labelPassword.setEnabled(false);
        labelKonfirm.setEnabled(false);
        newPassword.setEnabled(false);
        confirmPassword.setEnabled(false);

        hapusTransaksi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dialogBuilder();
                return false;
            }
        });
        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void dialogBuilder() {
        new AlertDialog.Builder(this)
                .setMessage("Anda Yakin Ingin Menghapus Semua Transaksi?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PengaturanService service = new PengaturanService(getBaseContext());
                        service.deleteAllTables();
                        Intent intent = new Intent(PengaturanActivity.this, Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Exit", true);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Tidak", null).show();
    }

    public void checkBox(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.password_checkbox:
                if(checked) {
                    labelPassword.setEnabled(true);
                    labelKonfirm.setEnabled(true);
                    newPassword.setEnabled(true);
                    confirmPassword.setEnabled(true);
                } else {
                    labelPassword.setEnabled(false);
                    labelKonfirm.setEnabled(false);
                    newPassword.setEnabled(false);
                    confirmPassword.setEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v == simpan) {
            PengaturanModel model = new PengaturanModel();
            model.setPassword(newPassword.getText().toString());
            PengaturanService service = new PengaturanService(this);
            String p = newPassword.getText().toString();
            String c = confirmPassword.getText().toString();
            if(p.equals(c)) {
                service.tambahPassword(model);
                Toast.makeText(this, "Password Ditambah", Toast.LENGTH_SHORT).show();
                newPassword.setText("");
                confirmPassword.setText("");
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("Field Tidak Sama").setPositiveButton("OK", null).show();
            }
        }
    }
}
