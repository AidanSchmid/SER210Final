package edu.quinnipiac.finalproject4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CountryInputActivity extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Intent intentMain = new Intent(CountryInputActivity.this,
                        StatisticSelectorActivity.class);
                startActivity(intentMain);
                Log.i("Content ", " StatisticSelectorActivity ");
                break;
            case R.id.button2:
                Intent intentMain2 = new Intent(CountryInputActivity.this,
                        Favorites.class);
                startActivity(intentMain2);
                Log.i("Content ", " Favorites ");
                break;

        }

    }
}
