package raul.imashev.fitnesschedule.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Training.class}, version = 1, exportSchema = false)
public abstract class TrainingDatabase extends RoomDatabase {
    private static final String DB_NAME = "trainings.db";
    private static TrainingDatabase database;
    private static final Object LOCK = new Object();

    public static TrainingDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, TrainingDatabase.class, DB_NAME).build();

            }
        }
        return database;
    }

    public abstract TrainingDao trainingDao();
}
