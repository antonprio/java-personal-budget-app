package com.skripsi.antonprio.pencatatanpersonal.Rekap;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.skripsi.antonprio.pencatatanpersonal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Anton Prio on 7/14/2016.
 */
public class RekapActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton radioPemasukan, radioPengeluaran, radioSaldo, radioAnggaran;
    TextView labelDari, labelSampai;
    EditText tglDari, tglSampai;
    Button buttonProses, buttonLengkap;
    Calendar myCalendar;
    SimpleDateFormat dateFormat;
    DatePickerDialog.OnDateSetListener date, date2;
    Bundle bundle = new Bundle();
    String pilihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rekap_activity);

        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        radioSaldo = (RadioButton) findViewById(R.id.rekap_radio_saldo);
        radioAnggaran = (RadioButton) findViewById(R.id.rekap_radio_anggaran);
        radioPemasukan = (RadioButton) findViewById(R.id.rekap_radio_pemasukan);
        radioPengeluaran = (RadioButton) findViewById(R.id.rekap_radio_pengeluaran);
        labelDari = (TextView) findViewById(R.id.rekap_label_dari);
        labelSampai = (TextView) findViewById(R.id.rekap_label_sampai);
        tglDari = (EditText) findViewById(R.id.rekap_tgl_dari);
        tglSampai = (EditText) findViewById(R.id.rekap_tgl_sampai);
        buttonProses = (Button) findViewById(R.id.rekap_btn_proses);
        buttonLengkap = (Button) findViewById(R.id.rekap_btn_lengkap);

        if(buttonProses != null) {
            buttonProses.setOnClickListener(this);
        } if(buttonLengkap != null) {
            buttonLengkap.setOnClickListener(this);
        }

        labelDari.setVisibility(View.VISIBLE);
        tglDari.setVisibility(View.VISIBLE);
        labelSampai.setVisibility(View.VISIBLE);
        tglSampai.setVisibility(View.VISIBLE);


        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tglDari.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tglSampai.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        tglDari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RekapActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }

        });
        tglSampai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RekapActivity.this, date2, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }

        });
    }

    /*
    private void rekapPeriode(int periode, int jumlahPeriode) {
        try {
            String awal = tglDari.getText().toString();
            myCalendar.setTime(dateFormat.parse(awal));
            myCalendar.add(periode, jumlahPeriode);
            Date result = new Date(myCalendar.getTimeInMillis());
            awal = dateFormat.format(result);
            tglSampai.setText(awal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    */

    public void radioOnClick(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rekap_radio_pemasukan:
                if(isChecked) {
                    pilihan = "Pemasukan";
                }
                break;
            case R.id.rekap_radio_saldo:
                if(isChecked) {
                    pilihan = "Saldo";
                    //rekapPeriode(Calendar.DATE, 7);
                }
                break;
            /*
            case R.id.rekap_radio_pengeluaran:
                if(isChecked) {
                    labelDari.setVisibility(View.VISIBLE);
                    tglDari.setVisibility(View.VISIBLE);
                    labelSampai.setVisibility(View.VISIBLE);
                    tglSampai.setVisibility(View.VISIBLE);
                    //rekapPeriode(Calendar.MONTH, 1);
                }
                break;
            */
        }
    }

    @Override
    public void onClick(View v) {
        String awal, akhir;
        if(v == findViewById(R.id.rekap_btn_proses)) {
            if(pilihan.equals("Saldo")) {
                awal = tglDari.getText().toString();
                akhir = tglSampai.getText().toString();
                bundle.putString("Param 1", awal);
                bundle.putString("Param 2", akhir);
                Intent intent = new Intent(RekapActivity.this, HasilRekap.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if(pilihan.equals("Pemasukan")) {
                awal = tglDari.getText().toString();
                akhir = tglSampai.getText().toString();
                bundle.putString("Param 1", awal);
                bundle.putString("Param 2", akhir);
                Intent intent = new Intent(RekapActivity.this, HasilPemasukanRekap.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } if(v == findViewById(R.id.rekap_btn_lengkap)) {
            if(pilihan.equals("Saldo")) {
                awal = tglDari.getText().toString();
                akhir = tglSampai.getText().toString();
                bundle.putString("Param 1", awal);
                bundle.putString("Param 2", akhir);
                Intent intent = new Intent(RekapActivity.this, HasilSaldoLengkap.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if(pilihan.equals("Pemasukan")) {
                awal = tglDari.getText().toString();
                akhir = tglSampai.getText().toString();
                bundle.putString("Param 1", awal);
                bundle.putString("Param 2", akhir);
                Intent intent = new Intent(RekapActivity.this, HasilPemasukanLengkap.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}