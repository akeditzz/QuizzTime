package com.akeditzz.quizztime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.akeditzz.quizztime.Utils.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    Button btn_submit;
    EditText et_userName;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();





    }

    private void initView() {
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setEnabled(false);
        btn_submit.setBackground(getResources().getDrawable(R.drawable.btn_bg_disabled));
        et_userName = findViewById(R.id.et_name);
        preferenceManager = new PreferenceManager(this);

        et_userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length()>0){
                    btn_submit.setEnabled(true);
                    btn_submit.setBackground(getResources().getDrawable(R.drawable.btn_bg));
                }else {
                    btn_submit.setEnabled(false);
                    btn_submit.setBackground(getResources().getDrawable(R.drawable.btn_bg_disabled));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferenceManager.insertUserName(et_userName.getText().toString().trim());
                next();

            }
        });
    }

    private void next(){
        startActivity(new Intent(MainActivity.this,Main2Activity.class));
        finish();
    }

}
