package ru.synergy.wordsmvvmlivedata.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.synergy.wordsmvvmlivedata.data.room.Word;

public interface WordRepository {

    LiveData<List<Word>> getAllWords(); //доступ к реализации, получать слова

    void insert(Word word); // создаем класс ворд, то что он должен реализовывать

    void deleteAll(); // создаем класс делетеОл, кдалять все слова кот есть в БД
}
