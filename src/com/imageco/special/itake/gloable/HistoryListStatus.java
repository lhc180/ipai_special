package com.imageco.special.itake.gloable;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-22
 * Time: 下午6:09
 */
public class HistoryListStatus {
    /**
     * Method getPositionStatus returns the positionStatus of this HistoryListStatus object.
     *
     * @return the positionStatus (type HashMap<String, Boolean>) of this HistoryListStatus object.
     */
    public HashMap<String, Boolean> getPositionStatus() {
        return positionStatus;
    }

    /**
     * Method setPositionStatus sets the positionStatus of this HistoryListStatus object.
     *
     * @param positionStatus the positionStatus of this HistoryListStatus object.
     */
    public void setPositionStatus(HashMap<String, Boolean> positionStatus) {
        this.positionStatus = positionStatus;
    }

    /**
     * Field positionStatus
     */
    private HashMap<String, Boolean> positionStatus;

    /**
     * Field INSTANCE
     */
    private static HistoryListStatus INSTANCE = null;


    /**
     * Method getInstance returns the instance of this HistoryListStatus object.
     *
     * @return the instance (type HistoryListStatus) of this HistoryListStatus object.
     */
    public static HistoryListStatus getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HistoryListStatus();
        }
        return INSTANCE;
    }
}
