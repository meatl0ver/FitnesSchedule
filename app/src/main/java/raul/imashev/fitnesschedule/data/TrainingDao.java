package raul.imashev.fitnesschedule.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrainingDao {

    @Query("SELECT * FROM trainings")
    LiveData<List<Training>> getAllTrainings();

    @Query("DELETE FROM trainings")
    void deleteAllTrainings();

    @Insert
    void insertTraining(Training training);
}
