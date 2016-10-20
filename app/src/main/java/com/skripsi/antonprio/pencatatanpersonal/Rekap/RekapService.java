package com.skripsi.antonprio.pencatatanpersonal.Rekap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.skripsi.antonprio.pencatatanpersonal.Database.DatabaseHelper;
import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Prio on 8/3/2016.
 */
public class RekapService {
    SQLiteDatabase db;
    DatabaseHelper helper;

    public RekapService(Context context) {
        helper = new DatabaseHelper(context);
    }

    public List<RekapModel> getRekap(String tglAwal, String tglAkhir) {
        db = helper.getReadableDatabase();
        List<RekapModel> list = new ArrayList<>();
        String sql = "SELECT TANGGAL, JUMLAH FROM V_REKAP WHERE TANGGAL BETWEEN ? AND ? ORDER BY TANGGAL ASC";
        String[] args = {tglAwal, tglAkhir};
        Cursor cursor = db.rawQuery(sql, args);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                RekapModel model = new RekapModel();
                int rekapTanggal = cursor.getColumnIndex("TANGGAL");
                int rekapJumlah = cursor.getColumnIndex("JUMLAH");
                model.setRekapTanggal(cursor.getString(rekapTanggal));
                model.setRekapJumlah(cursor.getInt(rekapJumlah));
                list.add(model);
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<PemasukanModel> getPemasukanRekap(String tglAwal, String tglAkhir) {
        db = helper.getReadableDatabase();
        List<PemasukanModel> list = new ArrayList<>();
        String sql = "SELECT TANGGAL_PEMASUKAN, JUMLAH_PEMASUKAN FROM PEMASUKAN WHERE " +
                "TANGGAL_PEMASUKAN BETWEEN ? AND ? ORDER BY TANGGAL_PEMASUKAN ASC";
        String[] args = {tglAwal, tglAkhir};
        Cursor cursor = db.rawQuery(sql, args);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                PemasukanModel model = new PemasukanModel();
                int rekapTanggal = cursor.getColumnIndex("TANGGAL_PEMASUKAN");
                int rekapJumlah = cursor.getColumnIndex("JUMLAH_PEMASUKAN");
                model.setTanggalPemasukan(cursor.getString(rekapTanggal));
                model.setJumlahPemasukan(cursor.getInt(rekapJumlah));
                list.add(model);
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public int getTotalPemasukan(String tglAwal, String tglAkhir) {
        db = helper.getWritableDatabase();
        int total = 0;
        String sql = "SELECT SUM(JUMLAH_PEMASUKAN) FROM PEMASUKAN WHERE TANGGAL_PEMASUKAN " +
                "BETWEEN ? AND ?";
        String[] args = {tglAwal, tglAkhir};
        Cursor cursor = db.rawQuery(sql, args);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }

    public int getTotalRekap(String tglAwal, String tglAkhir) {
        db = helper.getWritableDatabase();
        int total = 0;
        String sql = "SELECT SUM(JUMLAH) FROM V_REKAP WHERE TANGGAL BETWEEN ? AND ?";
        String[] args = {tglAwal, tglAkhir};
        Cursor cursor = db.rawQuery(sql, args);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }

    public List<RekapModel> getRekapLengkap(String tglAwal, String tglAkhir) {
        db = helper.getReadableDatabase();
        List<RekapModel> list = new ArrayList<>();
        String sql = "SELECT * FROM V_REKAP WHERE TANGGAL BETWEEN ? AND ? ORDER BY TANGGAL ASC";
        String[] args = {tglAwal, tglAkhir};
        Cursor cursor = db.rawQuery(sql, args);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                RekapModel model = new RekapModel();
                int rekapNama = cursor.getColumnIndex("NAMA");
                int rekapTanggal = cursor.getColumnIndex("TANGGAL");
                int rekapJumlah = cursor.getColumnIndex("JUMLAH");
                model.setRekapNama(cursor.getString(rekapNama));
                model.setRekapTanggal(cursor.getString(rekapTanggal));
                model.setRekapJumlah(cursor.getInt(rekapJumlah));
                list.add(model);
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<PemasukanModel> getPemasukanLengkap(String tglAwal, String tglAkhir) {
        db = helper.getReadableDatabase();
        List<PemasukanModel> list = new ArrayList<>();
        String sql = "SELECT * FROM PEMASUKAN WHERE TANGGAL_PEMASUKAN BETWEEN ? AND ? ORDER BY " +
                "TANGGAL_PEMASUKAN ASC";
        String[] args = {tglAwal, tglAkhir};
        Cursor cursor = db.rawQuery(sql, args);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                PemasukanModel model = new PemasukanModel();
                int rekapNama = cursor.getColumnIndex("NAMA_PEMASUKAN");
                int rekapTanggal = cursor.getColumnIndex("TANGGAL_PEMASUKAN");
                int rekapJumlah = cursor.getColumnIndex("JUMLAH_PEMASUKAN");
                model.setNamaPemasukan(cursor.getString(rekapNama));
                model.setTanggalPemasukan(cursor.getString(rekapTanggal));
                model.setJumlahPemasukan(cursor.getInt(rekapJumlah));
                list.add(model);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
}
