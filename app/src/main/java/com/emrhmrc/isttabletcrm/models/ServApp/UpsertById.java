package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpsertById {
   private String UserId;
   @SerializedName("ServAppChangedFields")
    private List<String> servAppChangedFields;
   private ServiceAppointment serviceAppointment;
}
