package de.davidartmann.charowin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Database Helper which extends {@link de.davidartmann.charowin.db.model.DaoMaster.OpenHelper}.
 *
 * Created by David on 14.10.2015.
 */
public class ChaRoWinOpenHelper extends DaoMaster.OpenHelper {

    private static final String TAG = ChaRoWinOpenHelper.class.getSimpleName();

    private static final String DB_NAME = "charowin.db";

    /**
     * Constructor to only pass the {@link Context}.
     * Name of the DB is #DB_NAME.
     * The {@link android.database.sqlite.SQLiteDatabase.CursorFactory} is null.
     * @param context passed context.
     */
    public ChaRoWinOpenHelper(Context context) {
        super(context, DB_NAME, null);
    }

    /**
     * Constructor to pass the {@link Context} and a name of the database.
     * The {@link android.database.sqlite.SQLiteDatabase.CursorFactory} is null.
     * @param context passed context.
     * @param name name of the database.
     */
    public ChaRoWinOpenHelper(Context context, String name) {
        super(context, name, null);
    }

    /**
     * Constructor to only pass the {@link Context} and
     * the {@link android.database.sqlite.SQLiteDatabase.CursorFactory}.
     * Name of the DB is #DB_NAME.
     * @param context passed context.
     * @param factory passed cursorfactory.
     */
    public ChaRoWinOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory);
    }

    /**
     * Constructor to only pass the {@link Context}, the database name and
     * the {@link android.database.sqlite.SQLiteDatabase.CursorFactory}.
     * @param context passed context.
     * @param name name of the database.
     * @param factory passed cursorfactory.
     */
    public ChaRoWinOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO
        super.onCreate(db);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            Log.i(TAG, "Upgrading schema version from "+oldVersion+" to "+newVersion);
            //TODO: call update methods of daos
            onCreate(db);
        }
    }
}
