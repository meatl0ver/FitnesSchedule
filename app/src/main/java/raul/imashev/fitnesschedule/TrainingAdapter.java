package raul.imashev.fitnesschedule;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import raul.imashev.fitnesschedule.data.Training;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder> {
    List<Training> trainings;
    public TrainingAdapter() {
        trainings = new ArrayList<>();
    }

    @NonNull
    @Override
    public TrainingAdapter.TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item, parent, false);
        return new TrainingAdapter.TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.TrainingViewHolder holder, int position) {
        Training training = trainings.get(position);
        holder.textViewName.setText(training.getName());
        holder.textViewStartTime.setText(training.getStartTime());
        holder.textViewEndTime.setText(training.getEndTime());
        holder.textViewTeacher.setText(training.getTeacher());
        holder.textViewPlace.setText(training.getPlace());
        holder.textViewDescription.setText(training.getDescription());
        holder.textViewWeekDay.setText(training.getWeekDay());

    }
    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewStartTime;
        private TextView textViewEndTime;
        private TextView textViewTeacher;
        private TextView textViewPlace;
        private TextView textViewDescription;
        private TextView textViewWeekDay;

        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewStartTime = itemView.findViewById(R.id.textViewStartTime);
            textViewEndTime = itemView.findViewById(R.id.textViewEndTime);
            textViewTeacher = itemView.findViewById(R.id.textViewTeacher);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewWeekDay = itemView.findViewById(R.id.textViewWeekDay);

        }
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }
}
