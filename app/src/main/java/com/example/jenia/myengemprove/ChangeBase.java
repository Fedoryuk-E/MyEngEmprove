package com.example.jenia.myengemprove;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jenia.myengemprove.data.EngContract;
import com.example.jenia.myengemprove.data.EngDbHelper;

import static com.example.jenia.myengemprove.data.EngContract.NewWord.COLUMN_TRANSLATION;
import static com.example.jenia.myengemprove.data.EngContract.NewWord.COLUMN_WORD;
import static com.example.jenia.myengemprove.data.EngContract.NewWord.TABLE_NAME;

public class ChangeBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_base);
    }


    public void onClickChange(View view) {
        EngDbHelper mDbHelper = new EngDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO "+ TABLE_NAME +" (" + COLUMN_WORD
                + ", " + COLUMN_TRANSLATION  + ") VALUES ( this way'\n);");
    };
        /*finish();*/


    private void changeWord() {
        EditText mSerchText = (EditText) findViewById(R.id.editTextSearch);
        EditText mChangeText = (EditText) findViewById(R.id.editTextChange);
        String search = mSerchText.getText().toString().trim();
        String change = mChangeText.getText().toString().trim();
        EngDbHelper mDbHelper = new EngDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        /*values.put(EngContract.NewWord.COLUMN_WORD, word);
        values.put(EngContract.NewWord.COLUMN_TRANSLATION, translation);*/
        values.put(EngContract.NewWord.COLUMN_WORD, search);
        db.update(EngContract.NewWord.TABLE_NAME,
                values,
                EngContract.NewWord.COLUMN_WORD + "= ?", new String[]{change});

        long newRowId = db.insert(EngContract.NewWord.TABLE_NAME, null, values);

        // Выводим сообщение в успешном случае или при ошибке
        if (newRowId == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Ошибка при заведении слова", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Слово заведено под номером: " + newRowId, Toast.LENGTH_SHORT).show();
        }




    }
}
