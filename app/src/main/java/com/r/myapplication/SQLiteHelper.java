package com.r.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SQLiteHelper  extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	1;
    private	static final String	DATABASE_NAME = "task_db";
    private	static final String TABLE_TASKS = "tasks_table";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK = "task_desc";
    private static final String COLUMN_DATE = "date";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_CONTACTS_TABLE = "CREATE	 TABLE " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TASK + " TEXT," +
                COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public void addData(String s1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK,s1);
        values.put(COLUMN_DATE ,getDateTime());
        db.insert(TABLE_TASKS,null,values);
        db.close();
    }

    public ArrayList<task> listTasks(){
        String sql = "select * from " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<task> storeTasks = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String task_desc = cursor.getString(1);
                String date = cursor.getString(2);
                storeTasks.add(new task(id, task_desc, date));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeTasks;
    }

    public void removeSingleContact(String title) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        database.execSQL("DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASK + "= '" + title + "'");

        //Close the database
        database.close();
    }

    public Cursor fetchData()
    {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            //String str = ("SELECT * FROM tasks_table");
            //return db.rawQuery(str, null);

            String[] columns = {COLUMN_ID,COLUMN_TASK, COLUMN_DATE};
            return db.query(TABLE_TASKS, columns, null, null, null, null, null);
        } catch (Exception ex){
            return null;
        }

    }


}
