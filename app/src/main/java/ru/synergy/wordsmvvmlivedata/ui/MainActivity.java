package ru.synergy.wordsmvvmlivedata.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.synergy.wordsmvvmlivedata.R;
import ru.synergy.wordsmvvmlivedata.data.room.Word;
import ru.synergy.wordsmvvmlivedata.domain.WordViewModel;
import ru.synergy.wordsmvvmlivedata.ui.recycler.WordListAdapter;

public class MainActivity extends AppCompatActivity { //

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1; //

    public WordViewModel mWordViewModel; // создаем класс

    public ActivityResultLauncher<Intent> mStrartForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),new ActivityResultCallback<ActivityResult>(){
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent intent = result.getData();
            int resCode = result.getResultCode();
            onActivityResultHandler(resCode,  intent);
        }
    });
    
    // на этом моменте надо писать разметку

    @Override
    protected void onCreate(Bundle savedInstanceState) {//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerview = findViewById(R.id.recyclerview);// находим ресайклер вью

        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff()); // применяем адаптер, чтобы нашел
        recyclerview.setAdapter(adapter); // ставим адаптер
        recyclerview.setLayoutManager(new LinearLayoutManager(this));//выствляем лэйаут менеджер
//заимплементируем вью модел через вью модел провайдер, который получается из контекста взис
        mWordViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, words -> {// достаем в форграунд гет олл вордс и обзервим его.
            // на вход ворд и дальше условный лист элементов, и хотим чтобы лайф дата сам выставлял эти данные
            adapter.submitList(words);// сами выставляются на экран
        });

        FloatingActionButton fab = findViewById(R.id.fab);//кнопка с плючом, находим и инициализируем
        fab.setOnClickListener( view -> { // лямбда
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);// вызываем интент и переходим в ньювордактив класс
            mStrartForResult.launch(intent);//
        });


        Button button = (Button) findViewById(R.id.delete); // находим и инициализируем кнопку делете
        button.setOnClickListener( view -> { // по нажатию - говорили детеле олл
            mWordViewModel.deleteAll();// здесь делете олл
        });



    }


    public void onActivityResultHandler(int resultCode, Intent data) {// по результату добавления получ ответ

        if (resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));// реагируем на стату кода
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}