package com.example.calccarbohydrates.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.calccarbohydrates.LocaleUtils;
import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.dao.ProductsDao;
import com.example.calccarbohydrates.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductsDao productsDao();

    private static final String DB_NAME = "products_database";
    private static AppDatabase INSTANCE;
    private static Context activity;


    public static synchronized AppDatabase getAppDatabase(final Context context) {
        activity = context.getApplicationContext();
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    DB_NAME)
                    .fallbackToDestructiveMigrationFrom()
                    .addCallback(roomCallBack)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsincTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsincTask extends AsyncTask<Void, Void, Void> {
        private ProductsDao productsDao;

        private PopulateDbAsincTask(AppDatabase db) {
            productsDao = db.productsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fillWithStartingData(activity);
            return null;
        }
    }

    private static void fillWithStartingData(Context context){
        ProductsDao productsDao = getAppDatabase(context).productsDao();
        JSONArray products = loadJSONArray(context);

        try {
            for (int i = 0; i<products.length(); i++){
                JSONObject product = products.getJSONObject(i);
                String productName = product.getString("name");
                String productCarbohydrates = product.getString("carbohydrates");
                productsDao.insertProduct(new Product(productName,productCarbohydrates));
            }

        }catch (JSONException e){

        }
    }

    private static JSONArray loadJSONArray(Context context) {
        StringBuilder builder = new StringBuilder();
        if(LocaleUtils.getLocale().equals(LocaleUtils.ENGLISH)){
            InputStream inputStream = context.getResources().openRawResource(R.raw.products_list);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                JSONObject jsonObject = new JSONObject(builder.toString());
                return jsonObject.getJSONArray("products");
            } catch (IOException | JSONException exception) {
                exception.printStackTrace();
            }
        }
        if(LocaleUtils.getLocale().equals(LocaleUtils.BULGARIAN)){
            InputStream inputStream = context.getResources().openRawResource(R.raw.products_list_bg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                JSONObject jsonObject = new JSONObject(builder.toString());
                return jsonObject.getJSONArray("products");
            } catch (IOException | JSONException exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }
}
