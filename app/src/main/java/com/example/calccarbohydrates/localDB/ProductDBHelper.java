//package com.example.calccarbohydrates.localDB;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class ProductDBHelper extends SQLiteOpenHelper {
//
//    public ProductDBHelper(Context context) {
//
////        super(context, ProductContract.DB_NAME, null, ProductContract.DB_VERSION);
//
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqlDB) {
//
//        String sqlQuery = "CREATE TABLE " + ProductContract.TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                ProductContract.Columns.QUANTITY + " TEXT, " + ProductContract.Columns.MEASURE + " TEXT, " +
//                ProductContract.Columns.PRODUCT + ")";
//
//        sqlDB.execSQL(sqlQuery);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
//        sqlDB.execSQL("DROP TABLE IF EXISTS " + ProductContract.TABLE);
//        onCreate(sqlDB);
//    }
//}