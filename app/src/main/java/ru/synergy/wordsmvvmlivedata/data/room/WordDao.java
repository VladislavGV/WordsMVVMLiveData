package ru.synergy.wordsmvvmlivedata.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao; // импорт интерфейса
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC") // пишеа СКЛ - БД "ворд табл"
    public LiveData<List<Word>> getAlphabetizedWords(); // метод возвращает слова в алфав порядке

    @Insert(onConflict = OnConflictStrategy.IGNORE) // пишем команду инсерт, выбираем стратегию - игнор /аборт(для боевого приложения)
    public void insert(Word word); // инсертим ворд

    @Query("DELETE FROM word_table")// делитим в БД
    public void deleteAll();// удаляем все, всю БД
}
