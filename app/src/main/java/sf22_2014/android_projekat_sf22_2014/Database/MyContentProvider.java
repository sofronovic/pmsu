package sf22_2014.android_projekat_sf22_2014.Database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class MyContentProvider extends ContentProvider {

    private MySQLiteHelper database;

    private static final int RESTAURANT = 10;

    private static final int RESTAURANT_ID = 20;

    private static final String AUTHORITY = "sf22_2014.android_projekat_sf22_2014";

    private static final String BASE_PATH = "restaurants";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, BASE_PATH, RESTAURANT);
        sUriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", RESTAURANT_ID);
    }

    @Override
    public boolean onCreate() {
        database = new MySQLiteHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MySQLiteHelper.TABLE_RESTAURANT);

        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
            case RESTAURANT:
                break;
            case RESTAURANT_ID:
                // Adding the id to the original query
                queryBuilder.appendWhere(MySQLiteHelper.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case RESTAURANT:
                id = sqLiteDatabase.insert(MySQLiteHelper.TABLE_RESTAURANT, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

}
