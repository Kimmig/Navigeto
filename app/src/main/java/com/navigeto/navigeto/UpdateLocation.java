
package com.navigeto.navigeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class UpdateLocation {

    @SerializedName("UpdateLocation")
    @Expose
    private List<MasterTable_> updateLocation = new ArrayList<MasterTable_>();

    /**
     * 
     * @return
     *     The updateLocation
     */
    public List<MasterTable_> getUpdateLocation() {
        return updateLocation;
    }

    /**
     * 
     * @param updateLocation
     *     The UpdateLocation
     */
    public void setUpdateLocation(List<MasterTable_> updateLocation) {
        this.updateLocation = updateLocation;
    }

}
