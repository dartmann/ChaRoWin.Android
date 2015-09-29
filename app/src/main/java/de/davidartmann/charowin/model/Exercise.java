package de.davidartmann.charowin.model;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by David on 25.09.2015.
 */
public class Exercise extends BaseModel {

    //TODO: implement relations to muscel and workout

    private String name;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
