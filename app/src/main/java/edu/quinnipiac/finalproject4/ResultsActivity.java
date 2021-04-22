package edu.quinnipiac.finalproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

     /* R.id.climatebutton == 1000151
     R.id.elevationbutton == 1000349
     R.id.languagebutton == 1000336 */

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    public static List<String> data;
    String chosenCountry;
    int chosenStat;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        chosenCountry = getIntent().getStringExtra("Chosen Country");
        chosenStat = getIntent().getIntExtra("Selection",0);
        recyclerView = findViewById(R.id.results_recycler);

        db = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data = new ArrayList<>();
        fetchData();
    }

    public void fetchData() {
        db = new DatabaseHelper(this);
        try {
            db.createDataBase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        data = db.getSimilarItems(this,chosenCountry,chosenStat);
        adapter = new MyAdapter(this, (LinkedList) data);
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v) {}
}