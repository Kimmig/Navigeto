
package com.navigeto.navigeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MasterTable {

    @SerializedName("MasterTable")
    @Expose
    private List<MasterTable_> masterTable = new ArrayList<MasterTable_>();

    /**
     * 
     * @return
     *     The masterTable
     */
    public List<MasterTable_> getMasterTable() {
        return masterTable;
    }

    /**
     * 
     * @param masterTable
     *     The MasterTable
     */
    public void setMasterTable(List<MasterTable_> masterTable) {
        this.masterTable = masterTable;
    }

    @Override
    public String toString() {
        return "MasterTable{" +
                "masterTable=" + masterTable.toString() +
                '}';
    }
}
