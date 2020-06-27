package com.example.kopmav1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.kopmav1.home.HomeActivity;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN_STATUS = "LOGIN_STATUS";
    public static final String ID_ANGGOTA = "ID_ANGGOTA";
    public static final String NAMA = "NAMA";
    public static final String JENIS_KELAMIN = "JENIS_KELAMIN";
    public static final String TELEPON = "TELEPON";
    public static final String JABATAN = "JABATAN";
    public static final String PRODI = "PRODI";
    public static final String JURUSAN = "JURUSAN";
    public static final String ANGKATAN = "ANGKATAN";
    public static final String ALAMAT = "ALAMAT";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String idanggota, String nama, String jKelamin, String telp, String jabatan, String prodi, String jurusan, String angkatan, String alamat) {
        editor.putBoolean(LOGIN_STATUS, true);
        editor.putString(ID_ANGGOTA, idanggota);
        editor.putString(NAMA, nama);
        editor.putString(JENIS_KELAMIN, jKelamin);
        editor.putString(TELEPON, telp);
        editor.putString(JABATAN, jabatan);
        editor.putString(PRODI, prodi);
        editor.putString(JURUSAN, jurusan);
        editor.putString(JURUSAN, jurusan);
        editor.putString(ANGKATAN, angkatan);
        editor.putString(ALAMAT, alamat);

        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN_STATUS, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();

        Intent login = new Intent(context, LoginActivity.class);
        context.startActivity(login);
        ((HomeActivity)context).finish();
    }

    public String getIdAnggota() {
        return sharedPreferences.getString(ID_ANGGOTA, null);
    }

    public String getNAMA() {
        return sharedPreferences.getString(NAMA, null);
    }

    public String getJenisKelamin() {
        return sharedPreferences.getString(JENIS_KELAMIN, null);
    }

    public String getTELEPON() {
        return sharedPreferences.getString(TELEPON, null);
    }

    public String getJABATAN() {
        return sharedPreferences.getString(JABATAN, null);
    }

    public String getPRODI() {
        return sharedPreferences.getString(PRODI, null);
    }

    public String getJURUSAN() {
        return sharedPreferences.getString(JURUSAN, null);
    }

    public String getANGKATAN() {
        return sharedPreferences.getString(ANGKATAN, null);
    }

    public String getALAMAT() {
        return sharedPreferences.getString(ALAMAT, null);
    }
}
