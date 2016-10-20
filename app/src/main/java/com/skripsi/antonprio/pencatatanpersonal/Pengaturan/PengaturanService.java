package com.skripsi.antonprio.pencatatanpersonal.Pengaturan;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.skripsi.antonprio.pencatatanpersonal.Database.DatabaseHelper;

/**
 * Created by Anton Prio on 8/21/2016.
 */
public class PengaturanService {
    SQLiteDatabase mDatabase;
    DatabaseHelper mHelper;

    public PengaturanService(Context c) {
        mHelper = new DatabaseHelper(c);
    }

    public void deleteAllTables() {
        mDatabase = mHelper.getReadableDatabase();
        mDatabase.delete("PEMASUKAN", null, null);
        mDatabase.delete("PENGELUARAN", null, null);
        mDatabase.delete("ANGGARAN", null, null);
        String deleteSaldo = "DROP VIEW IF EXISTS V_SALDO";
        String deleteRekap = "DROP VIEW IF EXISTS V_REKAP";
        mDatabase.rawQuery(deleteRekap, null);
        mDatabase.rawQuery(deleteSaldo, null);
        mDatabase.execSQL("DELETE FROM sqlite_sequence");
        Log.i("1","Table Pemasukan Dihapus");
    }

    public void tambahPassword(PengaturanModel m) {
        mDatabase = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PASS", m.getPassword());
        mDatabase.insert("PASSWORD", null, values);
    }
}
