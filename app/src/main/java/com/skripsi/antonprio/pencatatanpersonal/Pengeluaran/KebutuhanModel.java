package com.skripsi.antonprio.pencatatanpersonal.Pengeluaran;

/**
 * Created by Anton Prio on 9/2/2016.
 */
public class KebutuhanModel {
    private String namaKebutuhan;
    private int jumlahKebutuhan;

    public void setNamaKebutuhan(String namaKebutuhan) {
        this.namaKebutuhan = namaKebutuhan;
    }

    public void setJumlahKebutuhan(int jumlahKebutuhan) {
        this.jumlahKebutuhan = jumlahKebutuhan;
    }

    public String getNamaKebutuhan() {
        return namaKebutuhan;
    }

    public int getJumlahKebutuhan() {
        return jumlahKebutuhan;
    }
}
