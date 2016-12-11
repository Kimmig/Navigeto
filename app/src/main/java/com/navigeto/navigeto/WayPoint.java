
package com.navigeto.navigeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WayPoint {

    @SerializedName("WP_ID")
    @Expose
    private Long wPID;
    @SerializedName("Trip_ID")
    @Expose
    private Long tripID;
    @SerializedName("User_ID_Source")
    @Expose
    private Long userIDSource;
    @SerializedName("User_ID_Target")
    @Expose
    private Long userIDTarget;
    @SerializedName("WP_Lat")
    @Expose
    private Double wPLat;
    @SerializedName("WP_Long")
    @Expose
    private Double wPLong;
    @SerializedName("WP_Desc")
    @Expose
    private String wPDesc;
    @SerializedName("WP_Accept_Status")
    @Expose
    private Long wPAcceptStatus;
    @SerializedName("WP_Date")
    @Expose
    private String wPDate;
    @SerializedName("WP_Time")
    @Expose
    private String wPTime;

    /**
     * 
     * @return
     *     The wPID
     */
    public Long getWPID() {
        return wPID;
    }

    /**
     * 
     * @param wPID
     *     The WP_ID
     */
    public void setWPID(Long wPID) {
        this.wPID = wPID;
    }

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
     *     The userIDSource
     */
    public Long getUserIDSource() {
        return userIDSource;
    }

    /**
     * 
     * @param userIDSource
     *     The User_ID_Source
     */
    public void setUserIDSource(Long userIDSource) {
        this.userIDSource = userIDSource;
    }

    /**
     * 
     * @return
     *     The userIDTarget
     */
    public Long getUserIDTarget() {
        return userIDTarget;
    }

    /**
     * 
     * @param userIDTarget
     *     The User_ID_Target
     */
    public void setUserIDTarget(Long userIDTarget) {
        this.userIDTarget = userIDTarget;
    }

    /**
     * 
     * @return
     *     The wPLat
     */
    public Double getWPLat() {
        return wPLat;
    }

    /**
     * 
     * @param wPLat
     *     The WP_Lat
     */
    public void setWPLat(Double wPLat) {
        this.wPLat = wPLat;
    }

    /**
     * 
     * @return
     *     The wPLong
     */
    public Double getWPLong() {
        return wPLong;
    }

    /**
     * 
     * @param wPLong
     *     The WP_Long
     */
    public void setWPLong(Double wPLong) {
        this.wPLong = wPLong;
    }

    /**
     * 
     * @return
     *     The wPDesc
     */
    public String getWPDesc() {
        return wPDesc;
    }

    /**
     * 
     * @param wPDesc
     *     The WP_Desc
     */
    public void setWPDesc(String wPDesc) {
        this.wPDesc = wPDesc;
    }

    /**
     * 
     * @return
     *     The wPAcceptStatus
     */
    public Long getWPAcceptStatus() {
        return wPAcceptStatus;
    }

    /**
     * 
     * @param wPAcceptStatus
     *     The WP_Accept_Status
     */
    public void setWPAcceptStatus(Long wPAcceptStatus) {
        this.wPAcceptStatus = wPAcceptStatus;
    }

    /**
     * 
     * @return
     *     The wPDate
     */
    public String getWPDate() {
        return wPDate;
    }

    /**
     * 
     * @param wPDate
     *     The WP_Date
     */
    public void setWPDate(String wPDate) {
        this.wPDate = wPDate;
    }

    /**
     * 
     * @return
     *     The wPTime
     */
    public String getWPTime() {
        return wPTime;
    }

    /**
     * 
     * @param wPTime
     *     The WP_Time
     */
    public void setWPTime(String wPTime) {
        this.wPTime = wPTime;
    }

    @Override
    public String toString() {
        return "WayPoint{" +
                "WP_ID=" + wPID +
                ", Trip_ID=" + tripID +
                ", User_ID_Source=" + userIDSource +
                ", User_ID_Target=" + userIDTarget +
                ", WP_Lat=" + wPLat +
                ", WP_Long='" + wPLong + '\'' +
                ", WP_Desc=" + wPDesc +
                ", WP_Accept_Status='" + wPAcceptStatus + '\'' +
                ", WP_Date='" + wPDate + '\'' +
                ", WP_Time='" + wPTime + '\'' +
                '}';
    }


}
