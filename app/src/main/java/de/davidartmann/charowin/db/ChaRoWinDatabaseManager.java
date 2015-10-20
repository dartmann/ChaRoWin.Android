package de.davidartmann.charowin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.davidartmann.charowin.db.contract.IDataBaseManager;
import de.davidartmann.charowin.db.model.DaoMaster;
import de.davidartmann.charowin.db.model.DaoSession;
import de.davidartmann.charowin.db.model.DietPlan;
import de.davidartmann.charowin.db.model.Exercise;
import de.davidartmann.charowin.db.model.Exercise_Workout;
import de.davidartmann.charowin.db.model.Food;
import de.davidartmann.charowin.db.model.Food_Meal;
import de.davidartmann.charowin.db.model.Meal;
import de.davidartmann.charowin.db.model.Meal_Dietplan;
import de.davidartmann.charowin.db.model.Muscle;
import de.davidartmann.charowin.db.model.Muscle_Exercise;
import de.davidartmann.charowin.db.model.User;
import de.davidartmann.charowin.db.model.Workout;
import de.davidartmann.charowin.db.model.WorkoutPlan;
import de.davidartmann.charowin.db.model.WorkoutSession;
import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;

/**
 * General DbManager class.
 *
 * Created by David on 14.10.2015.
 */
public class ChaRoWinDatabaseManager implements AsyncOperationListener, IDataBaseManager {

    private static final String TAG = ChaRoWinDatabaseManager.class.getSimpleName();

    private static ChaRoWinDatabaseManager sInstance;
    private Context context;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private AsyncSession asyncSession;
    private List<AsyncOperation> completedOperations;

    public ChaRoWinDatabaseManager(Context context) {
        this.context = context;
        //TODO: this should later be not DevOpenHelper any more -> ChaRoWinOpenHelper
        devOpenHelper = new DaoMaster.DevOpenHelper(this.context, "charowin.db", null);
        completedOperations = new CopyOnWriteArrayList<>();
    }

    public static ChaRoWinDatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ChaRoWinDatabaseManager(context);
        }
        return sInstance;
    }

    public void openReadableDb() {
        sqLiteDatabase = devOpenHelper.getReadableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    public void openWritableDb() {
        try {
            sqLiteDatabase = devOpenHelper.getWritableDatabase();
        } catch (SQLiteException se) {
            Log.w(TAG, "SQLiteException in openWritable()");
            se.printStackTrace();
        }
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    @Override
    public void closeDbConnections() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
        if (devOpenHelper != null) {
            devOpenHelper.close();
            devOpenHelper = null;
        }
        if (sInstance != null) {
            sInstance = null;
        }
    }

    @Override
    public void dropDatabase() {
        openWritableDb();
        DaoMaster.dropAllTables(sqLiteDatabase, true);
        devOpenHelper.onCreate(sqLiteDatabase);
        asyncSession.deleteAll(DietPlan.class);
        asyncSession.deleteAll(Exercise.class);
        asyncSession.deleteAll(Exercise_Workout.class);
        asyncSession.deleteAll(Food.class);
        asyncSession.deleteAll(Food_Meal.class);
        asyncSession.deleteAll(Meal.class);
        asyncSession.deleteAll(Meal_Dietplan.class);
        asyncSession.deleteAll(Muscle.class);
        asyncSession.deleteAll(Muscle_Exercise.class);
        asyncSession.deleteAll(User.class);
        asyncSession.deleteAll(Workout.class);
        asyncSession.deleteAll(WorkoutPlan.class);
        asyncSession.deleteAll(WorkoutSession.class);
    }


    /**
     * Note, that the operation may not have been successful, check
     * {@link AsyncOperation#isFailed()} and/or {@link AsyncOperation#getThrowable()} for error situations.
     *
     * @param operation the async operation
     */
    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);
    }
}
