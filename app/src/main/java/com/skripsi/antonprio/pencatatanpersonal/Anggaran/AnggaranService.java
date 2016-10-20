package com.skripsi.antonprio.pencatatanpersonal.Anggaran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.skripsi.antonprio.pencatatanpersonal.Database.DatabaseHelper;
import com.skripsi.antonprio.pencatatanpersonal.Interface.AnggaranInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Prio on 8/16/2016.
 */
public class AnggaranService implements AnggaranInterface {
    SQLiteDatabase database;
    DatabaseHelper helper;
    public AnggaranService(Context context) {
        helper = new DatabaseHelper(context);
    }

    @Override
    public void insert(AnggaranModel model) {
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AnggaranModel.AnggaranAttr.COL_NAMA_ANGGARAN, model.getNamaAnggaran());
        values.put(AnggaranModel.AnggaranAttr.COL_JUMLAH_ANGGARAN, model.getJumlahAnggaran());
        values.put(AnggaranModel.AnggaranAttr.COL_TANGGAL_ANGGARAN, model.getTanggalAnggaran());
        values.put(AnggaranModel.AnggaranAttr.COL_KATAGORI_ANGGARAN, model.getKatagoriAnggaran());
        values.put(AnggaranModel.AnggaranAttr.COL_PRIORITAS_ANGGARAN, model.getPrioritasAnggaran());
        values.put(AnggaranModel.AnggaranAttr.COL_STATUS_ANGGARAN, model.isStatusAnggaran());
        database.insert(AnggaranModel.AnggaranAttr.TABLE_NAME, null, values);
        database.close();
    }

    @Override
    public List<AnggaranModel> getAll() {
        database = helper.getWritableDatabase();
        List<AnggaranModel> list = new ArrayList<>();
        String sql = "SELECT ID_ANGGARAN, NAMA_ANGGARAN, JUMLAH_ANGGARAN, TANGGAL_ANGGARAN," +
                "KATAGORI_ANGGARAN FROM ANGGARAN";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                int idAnggaran = cursor.getColumnIndex(AnggaranModel.AnggaranAttr.COL_ID_ANGGARAN);
                int namaAnggaran = cursor.getColumnIndex(AnggaranModel.AnggaranAttr.COL_NAMA_ANGGARAN);
                int jumlahAnggaran = cursor.getColumnIndex(AnggaranModel.AnggaranAttr.COL_JUMLAH_ANGGARAN);
                int tanggalAnggaran = cursor.getColumnIndex(AnggaranModel.AnggaranAttr.COL_TANGGAL_ANGGARAN);
                int katagoriAnggaran = cursor.getColumnIndex(AnggaranModel.AnggaranAttr.COL_KATAGORI_ANGGARAN);
                AnggaranModel model = new AnggaranModel();
                model.setIdAnggaran(cursor.getInt(idAnggaran));
                model.setNamaAnggaran(cursor.getString(namaAnggaran));
                model.setJumlahAnggaran(cursor.getInt(jumlahAnggaran));
                model.setTanggalAnggaran(cursor.getString(tanggalAnggaran));
                model.setKatagoriAnggaran(cursor.getString(katagoriAnggaran));
                list.add(model);
                //cursor.close();
            }
        }
        database.close();
        return list;
    }
}
