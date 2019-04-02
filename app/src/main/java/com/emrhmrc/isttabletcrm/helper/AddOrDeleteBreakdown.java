package com.emrhmrc.isttabletcrm.helper;

import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppBreakdownTypes;

public interface AddOrDeleteBreakdown {

    public void addBreakdown(ServAppGetByIdServAppBreakdownTypes item);

    public void updateBreakdown(String id, boolean deleteStatus);

    public void deleteBreakdown(String id);
}
