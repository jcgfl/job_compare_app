package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class JobOffersActivity extends AppCompatActivity {

    private RecyclerView jobListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offers);

        ComparisonSettings comparisonSettings = ComparisonSettings.getComparisonSettings(this);

        jobListRecyclerView = findViewById(R.id.jobsListRecyclerView);
        jobListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobListRecyclerView.setAdapter(new JobsAdapter(this, comparisonSettings));

    }
}