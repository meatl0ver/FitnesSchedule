package raul.imashev.fitnesschedule.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static TrainingDatabase database;
    private LiveData<List<Training>> trainings;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = TrainingDatabase.getInstance(getApplication());
        trainings = database.trainingDao().getAllTrainings();
    }

    public LiveData<List<Training>> getTrainings() {
        return trainings;
    }


    public void deleteAllTrainigs() {
        new DeleteTrainingsTask().execute();
    }
    private static class DeleteTrainingsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            database.trainingDao().deleteAllTrainings();
            return null;
        }
    }

    public void insertTraining(Training training) {
        new InsertTask().execute(training);
    }

    private static class InsertTask extends AsyncTask<Training, Void, Void> {
        @Override
        protected Void doInBackground(Training... trainings) {
            if (trainings != null && trainings.length > 0) {
                database.trainingDao().insertTraining(trainings[0]);
            }
            return null;
        }
    }
}
