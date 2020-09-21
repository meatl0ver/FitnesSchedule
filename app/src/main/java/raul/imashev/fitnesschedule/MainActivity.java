package raul.imashev.fitnesschedule;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import raul.imashev.fitnesschedule.data.MainViewModel;
import raul.imashev.fitnesschedule.data.Training;
import raul.imashev.fitnesschedule.utils.JSONUtils;
import raul.imashev.fitnesschedule.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private TrainingAdapter adapter;

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SharedPrefferences для проверки на первый запуск
        prefs = getSharedPreferences("firstTimeRun", MODE_PRIVATE);
        //Подключение ViewModel для работы с данными
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //Установка адаптера для ресайклера
        recyclerView = findViewById(R.id.recyclerViewTrainings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrainingAdapter();
        recyclerView.setAdapter(adapter);

        //Подключение значений из бд в адаптер
        LiveData<List<Training>> trainingsFromLiveData = viewModel.getTrainings();
        trainingsFromLiveData.observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(@Nullable List<Training> trainings) {
                adapter.setTrainings(trainings);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Если приложение запущено в первый раз, происходит загружка данных
        if (prefs.getBoolean("firstrun", true)) {
            downloadData();
            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }

    //Метод для загрузки данных и обновления данных
    private void downloadData() {
        //Проверка на подключение к интернету
        if (!isOnline()) {
            Toast.makeText(this, "Internet connection required", Toast.LENGTH_LONG).show();
        } else {
            JSONArray jsonArray = NetworkUtils.getJSONFromNetwork();
            //Метод создают объект классов из JSON массива
            ArrayList<Training> trainings = JSONUtils.getTrainingsFromJSON(jsonArray);
            //В случае если база уже создана, происходит удаление существующих элементов и затем, добавление новых
            if (!trainings.isEmpty()) {
                viewModel.deleteAllTrainigs();
                for (Training training : trainings) {
                    viewModel.insertTraining(training);
                }
            }
        }
    }
    //Метод для проверки подключения к сети
    protected boolean isOnline() {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        result = cm.getActiveNetworkInfo() != null;
        return result;
    }

    public void onClickRefreshButton(View view) {
        downloadData();
    }
}