package com.example.ex113;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        takeText();
    }

    private void takeText() {
        SharedPreferences settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        String str = settings.getString("str","");
        tv.setText(str);
        et.setText(str);
    }

    public void save(View view) {
        tv.setText(et.getText().toString());
        SharedPreferences.Editor editor = getSharedPreferences("PREFS_NAME", MODE_PRIVATE).edit();
        editor.putString("str", et.getText().toString());
        editor.commit();
    }

    public void reset(View view) {
        tv.setText("");
        SharedPreferences.Editor editor = getSharedPreferences("PREFS_NAME", MODE_PRIVATE).edit();
        editor.putString("str","");
        editor.commit();
    }

    public void exit(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("PREFS_NAME", MODE_PRIVATE).edit();
        editor.putString("str", et.getText().toString());
        editor.commit();
        finish();
    }
}