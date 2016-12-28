package database.dao;

import database.objectDetails.snortLog;
import javafx.collections.ObservableList;

/**
 * Created by runtitc on 11/15/16.
 */
public interface SnortLogDao {
    ObservableList<snortLog> selectLogs();
}
