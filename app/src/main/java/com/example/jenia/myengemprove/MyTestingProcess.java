package com.example.jenia.myengemprove;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jenia.myengemprove.data.EngContract;
import com.example.jenia.myengemprove.data.EngDbHelper;

import java.util.Random;

public class MyTestingProcess extends AppCompatActivity implements View.OnClickListener  {
    private EngDbHelper mDbHelper;
    TextView answer;
    TextView question;
    TextView startTest;
    Button checkResult;
    Button showAnswer;
    Button nextQuestion;
    EditText myAnswer;
    EngDbHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    Spinner spinnerUnit;
    int unit;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_testing_process);
        answer = (TextView) findViewById(R.id.answer);
        question = (TextView) findViewById(R.id.text_view_info);
        myAnswer = (EditText) findViewById(R.id.myAnswer);
        myAnswer.setOnClickListener(this);
        checkResult = (Button) findViewById(R.id.chekResult);
        checkResult.setOnClickListener(this);
        spinnerUnit = (Spinner) findViewById(R.id.spinnerUnit);
        startTest = (TextView) findViewById(R.id.startTest);
        startTest.setOnClickListener(this);
        /*showAnswer = (Button) findViewById(R.id.showAnswer);
        showAnswer.setOnClickListener(this);*/
        nextQuestion = (Button) findViewById(R.id.nextQuestion);
        nextQuestion.setOnClickListener(this);





        }
    @Override
    public void onResume() {
        super.onResume();
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.units);

                String selected = parent.getItemAtPosition(selectedItemPosition).toString();

                unit = selectedItemPosition;
               /* Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш выбор: " +  unit, Toast.LENGTH_SHORT);
                toast.show();*/
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // add 4/04 when try to make a chose for units///
        /*spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.units);

                String selected = parent.getItemAtPosition(selectedItemPosition).toString();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш выбор: " + selected, Toast.LENGTH_SHORT);
                toast.show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                *//*if (selected == "Unit1"){
                    unit = 0;
                }if (selected == "Unit3"){
                    unit = 2;
                }unit = 4;*//*
            }
        });*/


///////////////////////////////////////////////////////////////////
        /*sqlHelper = new EngDbHelper(getApplicationContext());
        // открываем подключение
        db = sqlHelper.getReadableDatabase();
        String query = "SELECT " + EngContract.NewWord._ID + ", "
                + EngContract.NewWord.COLUMN_TRANSLATION + ", " + EngContract.NewWord.COLUMN_WORD + ", " + EngContract.NewWord.COLUMN_GUESS +
                ", " + EngContract.NewWord.COLUMN_UNIT +
                " FROM " + EngContract.NewWord.TABLE_NAME + " WHERE " + EngContract.NewWord.COLUMN_UNIT + " = " + "'2'";
        userCursor = db.rawQuery(query, null);

        //more to the first row
        *//*userCursor.moveToPosition(0);*//*
        userCursor.moveToNext();

        int item_id = userCursor.getInt(userCursor.getColumnIndex(EngContract.NewWord._ID));
        String item_content = userCursor.getString(userCursor
                .getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION));
        String item_mainWord = userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.COLUMN_WORD));
        String item_guess = userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.COLUMN_GUESS));
        String item_unit = userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.COLUMN_UNIT));
        question.setText(item_id + item_content + item_guess + item_unit + " ?");*/

        ///////////////////////////////////////////////////////////////////

       /* sqlHelper = new EngDbHelper(getApplicationContext());
        // открываем подключение
        db = sqlHelper.getReadableDatabase();*/
       /* //получаем данные из бд
        userCursor = db.query(EngContract.NewWord.TABLE_NAME,new String[]{EngContract.NewWord.COLUMN_WORD, EngContract.NewWord.COLUMN_TRANSLATION},
                null,null,null,null,null);
        question.setText(userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.TABLE_NAME)));*/
////////////////////////////////////////////////////////////////////////////////
       /* String query = "SELECT " + EngContract.NewWord._ID + ", "
                + EngContract.NewWord.COLUMN_TRANSLATION + " FROM " + EngContract.NewWord.TABLE_NAME;
        userCursor = db.rawQuery(query, null);
        userCursor.moveToFirst(); // переходим на первую строку
        // извлекаем данные из курсора
        int item_id = userCursor
                .getInt(userCursor.getColumnIndex(EngContract.NewWord._ID));
        String item_content = userCursor.getString(userCursor
                .getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION));
        userCursor.close();

        question.setText("id: " + item_id + " Имя кота: " + item_content);*/

///////////////////////////////////////////////////////////////////////////////////////////////

            /*    rawQuery("select * from " + EngContract.NewWord.TABLE_NAME, null);
        String[] headers = new String[]{EngContract.NewWord.COLUMN_WORD, EngContract.NewWord.COLUMN_TRANSLATION};
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        header.setText("Слов в словаре: " + String.valueOf(userCursor.getCount()));
        mList.setAdapter(userAdapter);
        registerForContextMenu(mList);*/

    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.startTest:
try {

    sqlHelper = new EngDbHelper(getApplicationContext());
    // открываем подключение
    db = sqlHelper.getReadableDatabase();
    String query = "SELECT " + EngContract.NewWord._ID + ", "
            + EngContract.NewWord.COLUMN_TRANSLATION + ", " + EngContract.NewWord.COLUMN_WORD + ", " + EngContract.NewWord.COLUMN_GUESS +
            ", " + EngContract.NewWord.COLUMN_UNIT +
            " FROM " + EngContract.NewWord.TABLE_NAME + " WHERE " + EngContract.NewWord.COLUMN_UNIT + " = " + "'" + unit + "'";
    userCursor = db.rawQuery(query, null);

    //more to the first row
        /*userCursor.moveToPosition(0);*/
    userCursor.moveToNext();

    int item_id = userCursor.getInt(userCursor.getColumnIndex(EngContract.NewWord._ID));
    String item_content = userCursor.getString(userCursor
            .getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION));
    String item_mainWord = userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.COLUMN_WORD));
    String item_guess = userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.COLUMN_GUESS));
    String item_unit = userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.COLUMN_UNIT));
    question.setText(item_id + item_content + item_guess + item_unit + " ?");
}
catch (android.database.CursorIndexOutOfBoundsException u){
    Toast.makeText(getApplicationContext(), "Нет такого Unit!", Toast.LENGTH_SHORT).show();
}


            case R.id.nextQuestion:
                Random rn = new Random();
                try {
                    userCursor.moveToPosition(rn.nextInt(userCursor.getCount()) + 1);

                    int item_ide = userCursor.getInt(userCursor.getColumnIndex(EngContract.NewWord._ID));
                    String item_contente = userCursor.getString(userCursor
                            .getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION));


                    question.setText(item_ide + " " + item_contente + " ??");
                    break;
                } catch (android.database.CursorIndexOutOfBoundsException e) {
                    Toast.makeText(getApplicationContext(), "Ошибочка", Toast.LENGTH_SHORT).show();

                } finally {
                    break;
                }

            case R.id.chekResult:
                try {
                    String item_mainWorde = userCursor.getString(userCursor.getColumnIndex(EngContract.NewWord.COLUMN_WORD));

                    String check = myAnswer.getText().toString();
                    if (check.equals(item_mainWorde)) {
                        Toast.makeText(getApplicationContext(), "Хех угадал", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "иди учи лузер! ", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                    question.setText(item_mainWorde);
                    break;
                } catch (java.lang.NullPointerException n) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Оу оу оу, с начало начни тест! ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }catch ( android.database.CursorIndexOutOfBoundsException h){
                    Toast toast = Toast.makeText(getApplicationContext(), "Ошибка 2! ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }

        }


       /* sqlHelper = new EngDbHelper(getApplicationContext());
        // открываем подключение
        db = sqlHelper.getReadableDatabase();
        String query = "SELECT " + EngContract.NewWord._ID + ", "
                + EngContract.NewWord.COLUMN_TRANSLATION + " FROM " + EngContract.NewWord.TABLE_NAME;
        userCursor = db.rawQuery(query, null);

        userCursor.moveToFirst(); /// переходим на первую строку
        // извлекаем данные из курсора

        int item_id = userCursor.getInt(userCursor.getColumnIndex(EngContract.NewWord._ID));
        String item_content = userCursor.getString(userCursor
                .getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION));
       userCursor.close();

        question.setText("id: " + item_id + " Имя кота: " + item_content);*/



           /*int item_id = userCursor
                    .getInt(userCursor.getColumnIndex(EngContract.NewWord._ID));
            String item_content = userCursor.getString(userCursor
                    .getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION));*/
            /*userCursor.close();*/

            /*question.setText("id: " + item_id + " Перевод: " + item_content);*/

        /*userCursor.moveToNext();*/

       /* transWord = userCursor.getString(userCursor
                .getColumnIndex(EngContract.NewWord.COLUMN_TRANSLATION));
        question.append(transWord + " ");


        userCursor.close();*/

        /*
        //catCursor.moveToFirst();
        String catName;
        while (userCursor.moveToNext()) {

            catName = userCursor.getString(userCursor
                    .getColumnIndex(EngContract.NewWord.TABLE_NAME));
            question.append(catName + " ");
        }

        userCursor.close();*/


    }
}



















