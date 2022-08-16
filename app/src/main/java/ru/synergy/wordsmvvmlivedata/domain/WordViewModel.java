package ru.synergy.wordsmvvmlivedata.domain;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.synergy.wordsmvvmlivedata.data.repository.WordRepositoryRoomImpl;
import ru.synergy.wordsmvvmlivedata.data.room.Word;

public class WordViewModel extends AndroidViewModel { // создаем и наследуемся

    private WordRepository mRepository; //указываем интерейфес а не реализацию
    private final LiveData<List<Word>> mAllWords; //имеем полный набор для схоэжения логики
    //если кто-то что-то добавит в БД это будет здесь в лайфдата и мы сможем отсюда это забирать

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepositoryRoomImpl(application); // жестко передаем вордрепозотори рум импл
        mAllWords = mRepository.getAllWords(); //из репорз получ ворд и передаем его в в аллворд
    }

    public LiveData<List<Word>> getAllWords() {return mAllWords;} // возвращаем лист вородов

    public void insert(Word word){ // может добавлять и читать слова
        mRepository.insert(word); //подклбючаем и передаем ворд
    } //эти методы будут дергаться из активности

    public void deleteAll(){
        mRepository.deleteAll();
        Toast.makeText(getApplication(), "All words have been removed", Toast.LENGTH_SHORT).show();
        //при удалении выводим сообщение
        // в репозитории это писать глупо
    }
}
