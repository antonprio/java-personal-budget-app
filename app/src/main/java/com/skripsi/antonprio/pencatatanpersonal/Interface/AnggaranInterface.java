package com.skripsi.antonprio.pencatatanpersonal.Interface;

import com.skripsi.antonprio.pencatatanpersonal.Anggaran.AnggaranModel;

import java.util.List;

/**
 * Created by Anton Prio on 8/16/2016.
 */
public interface AnggaranInterface {
    void insert(AnggaranModel model);
    List<AnggaranModel> getAll();
}
