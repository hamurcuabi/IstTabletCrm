package com.emrhmrc.isttabletcrm.helper;

import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;

public class CreateSubServAppSingleton {
    private static final CreateSubServAppSingleton ourInstance = new CreateSubServAppSingleton();
    private ServAppGetById servAppGetById;

    private CreateSubServAppSingleton() {
    }

    public static CreateSubServAppSingleton getInstance() {
        return ourInstance;
    }

    public ServAppGetById getServAppGetById() {
        return servAppGetById;
    }

    public void setServAppGetById(ServAppGetById servAppGetById) {
        this.servAppGetById = servAppGetById;
    }
}
