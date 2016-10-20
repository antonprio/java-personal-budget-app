package com.skripsi.antonprio.pencatatanpersonal.Rekap;

/**
 * Created by Anton Prio on 8/3/2016.
 */
public class RekapModel {
    private String rekapTanggal;
    private int rekapJumlah;
    private String rekapNama;

    public int getRekapJumlah() {
        return rekapJumlah;
    }

    public void setRekapJumlah(int rekapJumlah) {
        this.rekapJumlah = rekapJumlah;
    }

    public String getRekapNama() {
        return rekapNama;
    }

    public void setRekapNama(String rekapNama) {
        this.rekapNama = rekapNama;
    }

    public String getRekapTanggal() {
        return rekapTanggal;
    }

    public void setRekapTanggal(String rekapTanggal) {
        this.rekapTanggal = rekapTanggal;
    }
}
