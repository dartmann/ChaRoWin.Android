package de.davidartmann.charowin.db.contract;

/**
 * General Interface for each DbManager class.
 * For each Entity there is a seperate Interface which descripes the methods to implement.
 *
 * Created by David on 14.10.2015.
 */
public interface IOpenHelper {

    //TODO: think about adding a db manager class per model

    public void openReadableDb();

    public void openWritableDb();

    public void closeDbConnections();

    public void dropDatabase();


}
