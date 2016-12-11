
package com.navigeto.navigeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterTable_ {

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
    private Double userLat;
    @SerializedName("User_Long")
    @Expose
    private Double userLong;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("Track_User_Loc")
    @Expose
    private Long trackUserLoc;
    @SerializedName("Trip_Accept_Status")
    @Expose
    private String tripAcceptStatus;
    @SerializedName("Trip_Name")
    @Expose
    private String tripName;
    @SerializedName("Trip_Date")
    @Expose
    private String tripDate;
    @SerializedName("Trip_Time")
    @Expose
    private String tripTime;
    @SerializedName("Dest_Lat")
    @Expose
    private Double destLat;
    @SerializedName("Dest_Long")
    @Expose
    private Double destLong;
    @SerializedName("Dest_Desc")
    @Expose
    private String destDesc;
    @SerializedName("Orig_User_ID")
    @Expose
    private Long origUserID;
    @SerializedName("Trip_Stage")
    @Expose
    private String tripStage;
    @SerializedName("Last_Update")
    @Expose
    private String lastUpdate;
    @SerializedName("User_Name")
    @Expose
    private String userName;
    @SerializedName("verified")
    @Expose
    private Long verified;
    @SerializedName("Hi_Res")
    @Expose
    private String hiRes;
    @SerializedName("Lo_Res")
    @Expose
    private String loRes;
    @SerializedName("User_Email")
    @Expose
    private String userEmail;
    @SerializedName("User_Phone")
    @Expose
    private String userPhone;
    @SerializedName("Facebook_ID")
    @Expose
    private String facebookID;
    @SerializedName("Google_ID")
    @Expose
    private String googleID;
    @SerializedName("User_PicPath_Server")
    @Expose
    private String userPicPathServer;

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
    public Double getUserLat() {
        return userLat;
    }

    /**
     * 
     * @param userLat
     *     The User_Lat
     */
    public void setUserLat(Double userLat) {
        this.userLat = userLat;
    }

    /**
     * 
     * @return
     *     The userLong
     */
    public Double getUserLong() {
        return userLong;
    }

    /**
     * 
     * @param userLong
     *     The User_Long
     */
    public void setUserLong(Double userLong) {
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

    /**
     * 
     * @return
     *     The tripName
     */
    public String getTripName() {
        return tripName;
    }

    /**
     * 
     * @param tripName
     *     The Trip_Name
     */
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    /**
     * 
     * @return
     *     The tripDate
     */
    public String getTripDate() {
        return tripDate;
    }

    /**
     * 
     * @param tripDate
     *     The Trip_Date
     */
    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    /**
     * 
     * @return
     *     The tripTime
     */
    public String getTripTime() {
        return tripTime;
    }

    /**
     * 
     * @param tripTime
     *     The Trip_Time
     */
    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    /**
     * 
     * @return
     *     The destLat
     */
    public Double getDestLat() {
        return destLat;
    }

    /**
     * 
     * @param destLat
     *     The Dest_Lat
     */
    public void setDestLat(Double destLat) {
        this.destLat = destLat;
    }

    /**
     * 
     * @return
     *     The destLong
     */
    public Double getDestLong() {
        return destLong;
    }

    /**
     * 
     * @param destLong
     *     The Dest_Long
     */
    public void setDestLong(Double destLong) {
        this.destLong = destLong;
    }

    /**
     * 
     * @return
     *     The destDesc
     */
    public String getDestDesc() {
        return destDesc;
    }

    /**
     * 
     * @param destDesc
     *     The Dest_Desc
     */
    public void setDestDesc(String destDesc) {
        this.destDesc = destDesc;
    }

    /**
     * 
     * @return
     *     The origUserID
     */
    public Long getOrigUserID() {
        return origUserID;
    }

    /**
     * 
     * @param origUserID
     *     The Orig_User_ID
     */
    public void setOrigUserID(Long origUserID) {
        this.origUserID = origUserID;
    }

    /**
     * 
     * @return
     *     The tripStage
     */
    public String getTripStage() {
        return tripStage;
    }

    /**
     * 
     * @param tripStage
     *     The Trip_Stage
     */
    public void setTripStage(String tripStage) {
        this.tripStage = tripStage;
    }

    /**
     * 
     * @return
     *     The lastUpdate
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 
     * @param lastUpdate
     *     The Last_Update
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * 
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The User_Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return
     *     The verified
     */
    public Long getVerified() {
        return verified;
    }

    /**
     * 
     * @param verified
     *     The verified
     */
    public void setVerified(Long verified) {
        this.verified = verified;
    }

    /**
     * 
     * @return
     *     The hiRes
     */
    public String getHiRes() {
        return hiRes;
    }

    /**
     * 
     * @param hiRes
     *     The Hi_Res
     */
    public void setHiRes(String hiRes) {
        this.hiRes = hiRes;
    }

    /**
     * 
     * @return
     *     The loRes
     */
    public String getLoRes() {
        return loRes;
    }

    /**
     * 
     * @param loRes
     *     The Lo_Res
     */
    public void setLoRes(String loRes) {
        this.loRes = loRes;
    }

    /**
     * 
     * @return
     *     The userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 
     * @param userEmail
     *     The User_Email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 
     * @return
     *     The userPhone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 
     * @param userPhone
     *     The User_Phone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 
     * @return
     *     The facebookID
     */
    public String getFacebookID() {
        return facebookID;
    }

    /**
     * 
     * @param facebookID
     *     The Facebook_ID
     */
    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    /**
     * 
     * @return
     *     The googleID
     */
    public String getGoogleID() {
        return googleID;
    }

    /**
     * 
     * @param googleID
     *     The Google_ID
     */
    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    /**
     * 
     * @return
     *     The userPicPathServer
     */
    public String getUserPicPathServer() {
        return userPicPathServer;
    }

    /**
     * 
     * @param userPicPathServer
     *     The User_PicPath_Server
     */
    public void setUserPicPathServer(String userPicPathServer) {
        this.userPicPathServer = userPicPathServer;
    }

    @Override
    public String toString() {
        return "MasterTable_{" +
                "tripID=" + tripID +
                ", userID=" + userID +
                ", admin=" + admin +
                ", userLat=" + userLat +
                ", userLong=" + userLong +
                ", time='" + time + '\'' +
                ", trackUserLoc=" + trackUserLoc +
                ", tripAcceptStatus='" + tripAcceptStatus + '\'' +
                ", tripName='" + tripName + '\'' +
                ", tripDate='" + tripDate + '\'' +
                ", tripTime='" + tripTime + '\'' +
                ", destLat=" + destLat +
                ", destLong=" + destLong +
                ", destDesc='" + destDesc + '\'' +
                ", origUserID=" + origUserID +
                ", tripStage='" + tripStage + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", userName='" + userName + '\'' +
                ", verified=" + verified +
                ", hiRes='" + hiRes + '\'' +
                ", loRes='" + loRes + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", facebookID='" + facebookID + '\'' +
                ", googleID='" + googleID + '\'' +
                ", userPicPathServer='" + userPicPathServer + '\'' +
                '}';
    }
}
