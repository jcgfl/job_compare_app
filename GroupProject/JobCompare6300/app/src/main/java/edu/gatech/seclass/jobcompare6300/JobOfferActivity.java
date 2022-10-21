package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JobOfferActivity extends AppCompatActivity {

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
    private Button btnSaveJob;
    private Button btnReset;
    private Button btnEnterAnother;
    private Button btnCompare;
    private Button btnReturn;


    Job.JobDbHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);
        myDb = new Job.JobDbHelper(this);
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
        btnSaveJob = findViewById(R.id.saveOfferButton);
        btnReset = findViewById(R.id.offerCancelButton);
    }

    public void SaveJob(View view) {
        String index = costOfLivingAdjustmentField.getText().toString();
        if (!TextUtils.isEmpty(index)){
            if (Integer.parseInt(index) == 0) {
                costOfLivingAdjustmentField.setError("Cost of living index should be a number > 0");
                return;
            }
        }
        Job job = Job.saveJobOffer(this, -1, titleField.getText().toString(),
                companyField.getText().toString(), cityField.getText().toString(),
                stateField.getText().toString(), costOfLivingAdjustmentField.getText().toString(),
                yearlySalaryField.getText().toString(), yearlyBonusField.getText().toString(),
                allowedTeleworkField.getText().toString(), leaveTimeField.getText().toString(),
                gymAllowanceField.getText().toString());

        Intent intent = new Intent(this, OfferSavedActivity.class);
        intent.putExtra(OfferSavedActivity.JOB_OFFER, job.get_ID());
        startActivity(intent);
    }

    public void launchOfferCanceled(View view) {
        Intent intent = new Intent(this, OfferCanceledActivity.class);
        startActivity(intent);
    }
}