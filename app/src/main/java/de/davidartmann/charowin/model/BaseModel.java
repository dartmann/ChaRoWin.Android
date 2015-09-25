package de.davidartmann.charowin.model;

/**
 * Created by David on 25.09.2015.
 */
public class BaseModel {

    private Boolean active;

    //TODO: more attributes (id, version, createDate, updateDate)?

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
