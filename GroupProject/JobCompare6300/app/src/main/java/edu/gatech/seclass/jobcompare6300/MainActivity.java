package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button setCurrentJobButton;
    private Button enterJobOfferButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Job currentJob = Job.getCurrentJob(this);
        System.out.println("Main activity:" + currentJob);

        setCurrentJobButton = findViewById(R.id.setCurrentJobButton);
        enterJobOfferButton = findViewById(R.id.enterJobOfferDetailsButton);

        if (currentJob != null) {
            setCurrentJobButton.setText(R.string.editCurrentJob);
        } else {
            setCurrentJobButton.setText(R.string.addCurrentJob);
        }
    }

    public void launchCurrentJob(View view) {
        Intent intent = new Intent(this, CurrentJobActivity.class);

        startActivity(intent);
    }

    public void launchJobOffer(View view) {
        Intent intent = new Intent(this, JobOfferActivity.class);

        startActivity(intent);
    }

    public void launchComparisonSettings(View view) {
        Intent intent = new Intent(this, ComparisonSettingsActivity.class);

        startActivity(intent);
    }

    public void launchJobOffers(View view) {
        Intent intent = new Intent(this, JobOffersActivity.class);
        startActivity(intent);
    }
}