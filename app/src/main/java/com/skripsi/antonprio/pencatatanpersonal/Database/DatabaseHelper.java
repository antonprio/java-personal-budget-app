package com.skripsi.antonprio.pencatatanpersonal.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.skripsi.antonprio.pencatatanpersonal.Anggaran.AnggaranModel;
import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanModel;
import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranModel;

import java.io.File;

/**
 * Created by Anton Prio on 6/18/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Budget.db";
    private static final String CREATE_TABLE_PEMASUKAN =
            "CREATE TABLE "+ PemasukanModel.PemasukanAttr.TABLE_NAME+" (" +
                    PemasukanModel.PemasukanAttr.PEMASUKAN_COL_ID_PEMASUKAN+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PemasukanModel.PemasukanAttr.PEMASUKAN_COL_NAMA_PEMASUKAN+" VARCHAR(60), " +
                    PemasukanModel.PemasukanAttr.PEMASUKAN_COL_TANGGAL_PEMASUKAN+ " VARCHAR(20), " +
                    PemasukanModel.PemasukanAttr.PEMASUKAN_COL_WAKTU_PEMASUKAN+ " VARCHAR(10), "+
                    PemasukanModel.PemasukanAttr.PEMASUKAN_COL_JUMLAH_PEMASUKAN+" INTEGER, " +
                    PemasukanModel.PemasukanAttr.PEMASUKAN_COL_KATAGORI_PEMASUKAN+" VARCHAR(50)" +
                    ")";
    private static final String CREATE_TABLE_PENGELUARAN =
            "CREATE TABLE "+PengeluaranModel.PengeluaranAttr.TABLE_NAME+" (" +
                    PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_NAMA+" VARCHAR(60), " +
                    PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_TANGGAL+" VARCHAR(20), " +
                    PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_WAKTU+" VARCHAR(10), "+
                    PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_JUMLAH+" INTEGER, " +
                    PengeluaranModel.PengeluaranAttr.PENGELUARAN_COL_KATAGORI+" VARCHAR(50)" +
                    ")";
    private static final String CREATE_VIEW_SALDO =
            "CREATE VIEW V_SALDO AS SELECT " +
                    "(SELECT IFNULL(SUM(JUMLAH_PEMASUKAN),0) FROM PEMASUKAN) - " +
                    "(SELECT IFNULL(SUM(JUMLAH_PENGELUARAN),0) FROM PENGELUARAN) AS SALDO";
    private static final String CREATE_VIEW_REKAP =
            "CREATE VIEW V_REKAP AS SELECT TANGGAL_PEMASUKAN AS TANGGAL, JUMLAH_PEMASUKAN AS JUMLAH," +
                    "NAMA_PEMASUKAN AS NAMA FROM PEMASUKAN UNION ALL SELECT TANGGAL_PENGELUARAN, " +
                    "JUMLAH_PENGELUARAN, NAMA_PENGELUARAN FROM PENGELUARAN";
    private static final String CREATE_TABLE_ANGGARAN =
            "CREATE TABLE "+ AnggaranModel.AnggaranAttr.TABLE_NAME +" (" +
                    AnggaranModel.AnggaranAttr.COL_ID_ANGGARAN+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AnggaranModel.AnggaranAttr.COL_NAMA_ANGGARAN+" VARCHAR(60), " +
                    AnggaranModel.AnggaranAttr.COL_JUMLAH_ANGGARAN+" INTEGER, " +
                    AnggaranModel.AnggaranAttr.COL_TANGGAL_ANGGARAN+" VARCHAR(20), " +
                    AnggaranModel.AnggaranAttr.COL_KATAGORI_ANGGARAN+" VARCHAR(60), " +
                    AnggaranModel.AnggaranAttr.COL_PRIORITAS_ANGGARAN+" VARCHAR(10), " +
                    AnggaranModel.AnggaranAttr.COL_STATUS_ANGGARAN+" BOOLEAN" +
                    ")";
    private static final String CREATE_TABLE_PASSWORD =
            "CREATE TABLE PASSWORD (" +
                    "PASS VARCHAR(50)" +
                    ")";
    public DatabaseHelper(final Context context) {
        super(context, Environment.getExternalStorageDirectory()
        + File.separator+ DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PEMASUKAN);
        db.execSQL(CREATE_TABLE_PENGELUARAN);
        db.execSQL(CREATE_TABLE_ANGGARAN);
        db.execSQL(CREATE_VIEW_SALDO);
        db.execSQL(CREATE_VIEW_REKAP);
        db.execSQL(CREATE_TABLE_PASSWORD);
        Log.d("View Created", "View V_Saldo Dibuat!");
        Log.d("View Created", "View V_Rekap Dibuat");
        Log.i("Table Created", "Table Anggaran Dibuat");
        Log.d("Table Created", "Table Pemasukan Dibuat!");
        Log.d("Table Created", "Table Pengeluaran Dibuat!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ PemasukanModel.PemasukanAttr.TABLE_NAME);
        onCreate(db);
    }
}
