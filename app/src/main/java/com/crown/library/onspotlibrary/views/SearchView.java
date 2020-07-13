package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.crown.library.onspotlibrary.R;

public class SearchView extends LinearLayout {

    private EditText inputET;

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchView, 0, 0);
        String hint = a.getString(R.styleable.SearchView_hint);
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_search, this, true);

        inputET = findViewById(R.id.et_vs_input);

        setHint(hint);
    }

    public void setHint(String hint) {
        inputET.setHint(hint);
    }

    public EditText getField() {
        return this.inputET;
    }
}
