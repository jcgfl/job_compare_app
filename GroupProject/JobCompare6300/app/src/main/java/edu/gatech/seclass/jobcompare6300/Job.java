package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class Job implements Comparable<Job> {

    private static String[] jobsProjection = {
            JobEntry._ID,
            JobEntry.COLUMN_NAME_TITLE,
            JobEntry.COLUMN_NAME_COMPANY,
            JobEntry.COLUMN_NAME_CITY,
            JobEntry.COLUMN_NAME_STATE,
            JobEntry.COLUMN_NAME_COST_OF_LIVING_INDEX,
            JobEntry.COLUMN_NAME_YEARLY_SALARY,
            JobEntry.COLUMN_NAME_YEARLY_BONUS,
            JobEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS,
            JobEntry.COLUMN_NAME_LEAVE_TIME_DAYS,
            JobEntry.COLUMN_NAME_GYM_ALLOWANCE,
            JobEntry.COLUMN_NAME_STATUS
    };

    private static final int JOB_ENUM_OFFER = 0;
    private static final int JOB_ENUM_CURRENT = 1;

    private long _ID;
    private String title;
    private String company;
    private String city;
    private String state;
    private int costOfLivingAdjustment;
    // Money is stored in cents. Any presentation will be converted to dollars
    private int yearlySalary;
    private int yearlyBonus;
    private int allowedTeleworkDays;
    private int leaveTimeDays;
    private int gymAllowance;
    private int status;
    private ComparisonSettings comparisonSettings;

    private Job(long _ID, String title, String company, String city, String state,
                int costOfLivingAdjustment, int yearlySalary, int yearlyBonus,
                int allowedTeleworkDays, int leaveTimeDays, int gymAllowance, int status) {
        this._ID = _ID;
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.costOfLivingAdjustment = costOfLivingAdjustment;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.allowedTeleworkDays = allowedTeleworkDays;
        this.leaveTimeDays = leaveTimeDays;
        this.gymAllowance = gymAllowance;
        this.status = status;
    }

    private Job(Cursor cursor) {
        this._ID = cursor.getLong(cursor.getColumnIndexOrThrow(JobEntry._ID));
        this.title = cursor.getString(cursor.getColumnIndexOrThrow (JobEntry.COLUMN_NAME_TITLE));
        this.company = cursor.getString(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_COMPANY));
        this.city = cursor.getString(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_CITY));
        this.state = cursor.getString(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_STATE));
        this.costOfLivingAdjustment = cursor.getInt(cursor.getColumnIndexOrThrow (JobEntry.COLUMN_NAME_COST_OF_LIVING_INDEX));
        this.yearlySalary = cursor.getInt(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_YEARLY_SALARY));
        this.yearlyBonus = cursor.getInt(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_YEARLY_BONUS));
        this.allowedTeleworkDays = cursor.getInt(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS));
        this.leaveTimeDays = cursor.getInt(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_LEAVE_TIME_DAYS));
        this.gymAllowance = cursor.getInt(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_GYM_ALLOWANCE));
        this.status =  cursor.getInt(cursor.getColumnIndexOrThrow(JobEntry.COLUMN_NAME_STATUS));
    }

    public static Job getCurrentJob(Context context) {
        Job currentJob = null;
        try (Job.JobDbHelper dbHelper = new Job.JobDbHelper(context)) {
            System.out.println("DBHELPER: " + dbHelper);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String selection = JobEntry.COLUMN_NAME_STATUS + " = ?";
            String[] selectionArgs = { "" + JOB_ENUM_CURRENT };

            Cursor cursor = db.query(
                    JobEntry.TABLE_NAME,
                    jobsProjection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            while(cursor.moveToNext()) {
                currentJob = new Job(cursor);
            }
            cursor.close();
        }

        return currentJob;
    }

    public static List<Job> getAllJobs(Context context, ComparisonSettings comparisonSettings) {

        ArrayList<Job> jobs = new ArrayList<>();

        try (Job.JobDbHelper dbHelper = new Job.JobDbHelper(context)) {
            System.out.println("DBHELPER: " + dbHelper);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    JobEntry.TABLE_NAME,
                    jobsProjection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while(cursor.moveToNext()) {
                Job job = new Job(cursor);
                job.setComparisonSettings(comparisonSettings);
                jobs.add( job );
                System.out.println("DBHELPER: getting job!!" + job.title + " " + job.score());
            }
        }

        return jobs;
    }

    public static Job saveJobOffer(Context context, long id, String title, String company, String city,
                                     String state, String costOfLivingIndex, String yearlySalary,
                                     String yearlyBonus, String allowedTeleworkDays, String leaveTimeDays,
                                     String gymAllowance) {
        return saveJob(context, id, title, company, city, state, costOfLivingIndex, yearlySalary,
                yearlyBonus, allowedTeleworkDays, leaveTimeDays, gymAllowance, "" + JOB_ENUM_OFFER);
    }

    public static Job saveCurrentJob(Context context, long id, String title, String company, String city,
                                 String state, String costOfLivingIndex, String yearlySalary,
                                 String yearlyBonus, String allowedTeleworkDays, String leaveTimeDays,
                                 String gymAllowance) {
        return saveJob(context, id, title, company, city, state, costOfLivingIndex, yearlySalary,
                yearlyBonus, allowedTeleworkDays, leaveTimeDays, gymAllowance, "" + JOB_ENUM_CURRENT);
    }

    public static Job getJob(Context context, long id) {
        Job job = null;
        try (Job.JobDbHelper dbHelper = new Job.JobDbHelper(context)) {
            System.out.println("DBHELPER: " + dbHelper);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String selection = JobEntry._ID + " = ?";
            String[] selectionArgs = { "" + id };

            Cursor cursor = db.query(
                    JobEntry.TABLE_NAME,
                    jobsProjection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            while(cursor.moveToNext()) {
                job = new Job(cursor);
            }
            cursor.close();
        }

        return job;
    }

    private static Job saveJob(Context context, long id, String title, String company, String city,
                                     String state, String costOfLivingIndex, String yearlySalary,
                                     String yearlyBonus, String allowedTeleworkDays, String leaveTimeDays,
                                     String gymAllowance, String status) {
        Job job = null;

        try (Job.JobDbHelper dbHelper = new Job.JobDbHelper(context)) {
            System.out.println("DBHELPER: " + dbHelper);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put(JobEntry.COLUMN_NAME_TITLE, title);
            values.put(JobEntry.COLUMN_NAME_COMPANY, company);
            values.put(JobEntry.COLUMN_NAME_CITY, city);
            values.put(JobEntry.COLUMN_NAME_STATE, state);
            values.put(JobEntry.COLUMN_NAME_COST_OF_LIVING_INDEX, costOfLivingIndex);
            values.put(JobEntry.COLUMN_NAME_YEARLY_SALARY, yearlySalary);
            values.put(JobEntry.COLUMN_NAME_YEARLY_BONUS, yearlyBonus);
            values.put(JobEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS, allowedTeleworkDays);
            values.put(JobEntry.COLUMN_NAME_LEAVE_TIME_DAYS, leaveTimeDays);
            values.put(JobEntry.COLUMN_NAME_GYM_ALLOWANCE, gymAllowance);
            values.put(JobEntry.COLUMN_NAME_STATUS, status);

            if (id == -1) {
                id = db.insert(JobEntry.TABLE_NAME, null, values);
                System.out.println("DBHELPER: Inserting " + id);
            } else {
                String selection = JobEntry._ID + " LIKE ?";
                String[] selectionArgs = {"" + id};

                int count = db.update(
                        JobEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                System.out.println("DBHELPER: Updating " + id + " " + count);
            }
            String findSelection = JobEntry._ID + " LIKE ?";
            String[] findSelectionArgs = {"" + id};

            Cursor cursor = db.query(
                    JobEntry.TABLE_NAME,
                    jobsProjection,
                    findSelection,
                    findSelectionArgs,
                    null,
                    null,
                    null
            );

            while(cursor.moveToNext()) {
                job = new Job(cursor);
            }
            cursor.close();
        }

        return job;

    }

    public long get_ID() {
        return _ID;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getCostOfLivingAdjustment() {
        return costOfLivingAdjustment;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public double getAdjustedYearlySalary() {
        return yearlySalary / costOfLivingAdjustment;
    }

    public double getYearlyBonus() {
        return yearlyBonus;
    }

    public double getAdjustedYearlyBonus() {
        return yearlyBonus / costOfLivingAdjustment;
    }

    public int getAllowedTeleworkDays() {
        return allowedTeleworkDays;
    }

    public int getLeaveTimeDays() {
        return leaveTimeDays;
    }

    public int getGymAllowance() {
        return gymAllowance;
    }

    public boolean isCurrentJob() {
        return status == JOB_ENUM_CURRENT;
    }

    public boolean isJobOffer() {
        return status == JOB_ENUM_OFFER;
    }

    public ComparisonSettings getComparisonSettings() {
        return comparisonSettings;
    }

    public void setComparisonSettings(ComparisonSettings comparisonSettings) {
        this.comparisonSettings = comparisonSettings;
    }

    public double score() {
        double score = 1;
        if (comparisonSettings != null) {
            score = comparisonSettings.getYearlySalaryWeight() / comparisonSettings.getWeightSum() * getAdjustedYearlySalary();
            score += comparisonSettings.getYearlyBonusWeight() / comparisonSettings.getWeightSum() * getAdjustedYearlyBonus();
            score += comparisonSettings.getGymAllowanceWeight() / comparisonSettings.getWeightSum() * gymAllowance;
            score += comparisonSettings.getLeaveTimeDaysWeight() / comparisonSettings.getWeightSum() * (leaveTimeDays * getAdjustedYearlySalary() / 260);
            score -= comparisonSettings.getAllowedTeleworkDaysWeight() / comparisonSettings.getWeightSum() * ((260 - 52 * allowedTeleworkDays) * (getAdjustedYearlySalary() / 260) / 8);
        }
        return score;
    }

    @Override
    public int compareTo(Job job) {
        return ((int) job.score()) - ((int)this.score());
    }


    public static class JobEntry implements BaseColumns {
        public static final String TABLE_NAME = "jobs";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_COST_OF_LIVING_INDEX = "cost_of_living_index";
        public static final String COLUMN_NAME_YEARLY_SALARY = "yearly_salary";
        public static final String COLUMN_NAME_YEARLY_BONUS = "yearly_bonus";
        public static final String COLUMN_NAME_ALLOWED_TELEWORK_DAYS = "allowed_telework_days";
        public static final String COLUMN_NAME_LEAVE_TIME_DAYS = "leave_time_days";
        public static final String COLUMN_NAME_GYM_ALLOWANCE = "gym_allowance";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + JobEntry.TABLE_NAME + " (" +
                    JobEntry._ID + " INTEGER PRIMARY KEY," +
                    JobEntry.COLUMN_NAME_TITLE + " TEXT," +
                    JobEntry.COLUMN_NAME_COMPANY + " TEXT," +
                    JobEntry.COLUMN_NAME_CITY + " TEXT," +
                    JobEntry.COLUMN_NAME_STATE + " TEXT," +
                    JobEntry.COLUMN_NAME_COST_OF_LIVING_INDEX + " INTEGER," +
                    JobEntry.COLUMN_NAME_YEARLY_SALARY + " REAL," +
                    JobEntry.COLUMN_NAME_YEARLY_BONUS + " REAL," +
                    JobEntry.COLUMN_NAME_ALLOWED_TELEWORK_DAYS + " INTEGER," +
                    JobEntry.COLUMN_NAME_LEAVE_TIME_DAYS + " INTEGER," +
                    JobEntry.COLUMN_NAME_GYM_ALLOWANCE + " INTEGER," +
                    JobEntry.COLUMN_NAME_STATUS + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + JobEntry.TABLE_NAME;

    public static class JobDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "JobCompare.db";

        public JobDbHelper(Context context) {
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
