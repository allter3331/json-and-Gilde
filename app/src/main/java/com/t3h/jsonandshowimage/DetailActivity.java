package com.t3h.jsonandshowimage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity{

    TextView textView;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        String text = intent.getStringExtra("detail");
        textView = (TextView) findViewById(R.id.tv_detail);
        textView.setText(text);

    }
}
