package ru.synergy.wordsmvvmlivedata.ui.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import ru.synergy.wordsmvvmlivedata.data.room.Word;

public class WordListAdapter extends ListAdapter<Word, WordViewHolder> { //наследуемся

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) { //конструктор
        super(diffCallback);
    }
//имплементируем методы

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//
        return WordViewHolder.create(parent); // вызывать онкрейт и передавать туда парент
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word current  = getItem(position);// есть ворд карент и у него будет гетайтем позишн
        holder.bind(current.getWord()); //холдер называет бинд и здесь будет карент гет ворд
    }


    public static class WordDiff extends DiffUtil.ItemCallback<Word>{
        // для проверки разницы между словами создем дифутил и даем на вход ворд и имплементруем два метода

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem; // проверяем
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord()); //если оно иквелкс то берем
        }
    }
}
