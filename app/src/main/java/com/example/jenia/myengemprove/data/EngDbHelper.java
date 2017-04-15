package com.example.jenia.myengemprove.data;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jenia.myengemprove.R;
import com.example.jenia.myengemprove.data.EngContract.NewWord;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import static com.example.jenia.myengemprove.data.EngContract.NewWord.COLUMN_TRANSLATION;
import static com.example.jenia.myengemprove.data.EngContract.NewWord.COLUMN_WORD;
import static com.example.jenia.myengemprove.data.EngContract.NewWord.TABLE_NAME;

/**
 * Created by Jenia on 22.01.2017.
 */

public class EngDbHelper  extends SQLiteOpenHelper{
    private final Context fContext;
    private static final String DATABASE_NAME = "eng.db";
    private static final int DATABASE_VERSION = 1;
/*    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;*/

public EngDbHelper (Context context){
    super(context, DATABASE_NAME,null,DATABASE_VERSION);
    fContext = context;
}
  /*  public Cursor getAllItems() {
        mDb = mDbHelper.getReadableDatabase();
        return mDb.query(TABLE_NAME, null, null, null, null, null, null);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*String SQL_CREATE_WORDS_TABLE = "CREATE TEABLE " + EngContract.NewWord.TABLE_NAME + " ("
                + EngContract.NewWord._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NewWord.COLUMN_WORD + " TEXT NOT NULL, "
                + NewWord.COLUMN_TRANSLATION + " TEXT NOT NULL);";*/
        String SQL_CREATE_WORDS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + NewWord._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NewWord.COLUMN_WORD + " TEXT NOT NULL, "
                + NewWord.COLUMN_TRANSLATION + " TEXT NOT NULL, "
                + NewWord.COLUMN_GUESS + " TEXT NOT NULL, "
                + NewWord.COLUMN_COUNT + " TEXT NOT NULL, "
                + NewWord.COLUMN_UNIT + " TEXT NOT NULL );";

        db.execSQL(SQL_CREATE_WORDS_TABLE);
// Добавляем записи в таблицу
        ContentValues values = new ContentValues();

        // Получим файл из ресурсов
        Resources res = fContext.getResources();

        // Открываем xml-файл
        XmlResourceParser _xml = res.getXml(R.xml.dictionary_insert);
        try {
            // Ищем конец документа
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Ищем теги record
                if ((eventType == XmlPullParser.START_TAG)
                        && (_xml.getName().equals("record"))) {
                    // Тег Record найден, теперь получим его атрибуты и
                    // вставляем в таблицу
                    String word = _xml.getAttributeValue(0);
                    String translation = _xml.getAttributeValue(1);
                    String guess = _xml.getAttributeValue(2);
                    String count = _xml.getAttributeValue(3);
                    String unit = _xml.getAttributeValue(4);
                    values.put("word", word);
                    values.put("translation", translation);
                    values.put("guess", guess);
                    values.put("count", count);
                    values.put("unit", unit);
                    db.insert(TABLE_NAME, null, values);
                }
                eventType = _xml.next();
            }
        }
        // Catch errors
        catch (XmlPullParserException e) {
            Log.e("Test", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("Test", e.getMessage(), e);

        } finally {
            // Close the xml file
            _xml.close();
        }
    // добавление начальных данных
   /* db.execSQL("INSERT INTO "+ TABLE_NAME +" (" + COLUMN_WORD
    + ", " + COLUMN_TRANSLATION  + ") VALUES ('given name','first name (American English)');");*/
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
