package com.emrhmrc.isttabletcrm.models.Elevator;

public class ElevatorGetById
{
    private TableMetadataElevator TableMetadataElevator;

    private TableMetadataServAppDetails TableMetadataServAppDetails;

    private TableMetadataServApps TableMetadataServApps;

    private Elevator Elevator;

    private String ErrorMsg;

    private String Success;

    public TableMetadataElevator getTableMetadataElevator ()
    {
        return TableMetadataElevator;
    }

    public void setTableMetadataElevator (TableMetadataElevator TableMetadataElevator)
    {
        this.TableMetadataElevator = TableMetadataElevator;
    }

    public TableMetadataServAppDetails getTableMetadataServAppDetails ()
    {
        return TableMetadataServAppDetails;
    }

    public void setTableMetadataServAppDetails (TableMetadataServAppDetails TableMetadataServAppDetails)
    {
        this.TableMetadataServAppDetails = TableMetadataServAppDetails;
    }

    public TableMetadataServApps getTableMetadataServApps ()
    {
        return TableMetadataServApps;
    }

    public void setTableMetadataServApps (TableMetadataServApps TableMetadataServApps)
    {
        this.TableMetadataServApps = TableMetadataServApps;
    }

    public Elevator getElevator ()
    {
        return Elevator;
    }

    public void setElevator (Elevator Elevator)
    {
        this.Elevator = Elevator;
    }

    public String getErrorMsg ()
    {
        return ErrorMsg;
    }

    public void setErrorMsg (String ErrorMsg)
    {
        this.ErrorMsg = ErrorMsg;
    }

    public String getSuccess ()
    {
        return Success;
    }

    public void setSuccess (String Success)
    {
        this.Success = Success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TableMetadataElevator = "+TableMetadataElevator+", TableMetadataServAppDetails = "+TableMetadataServAppDetails+", TableMetadataServApps = "+TableMetadataServApps+", Elevator = "+Elevator+", ErrorMsg = "+ErrorMsg+", Success = "+Success+"]";
    }
}
