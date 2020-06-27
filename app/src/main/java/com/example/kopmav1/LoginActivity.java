package com.example.kopmav1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kopmav1.home.HomeActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText username;
    EditText password;

    //    progressdialog u/ loading, requestqueue untuk volley
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

//    session manager untuk menyimpan sesi login
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.eTextUsername);
        password = findViewById(R.id.eTextPassword);

        //        instansce dari progressdialog, sessionmanager dan requestqueue
        progressDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);
        sessionManager = new SessionManager(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(username, "Username tidak boleh kosong") & validate(password, "Password tidak boleh kosong")) {
                    processLogin();
                }
            }
        });
    }

    private boolean validate(EditText editText, String message) {
        String input = editText.getText().toString().trim();

        if (input.isEmpty()) {
            editText.setError(message);
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    private void processLogin() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

//        membuat request login
        StringRequest loginRequest = new StringRequest(Request.Method.POST, ApiUrl.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
//                    menangkap response berupa objek terluar
                    JSONObject objectResponse = new JSONObject(response);

//                    mengambil nilai
                    String status = objectResponse.getString("status");
                    String message = objectResponse.getString("message");

                    if (status.equals("true")) {
//                        mengambil data user
                        JSONObject user = objectResponse.getJSONObject("user");

//                        mengambil jabatan user
                        String jabatan = user.getString("jabatan");

//                        cek jabatan user
                        if(jabatan.equals("4")){
                            Toast.makeText(getApplicationContext(), "Aplikasi ini hanya untuk pengurus", Toast.LENGTH_LONG).show();
                        } else {
                            String id_anggota = user.getString("id_anggota");
                            String nama = user.getString("nama");
                            String jenis_kelamin = user.getString("jenis_kelamin");
                            String telepon = user.getString("telepon");
                            String prodi = user.getString("prodi");
                            String jurusan = user.getString("jurusan");
                            String angkatan = user.getString("angkatan");
                            String alamat = user.getString("alamat");

//                            membuat sesi login baru
                            sessionManager.createSession(id_anggota, nama, jenis_kelamin, telepon, jabatan, prodi, jurusan, angkatan, alamat);

//                            menuju halaman home
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
//                        jika status false
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
//                    jika terjadi error pada response
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                jika terjadi error pada proses volley
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(loginRequest);
    }
}
