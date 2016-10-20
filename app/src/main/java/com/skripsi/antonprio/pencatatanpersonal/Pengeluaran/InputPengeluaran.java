package com.skripsi.antonprio.pencatatanpersonal.Pengeluaran;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.skripsi.antonprio.pencatatanpersonal.Home.Home;
import com.skripsi.antonprio.pencatatanpersonal.Home.HomeService;
import com.skripsi.antonprio.pencatatanpersonal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Anton Prio on 6/22/2016.
 */
public class InputPengeluaran extends AppCompatActivity implements View.OnClickListener {
    private EditText namaPengeluaran, tanggalPengeluaran, jumlahPengeluaran;
    private DatePickerDialog tanggal;
    private Spinner katagoriPengeluaran;
    String katagoriValue, waktuPengeluaran;
    private String dateFormat;
    private SimpleDateFormat waktu;
    private Button btnSimpan;
    PengeluaranService service = new PengeluaranService(this);
    PengeluaranModel model;
    HomeService mHomeService = new HomeService(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pengeluaran);

        Date date = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date);
        waktu = new SimpleDateFormat("h:mm a");
        waktuPengeluaran = waktu.format(Calendar.getInstance().getTime());

        namaPengeluaran = (EditText) findViewById(R.id.text_nama_pengeluaran);
        tanggalPengeluaran = (EditText) findViewById(R.id.text_tanggal_pengeluaran);
        jumlahPengeluaran = (EditText) findViewById(R.id.text_jumlah_pengeluaran);
        katagoriPengeluaran = (Spinner) findViewById(R.id.katagori_pengeluaran_spinner);
        btnSimpan = (Button) findViewById(R.id.btn_simpan_pengeluaran);



        btnSimpan.setOnClickListener(this);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.katagori_pengeluaran, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        katagoriPengeluaran.setAdapter(adapter);
        katagoriPengeluaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                katagoriValue = adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dateUtil();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem deleteItem, editItem;

        deleteItem = menu.findItem(R.id.action_delete);
        editItem = menu.findItem(R.id.action_edit);
        Bundle extras = getIntent().getExtras();

        deleteItem.setVisible(false);
        editItem.setVisible(false);

        if(extras != null) {
            String visibility = getIntent().getStringExtra("Button Visibility");
            if(visibility.equals("tampil")) {
                deleteItem.setVisible(true);
                editItem.setVisible(true);
            } else {
                deleteItem.setVisible(false);
                editItem.setVisible(false);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input_pengeluaran, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void dateUtil() {
        assert tanggalPengeluaran != null; tanggalPengeluaran.setInputType(InputType.TYPE_NULL);
        tanggalPengeluaran.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        tanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(year, monthOfYear, dayOfMonth);
                tanggalPengeluaran.setText(dateFormat);
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void showToast() {
        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        List<KebutuhanModel> list = service.getKebutuhan(namaPengeluaran.getText().toString());
        if(v == tanggalPengeluaran) {
            tanggal.show();
        } else if (v == btnSimpan) {
            int saldo = mHomeService.getTotalSaldo();
            int jumlah1 = Integer.parseInt(jumlahPengeluaran.getText().toString());
            final int selisih = saldo - jumlah1;
            model = new PengeluaranModel();
            model.setNamaPengeluaran(namaPengeluaran.getText().toString());
            model.setTanggalPengeluaran(tanggalPengeluaran.getText().toString());
            model.setWaktuPengeluaran(waktuPengeluaran);
            try {
                model.setJumlahPengeluaran(Integer.parseInt(jumlahPengeluaran.getText().toString()));
            } catch (NumberFormatException e) {
                new AlertDialog.Builder(this)
                        .setMessage("Field Masih Kosong").setPositiveButton("OK", null).show();
                return;
            }
            model.setKatagoriPengeluaran(katagoriValue);
            try {
                KebutuhanModel model1 = new KebutuhanModel();
                model1.setNamaKebutuhan(namaPengeluaran.getText().toString());
                model1.setJumlahKebutuhan(Integer.parseInt(jumlahPengeluaran.getText().toString()));
                list.add(model1);

                if(list.size() > 0) {
                    new AlertDialog.Builder(this)
                            .setMessage("Berdasarkan penelitian kami nama pengeluaran dan jumlah pengeluaran" +
                                    "anda masuk dalam katagori sekunder, saran kami anda harus pertahankan" +
                                    " kebutuhan anda untuk keperluan primer anda, tetep ingin lanjut?").
                            setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(selisih < 0) {
                                        new AlertDialog.Builder(getApplicationContext())
                                                .setMessage("Saldo Minus, Tidak Bisa Lanjut").setPositiveButton("OK", null).show();
                                        return;
                                    } else {
                                        service.insert(model);
                                        showToast();
                                        Intent intent = new Intent(InputPengeluaran.this, Home.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.putExtra("Exit", true);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }).setNegativeButton("Tidak", null).show();
                    return;
                } else {
                    service.insert(model);
                    showToast();
                    Intent intent = new Intent(InputPengeluaran.this, Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Exit", true);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
