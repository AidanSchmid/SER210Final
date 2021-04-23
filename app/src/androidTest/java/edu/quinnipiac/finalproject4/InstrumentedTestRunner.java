package edu.quinnipiac.finalproject4;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;


import org.junit.*;

import static junit.framework.TestCase.assertEquals;

public class InstrumentedTestRunner {

    private Context context;
    private DatabaseHelper dbh;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbh = new DatabaseHelper(context);
        dbh.createDataBase();
        dbh.openDataBase();
    }

    @Test
    public void verifyElevation() {
        assertEquals("Cambodia, with an average elevation of 413 ft",dbh.getSimilarItems(
                context,"'Senegal'",R.id.elevationbutton).get(1));
        System.out.println("Elevation test complete");
    }

    @Test
    public void verifyLanguage() {
        assertEquals("Andorra",dbh.getSimilarItems(context,"'Spain'",R.id.languagebutton).get(1));
        System.out.println("Language test complete");
    }

    @Test
    public void verifyTemperature() {
        assertEquals("Canada, with an average yearly temperature of -5 degrees Celsius",
                dbh.getSimilarItems(context,"'Russia'",R.id.temperaturebutton).get(1));
        System.out.println("Temperature test complete");
    }
}