package com.skripsi.antonprio.pencatatanpersonal.Pemasukan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.skripsi.antonprio.pencatatanpersonal.Database.DatabaseHelper;
import com.skripsi.antonprio.pencatatanpersonal.Interface.PemasukanInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Prio on 6/20/2016.
 */
public class PemasukanService implements PemasukanInterface {
    SQLiteDatabase db;
    DatabaseHelper helper;
    //SimpleDateFormat tanggal;

    public PemasukanService(Context context) {
        helper = new DatabaseHelper(context);
    }

    @Override
    public void insert(PemasukanModel p) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_NAMA_PEMASUKAN, p.getNamaPemasukan());
        values.put(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_TANGGAL_PEMASUKAN, p.getTanggalPemasukan().toString());
        values.put(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_WAKTU_PEMASUKAN, p.getWaktuPemasukan());
        values.put(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_JUMLAH_PEMASUKAN, p.getJumlahPemasukan());
        values.put(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_KATAGORI_PEMASUKAN, p.getKatagoriPemasukan());
        db.insert(PemasukanModel.PemasukanAttr.TABLE_NAME, null, values);
        Log.d("Method insert()", "Proses Insert Berhasil");
        db.close();
    }

    @Override
    public int getTotalPemasukan() {
        db = helper.getWritableDatabase();
        int sum = 0;
        String sql = "SELECT SUM(JUMLAH_PEMASUKAN) FROM PEMASUKAN";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        } else {
            sum = 0;
        }
        cursor.close();
        db.close();
        return sum;
    }

    @Override
    public List<PemasukanModel> getAll() {
        db = helper.getReadableDatabase();
        List<PemasukanModel> list = new ArrayList<>();
        String sql = "SELECT * FROM PEMASUKAN ORDER BY ID_PEMASUKAN DESC";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_ID_PEMASUKAN);
                int namaPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_NAMA_PEMASUKAN);
                int tanggalPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_TANGGAL_PEMASUKAN);
                int waktuPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_WAKTU_PEMASUKAN);
                int jumlahPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_JUMLAH_PEMASUKAN);
                int katagoriPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_KATAGORI_PEMASUKAN);
                PemasukanModel model = new PemasukanModel();
                model.setIdPemasukan(cursor.getInt(idPemasukan));
                model.setNamaPemasukan(cursor.getString(namaPemasukan));
                model.setTanggalPemasukan(cursor.getString(tanggalPemasukan));
                model.setWaktuPemasukan(cursor.getString(waktuPemasukan));
                model.setJumlahPemasukan(cursor.getInt(jumlahPemasukan));
                model.setKatagoriPemasukan(cursor.getString(katagoriPemasukan));
                list.add(model);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
}
