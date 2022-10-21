package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CompareJobsActivity extends AppCompatActivity {

    public static final String JOB_ONE = "JobOne";
    public static final String JOB_TWO = "JobTwo";

    private TextView jobOneTitleField;
    private TextView jobOneCompanyField;
    private TextView jobOneCityField;
    private TextView jobOneStateField;
    private TextView jobOneYearlySalaryField;
    private TextView jobOneYearlyBonusField;
    private TextView jobOneAllowedTeleworkField;
    private TextView jobOneLeaveTimeField;
    private TextView jobOneGymAllowanceField;

    private TextView jobTwoTitleField;
    private TextView jobTwoCompanyField;
    private TextView jobTwoCityField;
    private TextView jobTwoStateField;
    private TextView jobTwoYearlySalaryField;
    private TextView jobTwoYearlyBonusField;
    private TextView jobTwoAllowedTeleworkField;
    private TextView jobTwoLeaveTimeField;
    private TextView jobTwoGymAllowanceField;

    private Button jobButton1;
    private Button jobButton2;

    private Job firstJob;
    private Job secondJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_jobs);

        long firstJobID = getIntent().getLongExtra(JOB_ONE, -1);
        firstJob = Job.getJob(this, firstJobID);
        long secondJobID = getIntent().getLongExtra(JOB_TWO, -1);
        secondJob = Job.getJob(this, secondJobID);

        System.out.println("DBH: Compare " + firstJobID + " with " + secondJobID);
        System.out.println("DBH: Compare " + firstJob.getTitle() + " with " + secondJob.getTitle());

        jobOneTitleField = findViewById(R.id.titleField1);
        jobOneCompanyField = findViewById(R.id.companyField1);
        jobOneCityField = findViewById(R.id.cityField1);
        jobOneStateField = findViewById(R.id.stateField1);
        jobOneYearlySalaryField = findViewById(R.id.salaryField1);
        jobOneYearlyBonusField = findViewById(R.id.bonusField1);
        jobOneAllowedTeleworkField = findViewById(R.id.teleworkField1);
        jobOneLeaveTimeField = findViewById(R.id.leaveField1);
        jobOneGymAllowanceField = findViewById(R.id.gymField1);

        jobOneTitleField.setText(firstJob.getTitle());
        jobOneCompanyField.setText(firstJob.getCompany());
        jobOneCityField.setText(firstJob.getCity());
        jobOneStateField.setText(firstJob.getState());
        jobOneYearlySalaryField.setText(String.format("%s", firstJob.getAdjustedYearlySalary()));
        jobOneYearlyBonusField.setText(String.format("%s", firstJob.getAdjustedYearlyBonus()));
        jobOneAllowedTeleworkField.setText(String.format("%s", firstJob.getAllowedTeleworkDays()));
        jobOneLeaveTimeField.setText(String.format("%s", firstJob.getLeaveTimeDays()));
        jobOneGymAllowanceField.setText(String.format("%s", firstJob.getGymAllowance()));

        jobTwoTitleField = findViewById(R.id.titleField2);
        jobTwoCompanyField = findViewById(R.id.companyField2);
        jobTwoCityField = findViewById(R.id.cityField2);
        jobTwoStateField = findViewById(R.id.stateField2);
        jobTwoYearlySalaryField = findViewById(R.id.salaryField2);
        jobTwoYearlyBonusField = findViewById(R.id.bonusField2);
        jobTwoAllowedTeleworkField = findViewById(R.id.teleworkField2);
        jobTwoLeaveTimeField = findViewById(R.id.leaveField2);
        jobTwoGymAllowanceField = findViewById(R.id.gymField2);

        jobTwoTitleField.setText(secondJob.getTitle());
        jobTwoCompanyField.setText(secondJob.getCompany());
        jobTwoCityField.setText(secondJob.getCity());
        jobTwoStateField.setText(secondJob.getState());
        jobTwoYearlySalaryField.setText(String.format("%s", secondJob.getAdjustedYearlySalary()));
        jobTwoYearlyBonusField.setText(String.format("%s", secondJob.getAdjustedYearlyBonus()));
        jobTwoAllowedTeleworkField.setText(String.format("%s", secondJob.getAllowedTeleworkDays()));
        jobTwoLeaveTimeField.setText(String.format("%s", secondJob.getLeaveTimeDays()));
        jobTwoGymAllowanceField.setText(String.format("%s", secondJob.getGymAllowance()));

        jobButton1 = findViewById(R.id.jobButton1);
        jobButton2 = findViewById(R.id.jobButton2);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, JobOffersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}