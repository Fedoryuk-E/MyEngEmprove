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

public class ActivityDictionary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
    }


    public void onClickSave(View view) {
        insertWords();
        // Закрываем активность
        finish();
    }

    private void insertWords() {
        // Считываем данные из текстовых полей
        EditText mWordEditText = (EditText) findViewById(R.id.editTextWord);
        EditText mTansEditText = (EditText) findViewById(R.id.editTextTrandlation);
        EditText mUnitEditText = (EditText) findViewById(R.id.unit);
        String word = mWordEditText.getText().toString().trim();
        String translation = mTansEditText.getText().toString().trim();
        String unit = mUnitEditText.getText().toString().trim();

        EngDbHelper mDbHelper = new EngDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EngContract.NewWord.COLUMN_WORD, word);
        values.put(EngContract.NewWord.COLUMN_TRANSLATION, translation);
        values.put(EngContract.NewWord.COLUMN_COUNT, 1);
        values.put(EngContract.NewWord.COLUMN_GUESS, 0);
        values.put(EngContract.NewWord.COLUMN_UNIT, unit);


        // Вставляем новый ряд в базу данных и запоминаем его идентификатор
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
