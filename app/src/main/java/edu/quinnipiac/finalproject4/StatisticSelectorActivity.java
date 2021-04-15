package edu.quinnipiac.finalproject4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticSelectorActivity extends AppCompatActivity implements View.OnClickListener {

    private String chosenCountry;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        chosenCountry = getIntent().getStringExtra("Chosen Country");
    }

    @Override
    public void onClick(View v) {
        Intent intentMain = new Intent(StatisticSelectorActivity.this, ResultsActivity.class);
        // Store the chosen country from the input activity, then store the statistic chosen here, in intent
        intentMain.putExtra("Chosen Country",chosenCountry);
        intentMain.putExtra("Selection",((RadioGroup)findViewById(R.id.selectionGroup)).getCheckedRadioButtonId());
        startActivity(intentMain);
        Log.i("Content "," CountryInputActivity ");
    }
}
