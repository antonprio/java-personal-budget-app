package com.skripsi.antonprio.pencatatanpersonal.Pengeluaran;

import android.provider.BaseColumns;

/**
 * Created by Anton Prio on 6/21/2016.
 */
public class PengeluaranModel {
    private int idPengeluaran;
    private String namaPengeluaran;
    private String tanggalPengeluaran;
    private int jumlahPengeluaran;
    private String katagoriPengeluaran;
    private String waktuPengeluaran;

    public int getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran(int idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }

    public int getJumlahPengeluaran() {
        return jumlahPengeluaran;
    }

    public void setJumlahPengeluaran(int jumlahPengeluaran) {
        this.jumlahPengeluaran = jumlahPengeluaran;
    }

    public String getKatagoriPengeluaran() {
        return katagoriPengeluaran;
    }

    public void setKatagoriPengeluaran(String katagoriPengeluaran) {
        this.katagoriPengeluaran = katagoriPengeluaran;
    }

    public String getNamaPengeluaran() {
        return namaPengeluaran;
    }

    public void setNamaPengeluaran(String namaPengeluaran) {
        this.namaPengeluaran = namaPengeluaran;
    }

    public String getTanggalPengeluaran() {
        return tanggalPengeluaran;
    }

    public void setTanggalPengeluaran(String tanggalPengeluaran) {
        this.tanggalPengeluaran = tanggalPengeluaran;
    }

    public void setWaktuPengeluaran(String waktuPengeluaran) {
        this.waktuPengeluaran = waktuPengeluaran;
    }

    public String getWaktuPengeluaran() {
        return waktuPengeluaran;
    }

    public static abstract class PengeluaranAttr implements BaseColumns {
        public static final String TABLE_NAME = "PENGELUARAN";
        public static final String PENGELUARAN_COL_ID = "ID_PENGELUARAN";
        public static final String PENGELUARAN_COL_NAMA = "NAMA_PENGELUARAN";
        public static final String PENGELUARAN_COL_TANGGAL = "TANGGAL_PENGELUARAN";
        public static final String PENGELUARAN_COL_WAKTU = "WAKTU_PENGELUARAN";
        public static final String PENGELUARAN_COL_JUMLAH = "JUMLAH_PENGELUARAN";
        public static final String PENGELUARAN_COL_KATAGORI = "KATAGORI_PENGELUARAN";
    }
}
