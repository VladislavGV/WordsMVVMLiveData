package ru.synergy.wordsmvvmlivedata.data.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false) //указываем что энтитис один- ворд класс, версию,экспорт схема
public abstract class WordRoomDatabase extends RoomDatabase {// наследуемся

    abstract WordDao wordDao();// создаем абстрактный класс вордДао

    public WordDao getWordDao() { return wordDao(); } // геттер для вордДао и возвращаем его

    private static  volatile WordRoomDatabase INSTANCE; // делает элемент сингтон -иинстанс
    private static final int NUMBER_OF_THREADS = 4; // многопоточность, 4 потока
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    // экзекьютор сервис, ваще не понял

    public static WordRoomDatabase getDatabase(final Context context) { // метод гетдатабейс и передаем контекст, чтобы его отдали
        if(INSTANCE == null){ // логика синглтона
            synchronized(WordRoomDatabase.class){//защита от многопоточности
                if(INSTANCE == null){ // еще раз проверяем
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), // иснтан создан как рум
                            WordRoomDatabase.class, "word_database")// имя базы данных
                            .addCallback(sRoomDatabaseCallback) //вызываем коллбеки, выполныем sql команды
                            .build();//строим
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) { // создаем коллбек с назван РумДатаБейсКоллб
            super.onCreate(db); // создаем БД

            databaseWriteExecutor.execute(()->{ //лямбда функция которая на вход ничего не принимает,
                // стрелка указ на реализация функции
                WordDao dao = INSTANCE.wordDao(); // и создаем БД
                dao.deleteAll(); // удаляем все что есть

                Word word = new Word("Hello"); // созд новый ворд
                dao.insert(word); //передаем ворд на вставку

                word = new Word("World"); // новое слово
                dao.insert(word);// передаем ворд на вставку
            });
        }
    };

}
