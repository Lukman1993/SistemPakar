package com.example.sistempakarkebunsawit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private ProgressBar loading;
    private TextView tvLink_register;
    SessionManager sessionManager;
    private static String URL_LOGIN = "https://lukmandigital.com/android_register_login/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        edtEmail = findViewById(R.id.edt_email_in);
        edtPassword = findViewById(R.id.edt_password_in);
        btnLogin = findViewById(R.id.btn_login);
        loading = findViewById(R.id.loading_in);
        tvLink_register = findViewById(R.id.tv_link_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = edtEmail.getText().toString().trim();
                String mPassword = edtPassword.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                    login(mEmail, mPassword);

                } else {
                    edtEmail.setError("Mohon Masukkan Email");
                    edtPassword.setError("Mohon Masukkan Password");
                }
            }
        });

        tvLink_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login(final String email, final String password) {

        loading.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String id = object.getString("id").trim();

                                    Toast.makeText(LoginActivity.this,
                                            "Selamat Datang " + name , Toast.LENGTH_SHORT).show();

                                    sessionManager.createSession(name, email, id);

                                    Intent intent = new Intent(LoginActivity.this,Splash.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("email", email);
                                    //intent.putExtra("id", id);
                                    startActivity(intent);

                                    loading.setVisibility(View.GONE);
                                    btnLogin.setVisibility(View.VISIBLE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Login Error " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btnLogin.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Login Error " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("password", password);
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}