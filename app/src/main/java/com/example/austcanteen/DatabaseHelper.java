package com.example.austcanteen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/31/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="foodlist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_PATH = "data/data/com.example.austcanteen/databases/";
    private final Context context;
    private static SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        createDB();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createDB(){
        boolean dbExist = checkDbExist();

        if(!dbExist){
            this.getWritableDatabase();
            copyDatabase();
        }
    }

    public boolean checkDbExist(){
        SQLiteDatabase db = null;
        try{
            String path = DATABASE_PATH + DATABASE_NAME;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e){

        }

        if(db != null){
            db.close();
            return true;
        }
        return false;
    }

    public void copyDatabase(){
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);

            byte b[] = new byte[1024];
            int length;

            while((length = inputStream.read(b)) > 0){
                outputStream.write(b, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ListItems> getList(String TABLE_NAME){
        ListItems obj = null;
        List<ListItems> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(cursor.moveToNext()){
            byte[] img = cursor.getBlob(5);
            Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
            obj = new ListItems(cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getString(4), bmp);
            list.add(obj);
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
}
