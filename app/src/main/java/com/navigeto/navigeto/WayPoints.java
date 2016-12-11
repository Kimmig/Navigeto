
package com.navigeto.navigeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class WayPoints {

    @SerializedName("WayPoints")
    @Expose
    private List<WayPoint> wayPoints = new ArrayList<WayPoint>();

    /**
     * 
     * @return
     *     The wayPoints
     */
    public List<WayPoint> getWayPoints() {
        return wayPoints;
    }

    /**
     * 
     * @param wayPoints
     *     The WayPoints
     */
    public void setWayPoints(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    @Override
    public String toString() {
        return "WayPoints{" +
                "wayPoints=" + wayPoints.toString() +
                '}';
    }


}
