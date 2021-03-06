package com.wpi.walkermagnet_inspection.data.model;

/**
 * Created by abhishek on 12/27/2016.
 */

public class Magnet {
    /**
     */
    public static final String TABLE = "magnets";

    /**
     * Table Columns names
     */
    public static final String KEY_MAGNET_ID = "magnet_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CONFIG_ID = "configuration_id";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     */
    private long mMagnetID;
    private long mUserID;
    private String mMagnetName;
    private long mConfigurationId;
    private Boolean mIsDelete;


    /**
     * Constructor
     */
    public Magnet(long id) {
        mMagnetID = id;
    }

    public Magnet(long id, String name) {
        mMagnetID = id;
        mMagnetName = name;
    }

    /**
     * Getters and Setters methods
     */

    public long getId() {
        return mMagnetID;
    }

    public void setId(long id) {
        mMagnetID = id;
    }

    private long getmUserID() {
        return mUserID;
    }

    private void setmUserID(long userId) {
        mUserID = userId;
    }

    public String getMagnetName() {
        return mMagnetName;
    }

    public void setMagnetName(String name) {
        mMagnetName = name;
    }

    public long getConfigurationId() {
        return mConfigurationId;
    }

    public void setConfigurationId(long configurationId) {
        mConfigurationId = configurationId;
    }
}
