package com.skripsi.antonprio.pencatatanpersonal.Pemasukan;

import android.provider.BaseColumns;

/**
 * Created by Anton Prio on 6/18/2016.
 */
public class PemasukanModel {
    private int idPemasukan;
    private String namaPemasukan;
    private int jumlahPemasukan;
    private String tanggalPemasukan;
    private String katagoriPemasukan;
    private String waktuPemasukan;

    public int getIdPemasukan() {
        return idPemasukan;
    }

    public void setIdPemasukan(int idPemasukan) {
        this.idPemasukan = idPemasukan;
    }

    public String getTanggalPemasukan() { return tanggalPemasukan; }

    public void setTanggalPemasukan(String tanggalPemasukan) { this.tanggalPemasukan = tanggalPemasukan; }


    public int getJumlahPemasukan() {
        return jumlahPemasukan;
    }

    public void setJumlahPemasukan(int jumlahPemasukan) {
        this.jumlahPemasukan = jumlahPemasukan;
    }

    public String getKatagoriPemasukan() {
        return katagoriPemasukan;
    }

    public void setKatagoriPemasukan(String katagoriPemasukan) { this.katagoriPemasukan = katagoriPemasukan; }

    public String getNamaPemasukan() {
        return namaPemasukan;
    }

    public void setNamaPemasukan(String namaPemasukan) {
        this.namaPemasukan = namaPemasukan;
    }

    public String getWaktuPemasukan() {
        return waktuPemasukan;
    }

    public void setWaktuPemasukan(String waktuPemasukan) {
        this.waktuPemasukan = waktuPemasukan;
    }

    public static abstract class PemasukanAttr implements BaseColumns {
        public static final String TABLE_NAME = "PEMASUKAN";
        public static final String PEMASUKAN_COL_ID_PEMASUKAN = "ID_PEMASUKAN";
        public static final String PEMASUKAN_COL_NAMA_PEMASUKAN = "NAMA_PEMASUKAN";
        public static final String PEMASUKAN_COL_TANGGAL_PEMASUKAN = "TANGGAL_PEMASUKAN";
        public static final String PEMASUKAN_COL_WAKTU_PEMASUKAN = "WAKTU_PEMASUKAN";
        public static final String PEMASUKAN_COL_JUMLAH_PEMASUKAN = "JUMLAH_PEMASUKAN";
        public static final String PEMASUKAN_COL_KATAGORI_PEMASUKAN = "KATAGORI_PEMASUKAN";
    }
}
