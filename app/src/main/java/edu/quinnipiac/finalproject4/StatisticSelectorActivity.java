package edu.quinnipiac.finalproject4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticSelectorActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intentMain = new Intent(StatisticSelectorActivity.this ,
                ResultsActivity.class);
        startActivity(intentMain);
        Log.i("Content "," CountryInputActivity ");
    }
}
