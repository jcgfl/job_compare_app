package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ComparisonSettingsActivity extends AppCompatActivity {

    private EditText yearlySalaryWeightField;
    private EditText yearlyBonusWeightField;
    private EditText allowedTeleworkDaysWeightField;
    private EditText leaveTimeDaysWeightField;
    private EditText gymAllowanceWeightField;

    private ComparisonSettings comparisonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_settings);

        yearlySalaryWeightField = findViewById(R.id.yearlySalaryWeightField);
        yearlyBonusWeightField = findViewById(R.id.yearlyBonusWeightField);
        allowedTeleworkDaysWeightField = findViewById(R.id.allowedTeleworkDaysWeightField);
        leaveTimeDaysWeightField = findViewById(R.id.leaveTimeDaysWeightField);
        gymAllowanceWeightField = findViewById(R.id.gymAllowanceWeightField);

        comparisonSettings = ComparisonSettings.getComparisonSettings(this);
        if (comparisonSettings != null) {
            yearlySalaryWeightField.setText("" + comparisonSettings.getYearlySalaryWeight());
            yearlyBonusWeightField.setText("" + comparisonSettings.getYearlyBonusWeight());
            allowedTeleworkDaysWeightField.setText("" + comparisonSettings.getAllowedTeleworkDaysWeight());
            leaveTimeDaysWeightField.setText("" + comparisonSettings.getLeaveTimeDaysWeight());
            gymAllowanceWeightField.setText("" + comparisonSettings.getGymAllowanceWeight());
        }
    }

    public void saveComparisonSettings(View view) {
        long id = -1;
        if (comparisonSettings != null) {
            id = comparisonSettings.get_ID();
        }

        comparisonSettings = ComparisonSettings.saveComparisonSettings(this, id, yearlySalaryWeightField.getText().toString(),
                yearlyBonusWeightField.getText().toString(), allowedTeleworkDaysWeightField.getText().toString(),
                leaveTimeDaysWeightField.getText().toString(), gymAllowanceWeightField.getText().toString());
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