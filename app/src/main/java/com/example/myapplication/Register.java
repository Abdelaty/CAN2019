package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends AppCompatActivity {
    String user, pass;

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.registerButton)
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        setTitle(R.string.reg_main);
        Firebase.setAndroidContext(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("")) {
                    username.setError(getString(R.string.blank_error));
                } else if (pass.equals("")) {
                    password.setError(getString(R.string.blank_error));
                } else if (!user.matches("[A-Za-z0-9]+")) {
                    username.setError(getString(R.string.only_alphabet));
                } else if (user.length() < 5) {
                    username.setError(getString(R.string.at_least_5));
                } else if (pass.length() < 5) {
                    password.setError(getString(R.string.at_least_5));
                } else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage(getString(R.string.loading));
                    pd.show();
                    String url = "https://can2019-2b45d.firebaseio.com/users.json";
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://can2019-2b45d.firebaseio.com/users");
                            if (s.equals("null")) {
                                reference.child(user).child(getString(R.string.password)).setValue(pass);
                                Toast.makeText(Register.this, R.string.Reg_suc, Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        reference.child(user).child(getString(R.string.password)).setValue(pass);
                                        Toast.makeText(Register.this, R.string.Reg_suc, Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(Register.this, Login.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(Register.this, R.string.user_exist, Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            pd.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });
                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }
            }
        });
    }
}
