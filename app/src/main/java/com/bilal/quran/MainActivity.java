package com.bilal.quran;
import com.bilal.quran.QDH;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[] values = QDH.urduSurahNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View customActionBarView = inflater.inflate(R.layout.custom_actionbar, null);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(customActionBarView);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = values[position];

                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("selectedItem", selectedItem);
                intent.putExtra("start", position);
                startActivity(intent);
            }
        });
    }
}
