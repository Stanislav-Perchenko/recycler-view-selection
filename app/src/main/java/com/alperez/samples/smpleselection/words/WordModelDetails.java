package com.alperez.samples.smpleselection.words;

import android.support.annotation.Nullable;

import com.alperez.samples.smpleselection.model.WordModel;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * Created by stanislav.perchenko on 11/18/2018
 */
public class WordModelDetails extends ItemDetailsLookup.ItemDetails<WordModel> {

    private final int adapterPosition;
    private final WordModel selectionKey;

    public WordModelDetails(int adapterPosition, WordModel selectionKey) {
        this.adapterPosition = adapterPosition;
        this.selectionKey = selectionKey;
    }

    @Override
    public int getPosition() {
        return adapterPosition;
    }

    @Nullable
    @Override
    public WordModel getSelectionKey() {
        return selectionKey;
    }
}
