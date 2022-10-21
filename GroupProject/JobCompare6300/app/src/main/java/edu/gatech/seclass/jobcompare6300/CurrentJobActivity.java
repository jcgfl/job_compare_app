package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class CurrentJobActivity extends AppCompatActivity {

    private EditText titleField;
    private EditText companyField;
    private EditText cityField;
    private EditText stateField;
    private EditText costOfLivingAdjustmentField;
    private EditText yearlySalaryField;
    private EditText yearlyBonusField;
    private EditText allowedTeleworkField;
    private EditText leaveTimeField;
    private EditText gymAllowanceField;

    private Job currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);

        titleField = findViewById(R.id.titleField);
        companyField = findViewById(R.id.companyField);
        cityField = findViewById(R.id.cityField);
        stateField = findViewById(R.id.stateField);
        costOfLivingAdjustmentField = findViewById(R.id.coliField);
        yearlySalaryField = findViewById(R.id.salaryField);
        yearlyBonusField = findViewById(R.id.bonusField);
        allowedTeleworkField = findViewById(R.id.teleworkField);
        leaveTimeField = findViewById(R.id.leaveField);
        gymAllowanceField = findViewById(R.id.gymField);

        currentJob = Job.getCurrentJob(this);


        if (currentJob != null) {
            titleField.setText(currentJob.getTitle());
            companyField.setText(currentJob.getCompany());
            cityField.setText(currentJob.getCity());
            stateField.setText(currentJob.getState());
            costOfLivingAdjustmentField.setText("" + currentJob.getCostOfLivingAdjustment());
            yearlySalaryField.setText("" + currentJob.getYearlySalary());
            yearlyBonusField.setText("" + currentJob.getYearlyBonus());
            allowedTeleworkField.setText("" + currentJob.getAllowedTeleworkDays());
            leaveTimeField.setText("" + currentJob.getLeaveTimeDays());
            gymAllowanceField.setText("" + currentJob.getGymAllowance());
        }
    }

    public void saveCurrentJob(View view) {
        String index = costOfLivingAdjustmentField.getText().toString();
        if (!TextUtils.isEmpty(index)){
            if (Integer.parseInt(index) == 0) {
                costOfLivingAdjustmentField.setError("Cost of living index should be a number > 0");
                return;
            }
        }
        long id = -1;
        if (currentJob != null) {
            id = currentJob.get_ID();
        }
        currentJob = Job.saveCurrentJob(this, id, titleField.getText().toString(),
                companyField.getText().toString(), cityField.getText().toString(),
                stateField.getText().toString(), costOfLivingAdjustmentField.getText().toString(),
                yearlySalaryField.getText().toString(), yearlyBonusField.getText().toString(),
                allowedTeleworkField.getText().toString(), leaveTimeField.getText().toString(),
                gymAllowanceField.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void launchMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}