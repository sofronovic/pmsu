package sf22_2014.android_projekat_sf22_2014.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sf22_2014.android_projekat_sf22_2014.Model.Meal;
import sf22_2014.android_projekat_sf22_2014.Model.Restaurant;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RESTAURANT = "restaurant";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_IMAGE = "image_path";
    public static final String COLUMN_START_HOUR = "start_hour";
    public static final String COLUMN_END_HOUR = "end_hour";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_URL = "url";

    public static final String TABLE_MEAL = "meal";
    public static final String COLUMN_MEAL_ID = "_id";
    public static final String COLUMN_MEAL_TITLE = "title";
    public static final String COLUMN_MEAL_DESCRIPTION = "description";
    public static final String COLUMN_MEAL_PRICE = "price";
    public static final String COLUMN_MEAL_IMAGE = "image_path";
    public static final String COLUMN_RES_ID = "restaurant_id";

    private static final String DATABASE_NAME = "food_order.db";
    private static final int DATABASE_VERSION = 9;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_RESTAURANT + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_TITLE + " text not null, " +
            COLUMN_DESCRIPTION + " text not null, " +
            COLUMN_ADDRESS + " text not null, " +
            COLUMN_IMAGE + " text not null, " +
            COLUMN_START_HOUR + " integer not null, " +
            COLUMN_END_HOUR + " integer not null, " +
            COLUMN_PHONE + " text not null, " +
            COLUMN_EMAIL + " text not null, " +
            COLUMN_URL + " text not null" +
            ");";


    private static final String DATABASE_MEAL_CREATE = "create table "
            + TABLE_MEAL + "(" +
            COLUMN_MEAL_ID + " integer primary key autoincrement, " +
            COLUMN_MEAL_TITLE + " text not null, " +
            COLUMN_MEAL_DESCRIPTION + " text not null, " +
            COLUMN_MEAL_PRICE + " double not null," +
            COLUMN_MEAL_IMAGE + " text not null," +
            COLUMN_RES_ID + " integer not null," +
            "FOREIGN KEY(" + COLUMN_RES_ID + ") REFERENCES " + TABLE_RESTAURANT + "("+COLUMN_ID+") );";

    private static MySQLiteHelper dbHelper;

    public static synchronized MySQLiteHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new MySQLiteHelper(context.getApplicationContext());
        }
        return dbHelper;
    }

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    protected MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(DATABASE_CREATE);
            sqLiteDatabase.execSQL(DATABASE_MEAL_CREATE);
            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANT + " (" + COLUMN_ID + "," + COLUMN_TITLE + "," +
                    COLUMN_DESCRIPTION + "," + COLUMN_ADDRESS + "," + COLUMN_IMAGE + "," + COLUMN_START_HOUR
                    + "," + COLUMN_END_HOUR + "," + COLUMN_PHONE + "," + COLUMN_EMAIL + "," + COLUMN_URL + ") " +
                    "VALUES (2, 'AAAA', 'AAAA', 'Bulevar Kralja Petra 10 Novi Sad', 'asdasdasd', 3, 3, 'a', 'a', 'a');");

            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MEAL + " (" + COLUMN_MEAL_ID + "," + COLUMN_MEAL_TITLE + "," + COLUMN_MEAL_DESCRIPTION + "," +
                    COLUMN_MEAL_PRICE + "," + COLUMN_MEAL_IMAGE + "," + COLUMN_RES_ID + ") " + "VALUES (2, 'AAAA', 'AAAA', 350, 'asd', 2);");
        } catch (Exception e) {
            Log.e("MySQLiteHelper", "Exception occurred: " + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion
                        + ", which will destroy all old data.");
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Log.e("MySQLiteHelper", "Exception: " + e);
        }
    }

    public List<Restaurant> getAllUser() {
        List<Restaurant> restaurants = new ArrayList<>();
        String RESTAURANT_SELECT_QUERY = "SELECT * FROM " + TABLE_RESTAURANT;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(RESTAURANT_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Restaurant r = new Restaurant();
                    long id = r.getId();
                    String name = r.getName();
                    String description = r.getDescription();
                    String address = r.getAddress();
                    int startHour = r.getStartHour();
                    int endHour = r.getEndHour();
                    String phone = r.getPhone();
                    String email = r.getEmail();
                    String url = r.getUrl();

                    id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                    startHour = cursor.getInt(cursor.getColumnIndex(COLUMN_START_HOUR));
                    endHour = cursor.getInt(cursor.getColumnIndex(COLUMN_END_HOUR));
                    phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                    email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                    url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));

                    r.setId(id);
                    r.setName(name);
                    r.setDescription(description);
                    r.setAddress(address);
                    r.setStartHour(startHour);
                    r.setEndHour(endHour);
                    r.setPhone(phone);
                    r.setEmail(email);
                    r.setUrl(url);

                    restaurants.add(r);

                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d("Select", "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return restaurants;
    }

    public List<Meal> getAllMeal() {
        List<Meal> mealList = new ArrayList<>();
        String RESTAURANT_SELECT_QUERY = "SELECT * FROM " + TABLE_MEAL;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(RESTAURANT_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Meal m = new Meal();
                    long id = m.getId();
                    String name = m.getName();
                    String description = m.getDescription();
                    double price = m.getPrice();

                    id = cursor.getLong(cursor.getColumnIndex(COLUMN_MEAL_ID));
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_MEAL_TITLE));
                    description = cursor.getString(cursor.getColumnIndex(COLUMN_MEAL_DESCRIPTION));
                    price = cursor.getDouble(cursor.getColumnIndex(COLUMN_MEAL_PRICE));

                    m.setId(id);
                    m.setName(name);
                    m.setDescription(description);
                    m.setPrice(price);

                    mealList.add(m);

                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d("Select", "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return mealList;
    }
    //Debagovanje baze

    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"mesage"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }


    }
}
