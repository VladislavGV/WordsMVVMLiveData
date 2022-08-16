package ru.synergy.wordsmvvmlivedata.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.synergy.wordsmvvmlivedata.data.room.Word;
import ru.synergy.wordsmvvmlivedata.data.room.WordDao;
import ru.synergy.wordsmvvmlivedata.data.room.WordRoomDatabase;
import ru.synergy.wordsmvvmlivedata.domain.WordRepository;

public class WordRepositoryRoomImpl implements WordRepository { //созд класс и импелмен методы
    private WordDao mWordDao; // создаем класс датааксееобъект
    private LiveData<List<Word>> mAllWords; // лист текущих слов

    public WordRepositoryRoomImpl(Application application) { // конструктор передаме класс апликейшн
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application); // БД мы создаем сами здесь
        mWordDao = db.getWordDao(); // получаем из дата бейс
        mAllWords = mWordDao.getAlphabetizedWords(); //по алфавиту вытаскиваем данные
    }

    @Override
    public LiveData<List<Word>> getAllWords() {
        return mAllWords; // на старте получили и вернули, чтобы бы он сам обновлял поле
    } //один раз получаем объект лайфдата и потом его не дергать

    @Override
    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> { //на ввод, лямбда
            mWordDao.insert(word); // на ввод передаем слово которое передали
        });
    }

    @Override
    public void deleteAll() {
        WordRoomDatabase.databaseWriteExecutor.execute(()->{
            mWordDao.deleteAll(); // то же но на удаление
        });
    }
}
