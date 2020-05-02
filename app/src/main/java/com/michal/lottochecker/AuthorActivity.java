package com.michal.lottochecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class AuthorActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    private void setCustomToolbarView(){
        Toolbar toolbar = findViewById(R.id.mCustomToolbar);
        AppCompatImageView img = toolbar.findViewById(R.id.toolbarImageView);
        img.setImageResource(R.drawable.lotto);
        TextView title = toolbar.findViewById(R.id.toolbarTitle);
        title.setText("Lotto Checker");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        setCustomToolbarView();
    }
}
