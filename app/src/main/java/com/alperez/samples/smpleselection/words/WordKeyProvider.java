package com.alperez.samples.smpleselection.words;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alperez.samples.smpleselection.model.WordModel;

import java.util.List;

import androidx.recyclerview.selection.ItemKeyProvider;

/**
 * Created by stanislav.perchenko on 11/18/2018
 */
public class WordKeyProvider extends ItemKeyProvider<WordModel> {

    private final List<WordModel> items;

    public WordKeyProvider(List<WordModel> items) {
        super(ItemKeyProvider.SCOPE_CACHED);
        this.items = items;
    }

    @Nullable
    @Override
    public WordModel getKey(int position) {
        return (position >= 0 && position < items.size()) ? items.get(position) : null;
    }

    @Override
    public int getPosition(@NonNull WordModel key) {
        return items.indexOf(key);
    }
}
