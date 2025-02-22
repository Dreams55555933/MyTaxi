package ru.fomihykh.mytaxi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "TAXI";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CASH_BEFORE = "cashBefore";
    public static final String COLUMN_NON_CASH_BEFORE = "nonCashBefore";
    public static final String COLUMN_MILEAGE_BEFORE = "mileageBefore";
    public static final String COLUMN_CASH_AFTER = "cashAfter";
    public static final String COLUMN_NON_CASH_AFTER = "nonCashAfter";
    public static final String COLUMN_MILEAGE_AFTER = "mileageAfter";
    public static final String COLUMN_PROFIT = "profit";
    public static final String COLUMN_NP = "np";
    public static final String COLUMN_GSM = "gsm";
    public static final String COLUMN_COMMENT = "comment";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_CASH_BEFORE + " INTEGER, " +
                    COLUMN_NON_CASH_BEFORE + " INTEGER, " +
                    COLUMN_MILEAGE_BEFORE + " INTEGER, " +
                    COLUMN_CASH_AFTER + " INTEGER, " +
                    COLUMN_NON_CASH_AFTER + "INTEGER, " +
                    COLUMN_MILEAGE_AFTER + " INTEGER, " +
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
}
