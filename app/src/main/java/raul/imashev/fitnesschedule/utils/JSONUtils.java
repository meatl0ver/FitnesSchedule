package raul.imashev.fitnesschedule.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import raul.imashev.fitnesschedule.data.Training;

public class JSONUtils {
    private static final String KEY_NAME = "name";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_END_TIME = "endTime";
    private static final String KEY_TEACHER = "teacher";
    private static final String KEY_PLACE = "place";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_WEEK_DAY = "weekDay";

    public static ArrayList<Training> getTrainingsFromJSON(JSONArray jsonArray) {
        ArrayList<Training> trainings = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString(KEY_NAME);
                String startTime = jsonObject.getString(KEY_START_TIME);
                String endTime = jsonObject.getString(KEY_END_TIME);
                String teacher = jsonObject.getString(KEY_TEACHER);
                String place = jsonObject.getString(KEY_PLACE);
                String description = jsonObject.getString(KEY_DESCRIPTION);
                int weekDay = jsonObject.getInt(KEY_WEEK_DAY);
                String day;
                switch (weekDay) {
                    case(1):
                        day = "Понедельник";
                        break;
                    case(2):
                        day = "Вторник";
                        break;
                    case(3):
                    day = "Среда";
                    break;
                    case(4):
                        day = "Четверг";
                        break;
                    case(5):
                        day = "Пятница";
                        break;
                    case(6):
                        day = "Суббота";
                        break;
                    default:
                        day ="Воскресенье";
                }
                trainings.add(new Training(null,name,startTime,endTime,teacher,place,description,day));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return trainings;
    }
}
