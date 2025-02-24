package ru.fomihykh.mytaxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "TAXI";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PROFIT = "profit";
    public static final String COLUMN_NP = "np";
    public static final String COLUMN_GSM = "gsm";
    public static final String COLUMN_COMMENT = "comment";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_PROFIT + " INTEGER, " +
                    COLUMN_NP + " INTEGER, " +
                    COLUMN_GSM + " INTEGER, " +
                    COLUMN_COMMENT + " TEXT" +
                    ");";

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void addData(String date,int profit,int np,int gsm,String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE,date);
        values.put(COLUMN_PROFIT,profit);
        values.put(COLUMN_NP,np);
        values.put(COLUMN_GSM,gsm);
        values.put(COLUMN_COMMENT,comment);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public ArrayList<String> readData(){
        ArrayList<String>taxis = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToNext()){
            do {
                taxis.add("Дата: "+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))+"; "
                        +"Непредвиденные расходы: "+cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NP))+"; "
                        + "Расходы на бензин: "+cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GSM))+"; "
                        +"Прибыль: "+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFIT))+"; "
                +"Комментарий: "+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMENT))+".");
            }while (cursor.moveToNext());
        }
        return taxis;
    }
    public ArrayList<Taxi> getTaxis(){
        ArrayList<Taxi>taxis = new ArrayList<Taxi>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToNext()){
            do {
                taxis.add(new Taxi(
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PROFIT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NP)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GSM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMENT))
                ));
            }while (cursor.moveToNext());
        }
        return taxis;
    }
}
