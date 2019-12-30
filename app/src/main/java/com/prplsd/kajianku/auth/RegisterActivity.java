package com.prplsd.kajianku.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.prplsd.kajianku.R;
import com.prplsd.kajianku.data.Constant;
import com.prplsd.kajianku.model.RegisterResponse;
import com.prplsd.kajianku.utils.DialogUtils;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txtLogin;
    EditText alamat;
    EditText nohp;
    EditText email;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initBinding();
        initButton();
    }

    private void initBinding(){
        btnRegister = findViewById(R.id.btn_reg_sign_up);
        txtLogin = findViewById(R.id.txt_reg_link_login);
        email = findViewById(R.id.et_reg_input_email);
        userName = findViewById(R.id.et_reg_input_name);
        alamat = findViewById(R.id.et_reg_input_alamat);
        nohp = findViewById(R.id.et_reg_input_nohp);
        password = findViewById(R.id.et_reg_input_password);
    }
    private void initButton(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Email Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Password Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(userName.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Nama harus diisi", Toast.LENGTH_SHORT).show();
                }else if(alamat.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Alamat Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(nohp.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "No.Hp harus diisi", Toast.LENGTH_SHORT).show();
                }else {
                    register();
                }
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
    public void register() {
        DialogUtils.openDialog(this);
        AndroidNetworking.post(Constant.REGISTER)
                .addBodyParameter("nama", userName.getText().toString())
                .addBodyParameter("alamat", alamat.getText().toString())
                .addBodyParameter("no_hp", nohp.getText().toString())
                .addBodyParameter("email", email.getText().toString())
                .addBodyParameter("password", password.getText().toString())
                .addBodyParameter("api_key", "1")
                .addBodyParameter("hit", "20")
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof RegisterResponse) {
                            RegisterResponse res = (RegisterResponse)
                                    response;
                            if (res.getStatus().equals("success")) {
                                Toast.makeText(RegisterActivity.this,
                                        "Register Berhasil, silakan login kembali",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this,
                                        "Username sudah digunakan", Toast.LENGTH_SHORT).show();
                            }
                            DialogUtils.closeDialog();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(RegisterActivity.this,
                                "Terjadi kesalahan API", Toast.LENGTH_SHORT).show();
                        DialogUtils.closeDialog();
                    }
                });
    }
}
