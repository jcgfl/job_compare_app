package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {

    private Context context;
    private List<Job> jobs;
    private int firstSelection = -1;
    private int secondSelection = -1;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param context context to be used by the Job and Comparison Settings Class
     * by RecyclerView.
     */
    public JobsAdapter(Context context, ComparisonSettings comparisonSettings) {
        this.context = context;
        jobs = Job.getAllJobs(context, comparisonSettings);
        Collections.sort(jobs);
    }


    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_item, parent, false);

        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.getTitleTextView().setText(job.getTitle());
        holder.getCompanyTextView().setText(job.getCompany());
        if (job.isCurrentJob()) {
            holder.getCurrentCompanyTextView().setVisibility(View.VISIBLE);
        } else if (job.isJobOffer()) {
            holder.getCurrentCompanyTextView().setVisibility(View.INVISIBLE);
        }

        // Set a listener for this entire view
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                System.out.println("Clicked on row: " + clickedPosition);
                if (firstSelection == clickedPosition) {
                    firstSelection = -1;
                    view.setBackgroundColor(view.getResources().getColor(R.color.white));
                } else if (secondSelection == clickedPosition) {
                    secondSelection = -1;
                    view.setBackgroundColor(view.getResources().getColor(R.color.white));
                } else if (firstSelection == -1 && secondSelection == -1 ) {
                    firstSelection = clickedPosition;
                    view.setBackgroundColor(view.getResources().getColor(R.color.background_selected));
                } else if (firstSelection != -1 && secondSelection == -1 ) {
                    secondSelection = clickedPosition;
                    view.setBackgroundColor(view.getResources().getColor(R.color.background_selected));
                }

                if (firstSelection != -1 && secondSelection != -1) {
                    System.out.println("DBH: Launch comparison " + firstSelection + " " + secondSelection);

                    Intent intent = new Intent(context, CompareJobsActivity.class);
                    intent.putExtra(CompareJobsActivity.JOB_ONE, jobs.get(firstSelection).get_ID());
                    intent.putExtra(CompareJobsActivity.JOB_TWO, jobs.get(secondSelection).get_ID());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        View parentView;
        private final TextView titleTextView;
        private final TextView companyTextView;
        private final TextView currentCompanyTextView;

        public JobViewHolder(View view) {
            super(view);
            this.parentView = view;
            view.setClickable(true);
            titleTextView = (TextView) view.findViewById(R.id.titleText);
            companyTextView = (TextView) view.findViewById(R.id.companyText);
            currentCompanyTextView = (TextView) view.findViewById(R.id.currentJobText);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getCompanyTextView() {
            return companyTextView;
        }

        public TextView getCurrentCompanyTextView() {
            return currentCompanyTextView;
        }
    }
}