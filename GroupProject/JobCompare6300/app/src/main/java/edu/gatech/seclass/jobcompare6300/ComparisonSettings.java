package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;

public class ComparisonSettings {

    private final long _ID;
    private final int yearlySalaryWeight;
    private final int yearlyBonusWeight;
    private final int allowedTeleworkDaysWeight;
    private final int leaveTimeDaysWeight;
    private final int gymAllowanceWeight;

    private ComparisonSettings(long _ID, int yearlySalaryWeight, int yearlyBonusWeight,
                               int allowedTeleworkDaysWeight, int leaveTimeDaysWeight,
                               int gymAllowanceWeight) {
        this._ID = _ID;
        this.yearlySalaryWeight = yearlySalaryWeight;
        this.yearlyBonusWeight = yearlyBonusWeight;
        this.allowedTeleworkDaysWeight = allowedTeleworkDaysWeight;
        this.leaveTimeDaysWeight = leaveTimeDaysWeight;
        this.gymAllowanceWeight = gymAllowanceWeight;
    }

    public static ComparisonSettings getComparisonSettings(Context context) {
        ComparisonSettings comparisonSettings = null;
        try (ComparisonSettingsDbHelper dbHelper = new ComparisonSettingsDbHelper(context)) {
            System.out.println("DBHELPER: " + dbHelper);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] projection = {
                    ComparisonSettingsEntry._ID,
                    ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_DAYS_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_GYM_ALLOWANCE_WEIGHT
            };

            Cursor cursor = db.query(
                    ComparisonSettingsEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while(cursor.moveToNext()) {
                comparisonSettings = new ComparisonSettings(cursor.getLong(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry._ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_DAYS_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_GYM_ALLOWANCE_WEIGHT)));
            }
            cursor.close();
        }
        if (comparisonSettings == null) {
            comparisonSettings = new ComparisonSettings(-1, 1,1,1,1,1);
        }
        return comparisonSettings;
    }

    public static ComparisonSettings saveComparisonSettings(Context context, long id, String yearlySalaryWeight, String yearlyBonusWeight,
                                              String allowedTeleworkDaysWeight, String leaveTimeDaysWeight,
                                              String gymAllowanceWeight) {

        ComparisonSettings comparisonSettings = null;

        try (ComparisonSettingsDbHelper dbHelper = new ComparisonSettingsDbHelper(context)) {
            System.out.println("DBHELPER: " + dbHelper);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put(ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT, yearlySalaryWeight);
            values.put(ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT, yearlyBonusWeight);
            values.put(ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS_WEIGHT, allowedTeleworkDaysWeight);
            values.put(ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_DAYS_WEIGHT, leaveTimeDaysWeight);
            values.put(ComparisonSettingsEntry.COLUMN_NAME_GYM_ALLOWANCE_WEIGHT, gymAllowanceWeight);

            if (id == -1) {
                long newRowId = db.insert(ComparisonSettingsEntry.TABLE_NAME, null, values);
                System.out.println("DBHELPER: Inserting " + newRowId);
            } else {
                String selection = ComparisonSettingsEntry._ID + " LIKE ?";
                String[] selectionArgs = {"" + id};

                int count = db.update(
                        ComparisonSettingsEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                System.out.println("DBHELPER: Updating " + id + " " + count);
            }
            String[] projection = {
                    ComparisonSettingsEntry._ID,
                    ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_DAYS_WEIGHT,
                    ComparisonSettingsEntry.COLUMN_NAME_GYM_ALLOWANCE_WEIGHT
            };
            String findSelection = ComparisonSettingsEntry._ID + " LIKE ?";
            String[] findSelectionArgs = {"" + id};

            Cursor cursor = db.query(
                    ComparisonSettingsEntry.TABLE_NAME,
                    projection,
                    findSelection,
                    findSelectionArgs,
                    null,
                    null,
                    null
            );

            while(cursor.moveToNext()) {
                comparisonSettings = new ComparisonSettings(cursor.getLong(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry._ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_DAYS_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ComparisonSettingsEntry.COLUMN_NAME_GYM_ALLOWANCE_WEIGHT)));
            }
            cursor.close();
        }

        return comparisonSettings;
    }

    public long get_ID() {
        return _ID;
    }

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public int getAllowedTeleworkDaysWeight() {
        return allowedTeleworkDaysWeight;
    }

    public int getLeaveTimeDaysWeight() {
        return leaveTimeDaysWeight;
    }

    public int getGymAllowanceWeight() {
        return gymAllowanceWeight;
    }

    public double getWeightSum() {
        return yearlySalaryWeight + yearlyBonusWeight + allowedTeleworkDaysWeight + leaveTimeDaysWeight + gymAllowanceWeight;
    }


    public static class ComparisonSettingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "comparison_settings";
        public static final String COLUMN_NAME_YEARLY_SALARY_WEIGHT = "yearly_salary_weight";
        public static final String COLUMN_NAME_YEARLY_BONUS_WEIGHT = "yearly_bonus_weight";
        public static final String COLUMN_NAME_ALLOWED_TELEWORK_DAYS_WEIGHT = "allowed_telework_days_weight";
        public static final String COLUMN_NAME_LEAVE_TIME_DAYS_WEIGHT = "leave_time_days_weight";
        public static final String COLUMN_NAME_GYM_ALLOWANCE_WEIGHT = "gym_allowance_weight";

    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ComparisonSettingsEntry.TABLE_NAME + " (" +
                    ComparisonSettingsEntry._ID + " INTEGER PRIMARY KEY," +
                    ComparisonSettingsEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT + " INTEGER," +
                    ComparisonSettingsEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT + " INTEGER," +
                    ComparisonSettingsEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS_WEIGHT + " INTEGER," +
                    ComparisonSettingsEntry.COLUMN_NAME_LEAVE_TIME_DAYS_WEIGHT + " INTEGER," +
                    ComparisonSettingsEntry.COLUMN_NAME_GYM_ALLOWANCE_WEIGHT + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ComparisonSettingsEntry.TABLE_NAME;

    public static class ComparisonSettingsDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "ComparisonSettings.db";

        public ComparisonSettingsDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(@NonNull SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
