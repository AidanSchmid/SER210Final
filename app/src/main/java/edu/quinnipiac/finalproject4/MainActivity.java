package edu.quinnipiac.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ResultsFragment.DatabaseListener,
        FavoritesFragment.getFavoritesListener {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyAdapter fetchData(String chosenCountry, int chosenStat) {
        List<String> data = db.getSimilarItems(this, chosenCountry, chosenStat);
        return new MyAdapter(this, (LinkedList<String>) data);
    }

    @Override
    public void setFavorite(String location) {
        db.setFavorite(location);
    }

    @Override
    public MyAdapter getFavorites() {
        List<String> favorites = db.getFavorites();
        return new MyAdapter(this, (LinkedList<String>) favorites);
    }
}