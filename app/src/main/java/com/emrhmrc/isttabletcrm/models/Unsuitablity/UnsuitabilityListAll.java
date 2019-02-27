package com.emrhmrc.isttabletcrm.models.Unsuitablity;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class UnsuitabilityListAll {

    private boolean Succes;
    private String ErrorMsg;
  private   TableMetadata TableMetadata;
    private List<Unsuitabilities> Unsuitabilities;

    public UnsuitabilityListAll() {
    }

    public boolean isSucces() {
        return Succes;
    }

    public void setSucces(boolean succes) {
        Succes = succes;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata tableMetadata) {
        TableMetadata = tableMetadata;
    }

    public List<com.emrhmrc.isttabletcrm.models.Unsuitablity.Unsuitabilities> getUnsuitabilities() {
        return Unsuitabilities;
    }

    public void setUnsuitabilities(List<com.emrhmrc.isttabletcrm.models.Unsuitablity.Unsuitabilities> unsuitabilities) {
        Unsuitabilities = unsuitabilities;
    }
}
