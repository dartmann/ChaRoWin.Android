package de.davidartmann.charowin.model;

/**
 * Created by David on 25.09.2015.
 */
public class Exercise extends BaseModel {

    private String name;

    //TODO: implement relations to muscel and workout

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
