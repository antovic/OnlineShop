package vuk.antovic.onlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    String TABLE_USERS = "users";
    String TABLE_USERS_USERNAME = "username";
    String TABLE_USERS_PASSWORD = "password";
    String TABLE_USERS_EMAIL = "email";
    String TABLE_USERS_ID = "id";

    String TABLE_ITEMS = "items";
    String TABLE_ITEMS_NAME = "name";
    String TABLE_ITEMS_IMAGE = "image";
    String TABLE_ITEMS_CATEGORY = "category";
    String TABLE_ITEMS_PRICE = "price";
    String TABLE_ITEMS_ID = "id";

    String TABLE_PURCHASES = "purchases";
    String TABLE_PURCHASES_STATUS = "status";
    String TABLE_PURCHASES_PRICE = "price";
    String TABLE_PURCHASES_DATE = "date";
    String TABLE_PURCHASES_ID = "id";

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS +
                " (" + TABLE_USERS_USERNAME + " text, " +
                TABLE_USERS_PASSWORD + " text, " +
                TABLE_USERS_EMAIL + " text, " +
                TABLE_USERS_ID + " integer primary key autoincrement);");
        db.execSQL("create table " + TABLE_ITEMS + " ( " +
                TABLE_ITEMS_NAME + " text, " +
                TABLE_ITEMS_PRICE + " text, "+
                TABLE_ITEMS_CATEGORY + " text, " +
                TABLE_ITEMS_IMAGE + " blob, " +
                TABLE_ITEMS_ID + " integer primary key autoincrement);");
        db.execSQL("create table " + TABLE_PURCHASES + " ( " +
                TABLE_PURCHASES_STATUS + " text, " +
                TABLE_PURCHASES_PRICE + " text, "+
                TABLE_PURCHASES_DATE + " text, " +
                TABLE_PURCHASES_ID + " integer primary key autoincrement);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertUser(User user)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_USERS_USERNAME, user.getUsername());
        contentValues.put(TABLE_USERS_EMAIL, user.getEmail());
        contentValues.put(TABLE_USERS_PASSWORD, user.getPassword());

        db.insert(TABLE_USERS, null, contentValues);
        close();
    }

    public boolean validLogin(String username, String password)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS, // Table
                null, // Columns to return
                TABLE_USERS_USERNAME + " =? AND " + TABLE_USERS_PASSWORD + " =? ", // Clauses
                new String[]{username, password}, // The data to inspect
                null, // Grouping
                null, // Filters
                null // Sort
        );
        boolean found = cursor.moveToFirst();

        cursor.close();
        db.close();
        return found;
    }

    public boolean checkUser(String username)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USERS,
                null,
                TABLE_USERS_USERNAME + " =? ",
                new String[]{username},
                null,
                null,
                null
        );
        boolean found = cursor.moveToFirst();

        cursor.close();
        db.close();
        return found;
    }


}
