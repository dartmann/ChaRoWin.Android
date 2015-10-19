package de.davidartmann.charowin.db;

/**
 * Created by David on 14.10.2015.
 */
public interface IOpenHelper {

    //TODO: think about adding a db manager class per model

    public void openReadableDb();

    public void openWritableDb();

    public void closeDbConnections();

    public void dropDatabase();


}
