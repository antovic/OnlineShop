package vuk.antovic.onlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


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
    String TABLE_PURCHASES_USERNAME = "username";
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
                TABLE_PURCHASES_USERNAME + " text, " +
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

    public void updatePassword(String username, String password)
    {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_USERS_PASSWORD, password);

        db.update(
                TABLE_USERS,
                contentValues,
                TABLE_USERS_USERNAME + " =? ",
                new String[]{username}
        );

        db.close();
    }

    public String getEmail(String username)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS,
                new String[]{TABLE_USERS_EMAIL},
                TABLE_USERS_USERNAME + " =? ",
                new String[]{username},
                null,
                null,
                null
        );

        cursor.moveToFirst();
        String email = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_USERS_EMAIL));

        cursor.close();
        db.close();
        return email;
    }


    // Convert Drawable to Byte array
    public byte[] drawableToByteArray(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        else {

            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();

    }
    private Drawable byteToDrawable(Context context, byte[] imageBytes) {
        if (imageBytes == null) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return new BitmapDrawable(context.getResources(), bitmap);
    }



    public void insertItem(ItemModel item)
    {
        byte[] image = drawableToByteArray(item.getImage());
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ITEMS_NAME, item.getName());
        contentValues.put(TABLE_ITEMS_PRICE, item.getPrice());
        contentValues.put(TABLE_ITEMS_CATEGORY, item.getCategory());
        contentValues.put(TABLE_ITEMS_IMAGE, image);

        db.insert(TABLE_ITEMS, null, contentValues);
        db.close();
    }


    public String[] getCategories()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                true,
                TABLE_ITEMS,
                new String[]{TABLE_ITEMS_CATEGORY},
                null,
                null,
                null,
                null,
                null,
                null
        );
        if(!cursor.moveToFirst())
            return null;

        String[] categories = new String[cursor.getCount()];
        int pos = 0;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            categories[pos] = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_ITEMS_CATEGORY));
            pos++;
        }

        cursor.close();
        db.close();

        return categories;
    }



    public ItemModel[] getItemsByCategory(Context context, String category)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_ITEMS,
                null,
                TABLE_ITEMS_CATEGORY + " =? ",
                new String[]{category},
                null,
                null,
                null);

        if(!cursor.moveToFirst())
            return null;

        int pos = 0;
        ItemModel[] items = new ItemModel[cursor.getCount()];
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_ITEMS_NAME));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE_ITEMS_PRICE));
            Drawable image = byteToDrawable(context, cursor.getBlob(cursor.getColumnIndexOrThrow(TABLE_ITEMS_IMAGE)));
            items[pos] = new ItemModel(image, name, price, category);
            pos++;
        }

        cursor.close();
        db.close();
        return items;
    }


    public void insertPurchase(ItemModel item, String username)
    {
        SQLiteDatabase db = getWritableDatabase();

        String date = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));

        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_PURCHASES_DATE, date);
        contentValues.put(TABLE_PURCHASES_USERNAME, username);
        contentValues.put(TABLE_PURCHASES_PRICE, item.getPrice());
        contentValues.put(TABLE_PURCHASES_STATUS, "WAITING FOR DELIVERY");

        db.insert(TABLE_PURCHASES, null, contentValues);
        db.close();
    }


    public PurchaseModel[] getPurchases(String username)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_PURCHASES,
                null,
                TABLE_PURCHASES_USERNAME + " =? ",
                new String[]{username},
                null,
                null,
                null
        );

        if(!cursor.moveToFirst())
            return null;

        PurchaseModel[] purchases = new PurchaseModel[cursor.getCount()];
        int pos = 0;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            String date = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_PURCHASES_DATE));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_PURCHASES_PRICE));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_PURCHASES_STATUS));

            PurchaseModel.State state;
            switch(status){
                case "DELIVERED":
                    state = PurchaseModel.State.DELIVERED;
                    break;
                case "CANCELLED":
                    state = PurchaseModel.State.CANCELLED;
                    break;
                default:
                    state = PurchaseModel.State.WAITING_FOR_DELIVERY;
                    break;
            }
            purchases[pos] =  new PurchaseModel(state, Integer.valueOf(price), date);
            pos++;
        }
        return purchases;
    }



}











