package com.example.ex113;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * The type Main activity.
 *
 *  @author Ori Ofek <oriofek106@gmail.com> 14/12/2020
 *  @version 1.0
 *  @since 23/12/2020
 *  sort description:
 *  this is the activty the implement the exericse that my teacher gave...
 */
public class MainActivity extends AppCompatActivity {
    Intent si;
    String filePath = "test.txt";
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        tv.setText(takeText());
    }

    /**
     * takeText.
     * short dec: take the data
     *
     * @param
     * @return	return the contance of the file
     */
    private String takeText() {
        String strrd = "";

        try {
            FileInputStream fis= openFileInput(filePath);

            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            strrd=sb.toString();
            isr.close();


        } catch (Exception e) {
        }


        return strrd;
    }

    /**
     * save.
     * short dec:save the text in the file
     *
     * <p>
     * @param
     * @return	none
     */
    public void save() {
        String strInFile = takeText();
        String strToPut = strInFile + " " + et.getText().toString();
        try {
            if(strInFile == "")
            {
                FileOutputStream fos = openFileOutput(filePath,MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(et.getText().toString());

                bw.close();
            }
            else
            {
                FileOutputStream fos = openFileOutput(filePath,MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(strToPut);

                bw.close();
            }


        } catch (Exception e) {
        }

        tv.setText(takeText());
    }

    /**
     * reset.
     * short dec: reset it
     *
     * <p>
     *      View view
     * @param	view - see which button pressed
     * @return	none
     */
    public void reset(View view) {
        et.setText("");
        tv.setText("");
    }

    /**
     * exit.
     * short dec: exit and save
     *
     * <p>
     *      View view
     * @param	view - see which button pressed
     * @return	none
     */
    public void exit(View view) {
        save();
        finish();
    }

    /**
     * doSaving.
     * short dec: save it when He clicked (He is meant to the user)
     *
     * <p>
     *      View view
     * @param	view - see which button pressed
     * @return	none
     */
    public void doSaving(View view) {
        save();
    }

    /**
     * onCreateContextMenu
     * Short description.
     * onCreateContextMenu listener use for the ContextMenu
     * <p>
     *     ContextMenu menu
     *     View v
     *     ContextMenu.ContextMenuInfo menuInfo
     *
     * @param  menu - the object,v - the item that selected ,menuInfo - the info
     * @return	true if it success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.generalmenu, menu);
        return true;
    }

    /**
     * onOptionsItemSelected
     * Short description.
     * what happen if an item was selected
     * <p>
     *     MenuItem item
     *
     * @param  item - the menuItem
     * @return	true if it success
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String whatClicked = (String) item.getTitle();

        if(whatClicked.equals("credits"))
        {
            si = new Intent(this, credits.class);
            startActivity(si);
        }

        return  true;
    }
}