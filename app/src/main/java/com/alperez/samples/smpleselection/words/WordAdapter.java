package com.alperez.samples.smpleselection.words;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alperez.samples.R;
import com.alperez.samples.smpleselection.model.WordModel;

import java.util.List;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;

/**
 * Created by stanislav.perchenko on 11/18/2018
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {


    private final LayoutInflater inflater;
    private final List<WordModel> dataItems;
    private SelectionTracker<WordModel> selectionTracker;

    public WordAdapter(Context ctx, List<WordModel> dataItems) {
        inflater = LayoutInflater.from(ctx);
        this.dataItems = dataItems;
    }


    public void setSelectionTracker(SelectionTracker<WordModel> selectionTracker) {
        this.selectionTracker = selectionTracker;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WordViewHolder(inflater.inflate(R.layout.item_word, parent, false));
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position, @NonNull List<Object> payloads) {
        final WordModel item = dataItems.get(position);
        holder.setActivatedState(selectionTracker.isSelected(item));


        if (!payloads.contains(SelectionTracker.SELECTION_CHANGED_MARKER)) {
            holder.bindData(position, item);
        }

    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView vTxtTitle;

        public WordViewHolder(@NonNull View iv) {
            super(iv);
            vTxtTitle = (TextView) iv.findViewById(R.id.item_word_text);
        }

        public ItemDetailsLookup.ItemDetails<WordModel> getItemDetail() {
            return (position >= 0 && (item != null)) ? new WordModelDetails(position, item) : null;
        }

        void setActivatedState(boolean isActivated) {
            super.itemView.setActivated(isActivated);
        }

        private WordModel item;
        private int position = -1;

        void bindData(int position, WordModel item) {
            this.position = position;
            vTxtTitle.setText((this.item = item).text());
        }
    }
}
