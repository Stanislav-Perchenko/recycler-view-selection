package com.alperez.samples.demoactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.alperez.samples.R;
import com.alperez.samples.smpleselection.model.WordModel;
import com.alperez.samples.smpleselection.words.WordAdapter;
import com.alperez.samples.smpleselection.words.WordKeyProvider;
import com.alperez.samples.smpleselection.words.WordModelLookup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;

/**
 * Created by stanislav.perchenko on 11/18/2018
 */
public class SimpleSelectionActivity extends BaseDemoActivity {

    private RecyclerView vRecycler;
    private ActionMode actionMode;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_simple_selection;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vRecycler = findViewById(R.id.recycler);
        vRecycler.setLayoutManager(new LinearLayoutManager(this));
        vRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        List<WordModel> items = new ArrayList<>(100);
        for (int i=0; i<100; i++) items.add(WordModel.create(i, "item #"+i));

        WordAdapter adapter = new WordAdapter(this, items);
        vRecycler.setAdapter(adapter);


        final SelectionTracker<WordModel> tracker = new SelectionTracker.Builder<>("someId", vRecycler, new WordKeyProvider(items), new WordModelLookup(vRecycler), StorageStrategy.createParcelableStorage(WordModel.class)).build();
        tracker.addObserver(new SelectionTracker.SelectionObserver() {
            @Override
            public void onSelectionChanged() {
                if (!tracker.hasSelection()) {
                    stopActionMode();
                } else {
                    if (actionMode == null) actionMode = startSupportActionMode(tracker);
                    actionMode.setTitle("Selected: " + tracker.getSelection().size());
                }
            }
        });

        adapter.setSelectionTracker(tracker);
    }

    @Nullable
    public ActionMode startSupportActionMode(final SelectionTracker<WordModel> tracker) {
        return super.startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.action_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
                if (item.getItemId() == R.id.action_clear) {
                    actionMode.finish();
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                tracker.clearSelection();
            }
        });
    }


    private void stopActionMode() {
        if (actionMode != null) {
            actionMode.finish();
            actionMode = null;
        }
    }


}
