package com.alperez.samples.smpleselection.words;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.alperez.samples.smpleselection.model.WordModel;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * Created by stanislav.perchenko on 11/18/2018
 */
public class WordModelLookup extends ItemDetailsLookup<WordModel> {

    private final RecyclerView recyclerView;

    public WordModelLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<WordModel> getItemDetails(@NonNull MotionEvent ev) {
        View child = recyclerView.findChildViewUnder(ev.getX(), ev.getY());
        if (child != null) {
            RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
            if (vh instanceof WordAdapter.WordViewHolder) {
                return ((WordAdapter.WordViewHolder) vh).getItemDetail();
            }
        }
        return null;
    }
}
