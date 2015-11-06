package de.davidartmann.charowin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.davidartmann.charowin.db.contract.IDataBaseManager;
import de.davidartmann.charowin.db.contract.IExerciseManager;
import de.davidartmann.charowin.db.contract.IMuscleManager;
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
import de.davidartmann.charowin.db.model.MuscleDao;
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
public class ChaRoWinDatabaseManager
        implements AsyncOperationListener,
        IDataBaseManager,
        IExerciseManager,
        IMuscleManager {

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
    public synchronized void dropDatabase() {
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

    /**
     * Returns all {@link Exercise}.
     * TODO: check if empty list or null gets returned, when not entites in db
     * @return List of Exercise or empty list?
     */
    @Override
    public synchronized List<Exercise> getAllExercises() {
        List<Exercise> exercises;
        openReadableDb();
        ExerciseDao exerciseDao = daoSession.getExerciseDao();
        exercises = exerciseDao.loadAll();
        daoSession.clear();
        return exercises;
    }

    /**
     * Returns an {@link Exercise} by its id.
     * @param id the id (usually PK) of the Exercise
     * @return the Exercise or null
     */
    @Override
    public synchronized Exercise getExercise(Long id) {
        Exercise exercise = null;
        if (id != null) {
            openReadableDb();
            ExerciseDao exerciseDao = daoSession.getExerciseDao();
            exercise = exerciseDao.load(id);
            daoSession.clear();
        }
        return exercise;
    }

    /**
     * Creates an {@link Exercise} by a given model.
     * @param exercise the given model
     * @return the id of the created Exercise or null
     */
    @Override
    public synchronized Long createExercise(Exercise exercise) {
        Long rowId = null;
        if (exercise != null) {
            openWritableDb();
            ExerciseDao exerciseDao = daoSession.getExerciseDao();
            rowId = exerciseDao.insert(exercise);
            daoSession.clear();
        } else {
            Log.w(TAG, "Could not create Exercise without model");
        }
        return rowId;
    }

    /**
     * Updates an {@link Exercise} by its id and a given model.
     * @param id the id of the Exercise
     * @param exercise the model with update information
     * @return true if updated or false if not
     */
    @Override
    public synchronized Boolean updateExerciseById(Long id, Exercise exercise) {
        if (id != null && exercise != null) {
            if (getExercise(id) != null) {
                openWritableDb();
                ExerciseDao exerciseDao = daoSession.getExerciseDao();
                exerciseDao.update(exercise);
                daoSession.clear();
                Log.i(TAG, "Updated Exercise with id "+id);
                return true;
            } else {
                Log.w(TAG, "Could not update Exercise with invalid id "+id);
            }
        } else {
            Log.w(TAG, "Could not update Exercise without parameters");
        }
        return false;
    }

    /**
     * Deletes an {@link Exercise} by its id.
     * @param id the id of the Exercise
     * @return true if deleted or false if not
     */
    @Override
    public synchronized Boolean deleteExerciseById(Long id) {
        if (id != null) {
            openWritableDb();
            ExerciseDao exerciseDao = daoSession.getExerciseDao();
            exerciseDao.deleteByKey(id);
            daoSession.clear();
            Log.i(TAG, "Deleted Exercise with id "+id);
            return true;
        } else {
            Log.w(TAG, "Could not delete Exercise without id");
        }
        return false;
    }

    /**
     * Returns all {@link Muscle}
     * @return List of Muscle or empty list?
     */
    @Override
    public List<Muscle> getAllMuscles() {
        List<Muscle> muscles;
        openReadableDb();
        MuscleDao muscleDao = daoSession.getMuscleDao();
        muscles = muscleDao.loadAll();
        daoSession.clear();
        return muscles;
    }

    /**
     * Returns a {@link Muscle} by its id.
     * @param id the id (usually PK) of the Muscle
     * @return Muscle or null
     */
    @Override
    public Muscle getMuscle(Long id) {
        Muscle muscle = null;
        if (id != null) {
            openReadableDb();
            MuscleDao muscleDao = daoSession.getMuscleDao();
            muscle = muscleDao.load(id);
            daoSession.clear();
        }
        return muscle;
    }

    /**
     * Creates a {@link Muscle} by a given model.
     * @param muscle the given model
     * @return the row id of the created Muscle
     */
    @Override
    public Long createMuscle(Muscle muscle) {
        Long rowId = null;
        if (muscle != null) {
            MuscleDao muscleDao = daoSession.getMuscleDao();
            rowId = muscleDao.insert(muscle);
            daoSession.clear();
            Log.i(TAG, "Created Muscle with id "+rowId);
        } else {
            Log.w(TAG, "Could not create Muscle without id");
        }
        return rowId;
    }

    /**
     * Updates a {@link Muscle} by its id and a given model.
     * @param id the id of the Muscle (Primary Key)
     * @param muscle the given model
     * @return true if Muscle could be updated or false otherwise
     */
    @Override
    public Boolean updateMuscleById(Long id, Muscle muscle) {
        if (id != null && muscle != null) {
            if (getMuscle(id) != null) {
                MuscleDao muscleDao = daoSession.getMuscleDao();
                muscleDao.update(muscle);
                daoSession.clear();
                Log.i(TAG, "Updated Muscle with id "+id);
                return true;
            } else {
                Log.w(TAG, "Could not update Muscle with invalid id "+id);
            }
        } else {
            Log.w(TAG, "Could not update Muscle without parameters");
        }
        return false;
    }

    /**
     * Deletes a {@link Muscle} by its id.
     * @param id the given id (Primary Key)
     * @return true if Muscle could be deleted or false otherwise
     */
    @Override
    public Boolean deleteMuscleById(Long id) {
        if (id != null) {
            MuscleDao muscleDao = daoSession.getMuscleDao();
            muscleDao.deleteByKey(id);
            daoSession.clear();
            Log.i(TAG, "Deleted Muscle with id "+id);
            return true;
        } else {
            Log.w(TAG, "Could not delete Muscle without id");
        }
        return false;
    }
}
