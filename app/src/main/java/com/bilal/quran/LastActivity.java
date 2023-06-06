package com.bilal.quran;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View customActionBarView = inflater.inflate(R.layout.custom_actionbar, null);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(customActionBarView);
        setContentView(R.layout.last);
        String suratNum = getIntent().getStringExtra("surat");
        int ayatNum = getIntent().getIntExtra("ayatNum", 0);
        String ayat = getIntent().getStringExtra("selectedAyat");
        TextView title = findViewById(R.id.surahNum);
        TextView heading = findViewById(R.id.ayatNum);
        TextView body = findViewById(R.id.ayat);
        title.setText("سورۃ " + suratNum);
        heading.setText("آیت نمبر: " + ayatNum);
        body.setText(ayat);

    }

}