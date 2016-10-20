package com.skripsi.antonprio.pencatatanpersonal.Pengeluaran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.skripsi.antonprio.pencatatanpersonal.Interface.PengeluaranInterface;
import com.skripsi.antonprio.pencatatanpersonal.Database.DatabaseHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Prio on 6/26/2016.
 */
public class PengeluaranService implements PengeluaranInterface {
    SQLiteDatabase db;
    DatabaseHelper helper;

    public PengeluaranService(Context context) {
        helper = new DatabaseHelper(context);
    }

    @Override
    public void insert(PengeluaranModel model) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_NAMA, model.getNamaPengeluaran());
        values.put(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_TANGGAL, model.getTanggalPengeluaran());
        values.put(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_WAKTU, model.getWaktuPengeluaran());
        values.put(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_JUMLAH, model.getJumlahPengeluaran());
        values.put(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_KATAGORI, model.getKatagoriPengeluaran());
        db.insert(PengeluaranModel.PengeluaranAttr.TABLE_NAME, null, values);
        Log.d("Insert Pengeluaran", "Insert Ke table pengeluaran berhasil");
        db.close();
    }

    @Override
    public int getTotalPengeluaran() {
        db = helper.getWritableDatabase();
        int sum = 0;
        String sql = "SELECT SUM (JUMLAH_PENGELUARAN) FROM PENGELUARAN";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return sum;
    }

    public List<KebutuhanModel> getKebutuhan(String namaKebutuhan) {
        db = helper.getReadableDatabase();
        List<KebutuhanModel> list = new ArrayList<>();
        String sql = "SELECT NAMA_KEBUTUHAN, JUMLAH FROM KEBUTUHAN WHERE NAMA_KEBUTUHAN LIKE ?  AND JUMLAH <= 10000000";
        Cursor cursor = db.rawQuery(sql, new String[]{"%"+namaKebutuhan+"%"});
        if(cursor != null) {
            while (cursor.moveToNext()) {
                int nama = cursor.getColumnIndex("NAMA_KEBUTUHAN");
                int jumlahKebutuhan = cursor.getColumnIndex("JUMLAH");
                KebutuhanModel model = new KebutuhanModel();
                model.setNamaKebutuhan(cursor.getString(nama));
                model.setJumlahKebutuhan(cursor.getInt(jumlahKebutuhan));
                list.add(model);
            }
        }
        db.close();
        return list;
    }

    @Override
    public List<PengeluaranModel> getAll() {
        db = helper.getReadableDatabase();
        List<PengeluaranModel> list = new ArrayList<>();
        String query = "SELECT * FROM PENGELUARAN ORDER BY ID_PENGELUARAN DESC";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                int idPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_ID);
                int namaPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_NAMA);
                int tanggalPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_TANGGAL);
                int waktuPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_WAKTU);
                int jumlahPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_JUMLAH);
                int katagoriPengeluaran = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_KATAGORI);
                PengeluaranModel model = new PengeluaranModel();
                model.setIdPengeluaran(cursor.getInt(idPengeluaran));
                model.setNamaPengeluaran(cursor.getString(namaPengeluaran));
                model.setTanggalPengeluaran(cursor.getString(tanggalPengeluaran));
                model.setWaktuPengeluaran(cursor.getString(waktuPengeluaran));
                model.setJumlahPengeluaran(cursor.getInt(jumlahPengeluaran));
                model.setKatagoriPengeluaran(cursor.getString(katagoriPengeluaran));
                list.add(model);

            }
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public PengeluaranModel getById(int id) {
        db = helper.getWritableDatabase();
        PengeluaranModel model = new PengeluaranModel();
        String pengeluaranId = PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_ID;
        String pengeluaranNama = PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_NAMA;
        String pengeluaranTanggal = PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_TANGGAL;
        String pengeluaranJumlah = PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_JUMLAH;
        String pengeluaranKatagori = PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_KATAGORI;
        String sql = "SELECT "+pengeluaranId+", "+pengeluaranNama+", "+pengeluaranTanggal+", "
                +pengeluaranJumlah+ ", "+pengeluaranKatagori+" FROM PENGELUARAN WHERE "+
                pengeluaranId+ " = ?";
        String[] args = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(sql, args);

        if (cursor != null && cursor.moveToFirst()) {
            int kolom_id = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_ID);
            int kolom_nama = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_NAMA);
            int kolom_tanggal = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_TANGGAL);
            int kolom_jumlah = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_JUMLAH);
            int kolom_katagori = cursor.getColumnIndex(PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_KATAGORI);
            model.setIdPengeluaran(cursor.getInt(kolom_id));
            model.setNamaPengeluaran(cursor.getString(kolom_nama));
            model.setTanggalPengeluaran(cursor.getString(kolom_tanggal));
            model.setJumlahPengeluaran(cursor.getInt(kolom_jumlah));
            model.setKatagoriPengeluaran(cursor.getString(kolom_katagori));
        }
        db.close();
        cursor.close();
        return model;
    }

    public static String formatedString(int value) {
        DecimalFormat format = new DecimalFormat();
        String formatter = format.format(value);
        return formatter;
    }
}
