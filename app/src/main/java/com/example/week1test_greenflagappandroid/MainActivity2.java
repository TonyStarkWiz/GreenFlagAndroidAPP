package com.example.week1test_greenflagappandroid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import java.util.regex.Matcher;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {
    private static final Pattern pattern = null;
    private EditText emailEdit;
    private TextView emailError;
    private EditText PasswordEdit;
    private TextView PasswordError;
    private TextView repeatPasswordEdit;
    private ImageView back;
    private SharedPreferences sharedPreferences;
    private ImageView next;

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[=0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main2);
        // REMEMBER ALWAYS FIND VIEW BY ID INSIDE ON CREATE
        back = findViewById(R.id.imageView6);
        emailError = findViewById(R.id.emailError);
        emailEdit = findViewById(R.id.EmailEdit);
        PasswordError = findViewById(R.id.PasswordError);
        PasswordEdit = findViewById(R.id.PasswordEdit);
        repeatPasswordEdit = findViewById(R.id.repeatPasswordEdit);
        next = findViewById(R.id.imageViewNext);
        sharedPreferences = getApplicationContext().getSharedPreferences("GREEN_FLAG", MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();

//            if (savedEmail != null) {
//                emailEdit.setText(savedEmail);
//                // display a ddialog
//            }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed.putString(emailEdit.getText().toString(), PasswordEdit.getText().toString());
                ed.apply();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                }
            });

        emailEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    if (isValidEmail(emailEdit.getText().toString())) {
                        String savedEmail = sharedPreferences.getString(emailEdit.getText().toString(), "default");
                        emailEdit.setBackgroundResource(R.drawable.broader1);
                        emailEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick2x, 0);
                        emailError.setVisibility(View.GONE);
                        if (!savedEmail.equals("default")) {

                        }
                    } else {
                        emailEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                        emailError.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.cross2x, 0, 0, 0);
                        emailError.setVisibility(View.VISIBLE);
                    }
                }
            });
            PasswordEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    if (isValidPassword(PasswordEdit.getText().toString())){
                        PasswordEdit.setBackgroundResource(R.drawable.broader1);
                    PasswordEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick2x, 0);
                    PasswordError.setVisibility(View.GONE);
                    }
                    else {
                        PasswordEdit.setBackgroundResource(R.drawable.broader2);
                        PasswordEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                        PasswordError.setText(R.string.second_password_error);
                        PasswordError.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.cross2x, 0, 0, 0);
                        PasswordError.setVisibility(View.VISIBLE);
                    }

                }
            });

            repeatPasswordEdit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged (CharSequence charSequence,int i, int i1, int i2){

                    }

                    @Override
                    public void onTextChanged (CharSequence charSequence,int i, int i1, int i2){

                    }

                    @Override
                    public void afterTextChanged (Editable editable){
                        if (isValidPassword(repeatPasswordEdit.getText().toString())) {
                            repeatPasswordEdit.setBackgroundResource(R.drawable.broader1);
                            repeatPasswordEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick2x, 0);
                            PasswordError.setVisibility(View.GONE);
                        } else {
                            repeatPasswordEdit.setBackgroundResource(R.drawable.broader2);
                            repeatPasswordEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
//                            repeatPasswordEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.cross2x, 0, 0, 0);
                            PasswordError.setText(R.string.notmatch);
                            PasswordError.setVisibility(View.VISIBLE);
                        }
                    }
                });


            }
    }


//    private void saveEmail(String email) {
//        SharedPreferences.Editor ed = sharedPreferences.edit();
//        ed.putString("my_email", email);
//        ed.apply();
//    }
