package ru.synergy.wordsmvvmlivedata.ui.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.synergy.wordsmvvmlivedata.R;

public class WordViewHolder extends RecyclerView.ViewHolder{ // наследуемся
    private final TextView wordItemView; // должен содержать текст, импортируем элемент

    private WordViewHolder(View itemView) {//закидывает вью и айтем вью
        super(itemView); // конструктор
        wordItemView = itemView.findViewById(R.id.textView); //находим тектвью
    }

    public void bind(String text){
        wordItemView.setText(text);
    } //принммать будет стринг текст

    static WordViewHolder create(ViewGroup parent){// выставляем текст и получаем ниже
        View view = LayoutInflater.from(parent.getContext()) // статичный метод
                .inflate(R.layout.recyclerview_item, parent, false);//
        return new WordViewHolder(view); //возвращаем как новый элемент
    }
}
