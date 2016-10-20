package com.skripsi.antonprio.pencatatanpersonal.Pemasukan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.skripsi.antonprio.pencatatanpersonal.Home.Home;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Anton Prio on 6/20/2016.
 */
public class InputPemasukan extends AppCompatActivity implements View.OnClickListener {
    private EditText namaPemasukan, tanggal, jumlahPemasukan;
    private DatePickerDialog tanggalPemasukan;
    SimpleDateFormat dateFormat, waktu;
    Spinner katagori;
    Button btnSimpan;
    String katagoriValue, waktuPemasukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pemasukan);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        waktu = new SimpleDateFormat("h:mm a");
        namaPemasukan = (EditText) findViewById(R.id.text_nama);
        tanggal = (EditText) findViewById(R.id.text_tanggal);
        jumlahPemasukan = (EditText) findViewById(R.id.text_jumlah);
        katagori = (Spinner) findViewById(R.id.katagori_spinner);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(this);
        waktuPemasukan = waktu.format(Calendar.getInstance().getTime());

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.katagori_pemasukan, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        katagori.setAdapter(adapter);
        katagori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               katagoriValue  = adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dateUtil();
    }

    public void dateUtil() {
        assert tanggal != null;
        tanggal.setInputType(InputType.TYPE_NULL);
        tanggal.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        tanggalPemasukan = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(year, monthOfYear, dayOfMonth);
                tanggal.setText(dateFormat.format(newCalendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {
        if(v == tanggal) {
            tanggalPemasukan.show();
        } else if(v == findViewById(R.id.btn_simpan)) {
            PemasukanModel pemasukan = new PemasukanModel();
            PemasukanService service = new PemasukanService(this);
            pemasukan.setNamaPemasukan(namaPemasukan.getText().toString());
            pemasukan.setTanggalPemasukan(tanggal.getText().toString());
            pemasukan.setWaktuPemasukan(waktuPemasukan);
            try {
                pemasukan.setJumlahPemasukan(Integer.parseInt(jumlahPemasukan.getText().toString()));
            } catch (NumberFormatException e) {
                new AlertDialog.Builder(this).
                        setMessage("Field Masih Kosong").setPositiveButton("OK", null).show();
                return;
            }

            pemasukan.setKatagoriPemasukan(katagoriValue);
            try {
                service.insert(pemasukan);
                Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InputPemasukan.this, Home.class);
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
