package ru.synergy.wordsmvvmlivedata.data.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity; // ентити относится к руму, здесь это можно понять
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName="word_table")//это сущность, библиотека роом подключится сама
public class Word {

    @PrimaryKey
    @NonNull // валидация
    @ColumnInfo(name = "word") // коллнейм, поле будет ворд
    private String mWord; //сам контент этой стринги

    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    } // конструктор, кототорый принимает стриинг, проверяет его и вставляет в нужное место

    @NonNull
    public String getWord() {
        return mWord;
    } // геттер класс
}
