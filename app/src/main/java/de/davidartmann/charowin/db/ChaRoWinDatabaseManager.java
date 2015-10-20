package de.davidartmann.charowin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.davidartmann.charowin.db.contract.IDataBaseManager;
import de.davidartmann.charowin.db.contract.IExerciseManager;
import de.davidartmann.charowin.db.model.DaoMaster;
import de.davidartmann.charowin.db.model.DaoSession;
import de.davidartmann.charowin.db.model.DietPlan;
import de.davidartmann.charowin.db.model.Exercise;
import de.davidartmann.charowin.db.model.ExerciseDao;
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
public class ChaRoWinDatabaseManager implements AsyncOperationListener, IDataBaseManager, IExerciseManager {

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
        try {
            sqLiteDatabase = devOpenHelper.getReadableDatabase();
        } catch (SQLiteException se) {
            Log.w(TAG, "SQLiteException in openReadableDb()");
            se.printStackTrace();
        }
        if (sqLiteDatabase != null) {
            daoMaster = new DaoMaster(sqLiteDatabase);
            daoSession = daoMaster.newSession();
            asyncSession = daoSession.startAsyncSession();
            asyncSession.setListener(this);
        } else {
            Log.w(TAG, "sqLiteDatabase was null in openReadableDb()");
        }
    }

    public void openWritableDb() {
        try {
            sqLiteDatabase = devOpenHelper.getWritableDatabase();
        } catch (SQLiteException se) {
            Log.w(TAG, "SQLiteException in openWritableDb()");
            se.printStackTrace();
        }
        if (sqLiteDatabase != null) {
            daoMaster = new DaoMaster(sqLiteDatabase);
            daoSession = daoMaster.newSession();
            asyncSession = daoSession.startAsyncSession();
            asyncSession.setListener(this);
        } else {
            Log.w(TAG, "sqLiteDatabase was null in openWritableDb()");
        }
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

    @Override
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises;
        openReadableDb();
        ExerciseDao exerciseDao = daoSession.getExerciseDao();
        exercises = exerciseDao.loadAll();
        daoSession.clear();
        return exercises;
    }

    /**
     * Returns an {@link Exercise} by its id.
     * @param id the id of the Exercise
     * @return the Exercise or null
     */
    @Override
    public Exercise getExercise(Long id) {
        Exercise exercise = null;
        if (id != null) {
            openReadableDb();
            ExerciseDao exerciseDao = daoSession.getExerciseDao();
            exercise = exerciseDao.load(id);
            daoSession.clear();
        }
        return exercise;
    }

    @Override
    public Long createExercise(Exercise exercise) {
        Long rowId = null;
        if (exercise != null) {
            openWritableDb();
            ExerciseDao exerciseDao = daoSession.getExerciseDao();
            rowId = exerciseDao.insert(exercise);
        } else {
            Log.w(TAG, "Could not create Exercise without model");
        }
        return rowId;
    }

    @Override
    public Exercise updateExerciseById(Long id, Exercise exercise) {
        return null;
    }

    @Override
    public Boolean deleteExerciseById(Long id) {
        return null;
    }
}
