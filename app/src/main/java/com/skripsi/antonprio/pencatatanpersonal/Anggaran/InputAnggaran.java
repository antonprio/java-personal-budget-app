package com.skripsi.antonprio.pencatatanpersonal.Anggaran;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.skripsi.antonprio.pencatatanpersonal.Home.Home;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Anton Prio on 8/11/2016.
 */
public class InputAnggaran extends AppCompatActivity implements View.OnClickListener {
    EditText namaAnggaran, jumlahAnggaran, tanggalAnggaran;
    Button simpan;
    DatePickerDialog.OnDateSetListener tanggal;
    Spinner katagoriAnggaran;
    String katagoriValue, prioritasAnggaran;
    boolean statusAnggaran;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_anggaran);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        namaAnggaran = (EditText) findViewById(R.id.nama_anggaran);
        jumlahAnggaran = (EditText) findViewById(R.id.jumlah_anggaran);
        tanggalAnggaran = (EditText) findViewById(R.id.tanggal_anggaran);
        katagoriAnggaran = (Spinner) findViewById(R.id.katagori_anggaran);
        simpan = (Button) findViewById(R.id.button_simpan_anggaran);
        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("Alarm"),
                PendingIntent.FLAG_NO_CREATE) == null);

        if(alarm) {
            Intent italarm = new Intent("Alarm");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, italarm, 0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60000, pendingIntent);
        }

        if(simpan != null) {
            simpan.setOnClickListener(this);
        }
        statusAnggaran = false;
        final Calendar calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.katagori_pengeluaran, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        katagoriAnggaran.setAdapter(adapter);
        katagoriAnggaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                katagoriValue = adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tanggal = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tanggalAnggaran.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        tanggalAnggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new DatePickerDialog(InputAnggaran.this, tanggal, calendar.get(Calendar.YEAR),
                       calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void radioAnggaran(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.prioritas_rendah:
                if(isChecked) {
                    prioritasAnggaran = "3";
                }
                break;
            case R.id.prioritas_sedang:
                if(isChecked) {
                    prioritasAnggaran = "2";
                }
                break;
            case R.id.prioritas_tinggi:
                if(isChecked) {
                    prioritasAnggaran = "1";
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.button_simpan_anggaran)) {
            AnggaranModel model = new AnggaranModel();
            AnggaranService service = new AnggaranService(this);
            model.setNamaAnggaran(namaAnggaran.getText().toString());
            try{
                model.setJumlahAnggaran(Integer.parseInt(jumlahAnggaran.getText().toString()));
            } catch (NumberFormatException e) {
                new AlertDialog.Builder(this).setMessage("Field Masih Kosong")
                        .setPositiveButton("OK", null).show();
                return;
            }
            model.setTanggalAnggaran(tanggalAnggaran.getText().toString());
            model.setKatagoriAnggaran(katagoriValue);
            model.setPrioritasAnggaran(prioritasAnggaran);
            model.setStatusAnggaran(statusAnggaran);
            try {
                service.insert(model);
                Toast.makeText(this, "Anggaran Ditambah", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InputAnggaran.this, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Exit", true);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
