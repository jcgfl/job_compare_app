package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OfferSavedActivity extends AppCompatActivity {

    public static final String JOB_OFFER = "JobOffer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_saved);
    }

    public void launchMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void launchJobOffer(View view) {
        Intent intent = new Intent(this, JobOfferActivity.class);

        startActivity(intent);
    }

    public void launchCompareOffer(View view) {
        Job currentJob = Job.getCurrentJob(this);
        if (currentJob != null) {
            Intent intent = new Intent(this, CompareJobsActivity.class);
            intent.putExtra(CompareJobsActivity.JOB_ONE, currentJob.get_ID());
            intent.putExtra(CompareJobsActivity.JOB_TWO, this.getIntent().getLongExtra(JOB_OFFER, -1));
            startActivity(intent);
        }
    }


}