/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.example.com.dictionaryproviderexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.provider.UserDictionary.Words;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends ActionBarActivity {

    //FIELDS
    private static final String[] COLUMNS_TO_BE_BOUND={
            Words.WORD,
            Words.FREQUENCY};
    private static final int[] LAYOUT_ITEMS_TO_FILL=new int[] {
            R.id.header,
            R.id.descr};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the TextView which will be populated with the Dictionary ContentProvider data.
        ListView dictTextView = (ListView) findViewById(R.id.dictionary_text_view);

        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();

        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        Log.e("TEST", Words.CONTENT_URI.toString());

        try {
            //String message = "The UserDictionary contains " + cursor.getCount() + " words.\n" + "COLUMNS: _ID - frequency - word\n";
            //StringBuilder row_text = new StringBuilder();

//            cursor.moveToFirst();
//            do {
//                //get column index from column name
//                int columnWord = cursor.getColumnIndex(Words.WORD);
//                int columnID = cursor.getColumnIndex(Words._ID);
//                int columnFrequency = cursor.getColumnIndex(Words.FREQUENCY);
//
//                //tha mporousa na kanw append apeutheias sto settext tou textview
//                //row_text.append(cursor.getInt(columnID) + " - " + cursor.getInt(columnFrequency) + " - " + cursor.getString(columnWord) + "\n");
//
//            }
//            while (cursor.moveToNext());
            //dictTextView.setText(message + row_text);
            //automata kahte stoixeio tou columns tha eisaxthei sto antistoixo tou layout_items_to_fill (to prwto me to prwto kok)
            SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,
                    R.layout.two_line_list_item,
                    cursor,
                    COLUMNS_TO_BE_BOUND,
                    LAYOUT_ITEMS_TO_FILL,
                    0);

            //attach adapter to the listview
            dictTextView.setAdapter(adapter);
        }
        finally {
            //always close the cursor to avoid memory leaks
            //ektos an sindew simplecursoradapter
            //cursor.close();
        }

    }
}
