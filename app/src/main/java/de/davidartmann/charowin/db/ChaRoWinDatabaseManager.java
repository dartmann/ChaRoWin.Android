package de.davidartmann.charowin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.davidartmann.charowin.db.model.DaoMaster;
import de.davidartmann.charowin.db.model.DaoSession;
import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;

/**
 * Created by David on 14.10.2015.
 */
public class ChaRoWinDatabaseManager implements AsyncOperationListener {

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
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
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
