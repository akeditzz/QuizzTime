package com.akeditzz.quizztime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akeditzz.quizztime.Utils.PreferenceManager;

public class ResultActivity extends AppCompatActivity {

    int score;
    PreferenceManager preferenceManager;
    TextView tv_congrats,tv_score;
    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
    }

    private void initView() {
        preferenceManager = new PreferenceManager(this);
        score = getIntent().getIntExtra(getString(R.string.label_score),0);
        tv_congrats = findViewById(R.id.tv_congrats);
        tv_score = findViewById(R.id.tv_score);
        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setData();
    }

    private void setData() {
        tv_score.setText(String.format(getString(R.string.int_resource), score));
        tv_congrats.setText(String.format(getString(R.string.mesg_congrats), preferenceManager.getUserName()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
