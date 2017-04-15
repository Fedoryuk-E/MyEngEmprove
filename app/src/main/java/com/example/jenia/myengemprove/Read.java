package com.example.jenia.myengemprove;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.jenia.myengemprove.data.EngContract;
import com.example.jenia.myengemprove.data.EngDbHelper;

public class Read extends AppCompatActivity {
    private EngDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        mDbHelper = new EngDbHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo() {
        // Создадим и откроем для чтения базу данных
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Зададим условие для выборки - список столбцов
        String[] projection = {
                EngContract.NewWord._ID,
                EngContract.NewWord.COLUMN_WORD,
                EngContract.NewWord.COLUMN_TRANSLATION
        };

        // Делаем запрос
        Cursor cursor = db.query(
                EngContract.NewWord.TABLE_NAME,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки

        TextView displayTextView = (TextView) findViewById(R.id.text_view_info);
        displayTextView.setMovementMethod(new ScrollingMovementMethod());

        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " слов.\n\n");
            displayTextView.append(EngContract.NewWord._ID + " - " +
                    EngContract.NewWord.COLUMN_WORD + " - " +
                    EngContract.NewWord.COLUMN_TRANSLATION + " - " + "\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(EngContract.NewWord._ID);
            int wordColumnIndex = cursor.getColumnIndex(EngContract.NewWord.COLUMN_WORD);
            int translateColumnIndex = cursor.getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION);

            // Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor.getInt(idColumnIndex);
                String currentWord = cursor.getString(wordColumnIndex);
                String currentTrans = cursor.getString(translateColumnIndex);

                // Выводим значения каждого столбца
                displayTextView.append(("\n" + currentID + " - " +
                        currentWord + " - " +
                        currentTrans + " - "
                ));
            }
        } finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
    }
}
