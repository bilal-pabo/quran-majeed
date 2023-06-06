package com.bilal.quran;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.bilal.quran.QuranArabicText;
import com.bilal.quran.QDH;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View customActionBarView = inflater.inflate(R.layout.custom_actionbar, null);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(customActionBarView);
        setContentView(R.layout.activity_new);

        String selectedItem = getIntent().getStringExtra("selectedItem");
        int surahNumber = getIntent().getIntExtra("start", 0);
        int surahStart = QDH.SSP[surahNumber] - 1;
        int ayaCount;
        if (surahNumber == 8)
            ayaCount = QDH.SSP[surahNumber + 1] - QDH.SSP[surahNumber];
        else if (surahNumber == 113)
            ayaCount = 6;
        else
            ayaCount = QDH.SSP[surahNumber + 1] - QDH.SSP[surahNumber] - 1;

        int end;
        if (surahNumber == 113)
        {
            end = QuranArabicText.QuranArabicText.length;
        }
        else
            end = QDH.SSP[surahNumber + 1] - 1;
        String text = "";
        String first = "";
        if (surahNumber != 8)
        {
            first = QuranArabicText.QuranArabicText[surahStart];
            for (int i = surahStart + 1; i < end; i++) {
                text += QuranArabicText.QuranArabicText[i];
            }
        }
        else {
            for (int i = surahStart; i < end; i++) {
                text += QuranArabicText.QuranArabicText[i];
            }
        }


        TextView title = findViewById(R.id.titleTextView);
        TextView heading = findViewById(R.id.headingTextView);
        TextView body = findViewById(R.id.largeTextView);
        title.setText("سورۃ " + selectedItem);
        heading.setText(first);
        body.setText(text);

        EditText searchNumber = findViewById(R.id.Number);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =  searchNumber.getText().toString();
                if(text.isEmpty())
                {
                    Toast toast = Toast.makeText(NewActivity.this, "کوئی نمبر داخل کریں", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 20, 20);
                    toast.show();
                }
                else
                {
                    int num = Integer.parseInt(text);
                    if (num < 1 || num > ayaCount)
                    Toast.makeText(NewActivity.this, "صحیح نمبر داخل کریں", Toast.LENGTH_SHORT).show();
                    else {
                        String ayat = "";
                        if (surahNumber == 8)
                        {
                            ayat = QuranArabicText.QuranArabicText[surahStart + num - 1];
                        }
                        else {
                            ayat = QuranArabicText.QuranArabicText[surahStart + num];
                        }
                        Intent intent = new Intent(NewActivity.this, LastActivity.class);
                        intent.putExtra("selectedAyat", ayat);
                        intent.putExtra("surat", selectedItem);
                        intent.putExtra("ayatNum", num);
                        startActivity(intent);
                    }
                }

            }
        });
    }

}

