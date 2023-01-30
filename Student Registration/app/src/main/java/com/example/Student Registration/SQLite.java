package com.example.blgm_ogrencilerisql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    private Context context;
    private static final String Database_ismi = "OgrenciBilgileri.db";
    private static final int version=1;
    private static final String TABLO_ISMI = "CMPE";
    private static final String TABLO_ISMI1 = "BLGM";
    private static final String SATIR_ID = "_id";
    private static final String ISIM = "isim";
    private static final String SOYISIM = "soyisim";
    private static final String TEL = "tel";



    public SQLite(@Nullable Context context) {
        super(context, Database_ismi, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String yaratNotlar = "CREATE TABLE " + TABLO_ISMI + " (" + SATIR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ISIM + " TEXT , " + SOYISIM + " TEXT , " + TEL + " TEXT);";
        String yaratNotlar1 = "CREATE TABLE " + TABLO_ISMI1 + " (" + SATIR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ISIM + " TEXT , " + SOYISIM + " TEXT , " + TEL + " TEXT);";
        sqLiteDatabase.execSQL(yaratNotlar);
        sqLiteDatabase.execSQL(yaratNotlar1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLO_ISMI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLO_ISMI1);
        onCreate(sqLiteDatabase);
    }

    void cmpebilgiekle(String isim, String soyisim, String tel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ISIM, isim);
        cv.put(SOYISIM, soyisim);
        cv.put(TEL, tel);
        db.insert(TABLO_ISMI,null,cv);
    }

    void blgmbilgiekle(String isim, String soyisim, String tel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ISIM, isim);
        cv.put(SOYISIM, soyisim);
        cv.put(TEL, tel);
        db.insert(TABLO_ISMI1,null,cv);
    }

    Cursor cmpebilgigetir(){
        String query= "SELECT * FROM " + TABLO_ISMI;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=null;
        if (db != null){
            c=db.rawQuery(query,null);
        }

        return c;
    }

    Cursor blgmbilgigetir(){
        String query= "SELECT * FROM " + TABLO_ISMI1;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=null;
        if (db != null){
            c=db.rawQuery(query,null);
        }

        return c;
    }

    void kisisilcmpe(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLO_ISMI, "_id=?",new String[]{id});


    }

    void kisisilblgm(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLO_ISMI1, "_id=?",new String[]{id});


    }

    /*
    void duzenle(int idb,String yeninot){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(NOTLAR ,yeninot);

        db.update(TABLO_ISMI,cv,"_id=?",new String[]{idb+""});
    }*/

}
