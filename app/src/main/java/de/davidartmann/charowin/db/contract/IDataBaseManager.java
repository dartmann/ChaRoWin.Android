package de.davidartmann.charowin.db.contract;

/**
 * General Interface for each DbManager class.
 * For each Entity there is a seperate Interface which descripes the methods to implement.
 *
 * Created by David on 14.10.2015.
 */
public interface IDataBaseManager {

    void openReadableDb();

    void openWritableDb();

    void closeDbConnections();

    void dropDatabase();
}
