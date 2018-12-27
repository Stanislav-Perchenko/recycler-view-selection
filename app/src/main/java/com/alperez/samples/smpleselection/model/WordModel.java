package com.alperez.samples.smpleselection.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by stanislav.perchenko on 11/18/2018
 */
@AutoValue
public abstract class WordModel implements Parcelable, Cloneable {

    public abstract int id();
    public abstract String text();

    public static WordModel create(int id, String text) {
        return new AutoValue_WordModel(id, text);
    }

    public WordModel clone() {
        return new AutoValue_WordModel(id(), text());
    }
}
