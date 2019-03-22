package com.emrhmrc.isttabletcrm.models.User;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.google.gson.annotations.SerializedName;

public class UserLogin {
    @SerializedName("Succes")
    private boolean Success;
    private String ErrorMsg;
    private String UserId;
    private String UserName;
    private String Description;
    private Inv_Id SuperVisorId;
    private Inv_Id CustomerId;

    public UserLogin() {

    }

    public Inv_Id getSuperVisorId() {
        return SuperVisorId;
    }

    public void setSuperVisorId(Inv_Id superVisorId) {
        SuperVisorId = superVisorId;
    }

    public Inv_Id getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Inv_Id customerId) {
        CustomerId = customerId;
    }

    public boolean isSuccess() {
        return Success;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    // Getter Methods

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    // Setter Methods

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "Success=" + Success +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}