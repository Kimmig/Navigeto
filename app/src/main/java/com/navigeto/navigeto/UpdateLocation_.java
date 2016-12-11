
package com.navigeto.navigeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UpdateLocation_ {

    @SerializedName("Trip_ID")
    @Expose
    private Long tripID;
    @SerializedName("User_ID")
    @Expose
    private Long userID;
    @SerializedName("Admin")
    @Expose
    private Long admin;
    @SerializedName("User_Lat")
    @Expose
    private Float userLat;
    @SerializedName("User_Long")
    @Expose
    private Float userLong;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("Track_User_Loc")
    @Expose
    private Long trackUserLoc;
    @SerializedName("Trip_Accept_Status")
    @Expose
    private String tripAcceptStatus;

    /**
     * 
     * @return
     *     The tripID
     */
    public Long getTripID() {
        return tripID;
    }

    /**
     * 
     * @param tripID
     *     The Trip_ID
     */
    public void setTripID(Long tripID) {
        this.tripID = tripID;
    }

    /**
     * 
     * @return
     *     The userID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * 
     * @param userID
     *     The User_ID
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * 
     * @return
     *     The admin
     */
    public Long getAdmin() {
        return admin;
    }

    /**
     * 
     * @param admin
     *     The Admin
     */
    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    /**
     * 
     * @return
     *     The userLat
     */
    public Float getUserLat() {
        return userLat;
    }

    /**
     * 
     * @param userLat
     *     The User_Lat
     */
    public void setUserLat(Float userLat) {
        this.userLat = userLat;
    }

    /**
     * 
     * @return
     *     The userLong
     */
    public Float getUserLong() {
        return userLong;
    }

    /**
     * 
     * @param userLong
     *     The User_Long
     */
    public void setUserLong(Float userLong) {
        this.userLong = userLong;
    }

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The Time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The trackUserLoc
     */
    public Long getTrackUserLoc() {
        return trackUserLoc;
    }

    /**
     * 
     * @param trackUserLoc
     *     The Track_User_Loc
     */
    public void setTrackUserLoc(Long trackUserLoc) {
        this.trackUserLoc = trackUserLoc;
    }

    /**
     * 
     * @return
     *     The tripAcceptStatus
     */
    public String getTripAcceptStatus() {
        return tripAcceptStatus;
    }

    /**
     * 
     * @param tripAcceptStatus
     *     The Trip_Accept_Status
     */
    public void setTripAcceptStatus(String tripAcceptStatus) {
        this.tripAcceptStatus = tripAcceptStatus;
    }

}
