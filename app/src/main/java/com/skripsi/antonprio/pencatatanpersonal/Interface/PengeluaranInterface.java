package com.skripsi.antonprio.pencatatanpersonal.Interface;

import com.skripsi.antonprio.pencatatanpersonal.Pengeluaran.PengeluaranModel;

import java.util.List;

/**
 * Created by Anton Prio on 6/26/2016.
 */
public interface PengeluaranInterface {
    void insert(PengeluaranModel model);
    int getTotalPengeluaran();
    List<PengeluaranModel> getAll();
    PengeluaranModel getById(int id);
}
