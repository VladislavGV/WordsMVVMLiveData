package ru.synergy.wordsmvvmlivedata.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import ru.synergy.wordsmvvmlivedata.R;

public class NewWordActivity extends AppCompatActivity {// наследуемся

    public static final String EXTRA_REPLY = "ru.synergy.wordsmvvmlivedata.reply"; // добавл экстра реплай в виде ссылки

    private EditText mEditWordView; // создаем

    @Override
    protected void onCreate(Bundle savedInstanceState) {// делает логику
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);//
        mEditWordView = findViewById(R.id.edit_word);// находим едит ворд, который вводит юзер

        final Button button = findViewById(R.id.button_save); // кнопка сайв
        button.setOnClickListener( view-> {//лямбда
            Intent replyintent = new Intent();// импортируем  и слкдаываем пустой интент
            if(TextUtils.isEmpty(mEditWordView.getText())){ //проверяем слова на правильность которые ввели
                setResult(RESULT_CANCELED, replyintent);// если текст пустой нет смыла отправлять результат
            }else{
                String word = mEditWordView.getText().toString();// тогда достаем как геттекст
                replyintent.putExtra(EXTRA_REPLY, word);// складываем в экстра
                setResult(RESULT_OK, replyintent);//сообщение что все ОК, и отправляем в реплайнтер
            }
            finish();//заканчиваем активность и отправляем резульатт обратно
        });

    }
}