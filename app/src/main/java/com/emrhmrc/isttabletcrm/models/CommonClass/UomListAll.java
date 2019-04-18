package com.emrhmrc.isttabletcrm.models.CommonClass;

import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;

import java.util.List;

public class UomListAll extends DefaultResponse {

    private List<Inv_Uom> UomList;


    public UomListAll() {
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<Inv_Uom> getUomList() {
        return UomList;
    }

    public void setUomList(List<Inv_Uom> uomList) {
        UomList = uomList;
    }
}
