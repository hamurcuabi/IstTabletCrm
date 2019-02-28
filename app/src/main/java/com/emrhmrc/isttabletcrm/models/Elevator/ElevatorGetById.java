package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

public class ElevatorGetById {
    @SerializedName("TableMetadataElevator")
    private TableMetadata TableMetadataElevator;
    @SerializedName("TableMetadataServAppDetails")
    private TableMetadata TableMetadataServAppDetails;
    @SerializedName("TableMetadataServApps")
    private TableMetadata TableMetadataServApps;


    private Elevator Elevator;

    private String ErrorMsg;

    private boolean Success;

    public TableMetadata getTableMetadataElevator() {
        return TableMetadataElevator;
    }

    public void setTableMetadataElevator(TableMetadata TableMetadataElevator) {
        this.TableMetadataElevator = TableMetadataElevator;
    }

    public TableMetadata getTableMetadataServAppDetails() {
        return TableMetadataServAppDetails;
    }

    public void setTableMetadataServAppDetails(TableMetadata TableMetadataServAppDetails) {
        this.TableMetadataServAppDetails = TableMetadataServAppDetails;
    }

    public TableMetadata getTableMetadataServApps() {
        return TableMetadataServApps;
    }

    public void setTableMetadataServApps(TableMetadata TableMetadataServApps) {
        this.TableMetadataServApps = TableMetadataServApps;
    }

    public Elevator getElevator() {
        return Elevator;
    }

    public void setElevator(Elevator Elevator) {
        this.Elevator = Elevator;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    @Override
    public String toString() {
        return "[TableMetadataElevator = " + TableMetadataElevator + ", TableMetadataServAppDetails = " + TableMetadataServAppDetails + ", TableMetadataServApps = " + TableMetadataServApps + ", Elevator = " + Elevator + ", ErrorMsg = " + ErrorMsg + ", Success = " + Success + "]";
    }
}
