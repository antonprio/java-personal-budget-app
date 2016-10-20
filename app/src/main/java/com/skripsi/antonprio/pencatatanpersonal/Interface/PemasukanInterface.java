package com.skripsi.antonprio.pencatatanpersonal.Interface;

import com.skripsi.antonprio.pencatatanpersonal.Pemasukan.PemasukanModel;

import java.util.List;

/**
 * Created by Anton Prio on 7/13/2016.
 */
public interface PemasukanInterface {
    void insert(PemasukanModel model);
    int getTotalPemasukan();
    List<PemasukanModel> getAll();
}
