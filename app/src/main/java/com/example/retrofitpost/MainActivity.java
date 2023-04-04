package com.example.retrofitpost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitpost.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SignUpResponse signUpResponsesData;
    ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        getSupportActionBar().setTitle("Retrofit POST type");

        bind.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate(bind.txtName) && validateEmail() && validate(bind.txtPassword)){
                    signUp();
                }
            }
        });

    }

    private boolean validateEmail() {
        String email = bind.txtEmailAddress.getText().toString().trim();
        if (email.isEmpty() || !isVaidEmail(email)){
            bind.txtEmailAddress.setError("Email is not valid");
            bind.txtEmailAddress.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isVaidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(TextInputEditText textInputEditText){
        if(textInputEditText.getText().toString().trim().length() > 0){
            return true;
        }
        textInputEditText.setError("Please Fill this!");
        textInputEditText.requestFocus();
        return false;
    }
    private void signUp() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait......Posting Data");
        progressDialog.show();

        (Api.getClient().registration(
                bind.txtName.getText().toString().trim(),
                bind.txtEmailAddress.getText().toString().trim(),
                bind.txtPassword.getText().toString().trim(),
                "email")).enqueue(new Callback<SignUpResponse>() {
                    //Calls the registration() method on the ApiInterface object returned by the getClient() method.
                    // The method takes four parameters - the name, email, password, and type of registration (in this case, "email").
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpResponsesData = response.body();
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                bind.txtName.setText("");
                bind.txtEmailAddress.setText("");
                bind.txtPassword.setText("");

            }
            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Unsuccessfull, Retry!", Toast.LENGTH_SHORT).show();
                
            }
        });
    };

    }

