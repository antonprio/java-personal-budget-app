package com.skripsi.antonprio.pencatatanpersonal.Anggaran;

import android.provider.BaseColumns;

/**
 * Created by Anton Prio on 8/16/2016.
 */
public class AnggaranModel {
    private int idAnggaran;
    private String namaAnggaran;
    private int jumlahAnggaran;
    private String tanggalAnggaran;
    private String katagoriAnggaran;
    private String prioritasAnggaran;
    private boolean statusAnggaran;

    public int getIdAnggaran() {
        return idAnggaran;
    }

    public void setIdAnggaran(int idAnggaran) {
        this.idAnggaran = idAnggaran;
    }

    public int getJumlahAnggaran() {
        return jumlahAnggaran;
    }

    public void setJumlahAnggaran(int jumlahAnggaran) {
        this.jumlahAnggaran = jumlahAnggaran;
    }

    public String getKatagoriAnggaran() {
        return katagoriAnggaran;
    }

    public void setKatagoriAnggaran(String katagoriAnggaran) {
        this.katagoriAnggaran = katagoriAnggaran;
    }

    public String getNamaAnggaran() {
        return namaAnggaran;
    }

    public void setNamaAnggaran(String namaAnggaran) {
        this.namaAnggaran = namaAnggaran;
    }

    public String getPrioritasAnggaran() {
        return prioritasAnggaran;
    }

    public void setPrioritasAnggaran(String prioritasAnggaran) {
        this.prioritasAnggaran = prioritasAnggaran;
    }

    public boolean isStatusAnggaran() {
        return statusAnggaran;
    }

    public void setStatusAnggaran(boolean statusAnggaran) {
        this.statusAnggaran = statusAnggaran;
    }

    public String getTanggalAnggaran() {
        return tanggalAnggaran;
    }

    public void setTanggalAnggaran(String tanggalAnggaran) {
        this.tanggalAnggaran = tanggalAnggaran;
    }

    public static abstract class AnggaranAttr implements BaseColumns {
        public static final String TABLE_NAME = "ANGGARAN";
        public static final String COL_ID_ANGGARAN = "ID_ANGGARAN";
        public static final String COL_NAMA_ANGGARAN = "NAMA_ANGGARAN";
        public static final String COL_JUMLAH_ANGGARAN = "JUMLAH_ANGGARAN";
        public static final String COL_TANGGAL_ANGGARAN = "TANGGAL_ANGGARAN";
        public static final String COL_KATAGORI_ANGGARAN = "KATAGORI_ANGGARAN";
        public static final String COL_PRIORITAS_ANGGARAN = "PRIORITAS_ANGGARAN";
        public static final String COL_STATUS_ANGGARAN = "STATUS_ANGGARAN";
    }
}
