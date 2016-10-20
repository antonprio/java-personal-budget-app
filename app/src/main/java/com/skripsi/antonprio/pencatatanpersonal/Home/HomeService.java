package com.skripsi.antonprio.pencatatanpersonal.Home;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.skripsi.antonprio.pencatatanpersonal.Database.DatabaseHelper;
import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanModel;
import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanService;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranModel;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Prio on 7/24/2016.
 */
public class HomeService {
    private SQLiteDatabase db;
    private DatabaseHelper helper;
    PemasukanService pemasukanService;
    PengeluaranService pengeluaranService;

    public HomeService(Context context) {
        helper = new DatabaseHelper(context);
    }

    public int getTotalSaldo() {
        db = helper.getWritableDatabase();
        int saldo = 0;
        String sql = "SELECT * FROM V_SALDO";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            saldo = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return saldo;
    }

    public List<PemasukanModel> getPemasukan() {
        db = helper.getReadableDatabase();
        List<PemasukanModel> list = new ArrayList<>();
        String sql = "SELECT * FROM PEMASUKAN ORDER BY ID_PEMASUKAN DESC LIMIT 0,2";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int namaPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_NAMA_PEMASUKAN);
                int jumlahPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_JUMLAH_PEMASUKAN);
                int katagoriPemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_KATAGORI_PEMASUKAN);
                int tanggal_pemasukan = cursor.getColumnIndex(PemasukanModel.PemasukanAttr.PEMASUKAN_COL_TANGGAL_PEMASUKAN);
                PemasukanModel model = new PemasukanModel();
                model.setNamaPemasukan(cursor.getString(namaPemasukan));
                model.setJumlahPemasukan(cursor.getInt(jumlahPemasukan));
                model.setKatagoriPemasukan(cursor.getString(katagoriPemasukan));
                model.setTanggalPemasukan(cursor.getString(tanggal_pemasukan));
                list.add(model);
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<PengeluaranModel> getPengeluaran() {
        db = helper.getReadableDatabase();
        List<PengeluaranModel> list = new ArrayList<>();
        String sql = "SELECT * FROM PENGELUARAN ORDER BY ID_PENGELUARAN DESC LIMIT 0,2";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int namaPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_NAMA);
                int jumlahPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_JUMLAH);
                int katagoriPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_KATAGORI);
                int tanggalPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_TANGGAL);
                PengeluaranModel model = new PengeluaranModel();
                model.setNamaPengeluaran(cursor.getString(namaPengeluaran));
                model.setJumlahPengeluaran(cursor.getInt(jumlahPengeluaran));
                model.setKatagoriPengeluaran(cursor.getString(katagoriPengeluaran));
                model.setTanggalPengeluaran(cursor.getString(tanggalPengeluaran));
                list.add(model);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
}
