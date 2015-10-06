package de.davidartmann.charowin.adapter.user.model;

/**
 * ListView item for the user view.
 * 
 * Created by David on 06.10.2015.
 */
public class UserElements {

    private int icon;
    private String headline;
    private String subHeadline;
    private boolean checkableItem;

    public UserElements(int icon, String headline, String subHeadline, boolean checkableItem) {
        this.icon = icon;
        this.headline = headline;
        this.subHeadline = subHeadline;
        this.checkableItem = checkableItem;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSubHeadline() {
        return subHeadline;
    }

    public void setSubHeadline(String subHeadline) {
        this.subHeadline = subHeadline;
    }

    public boolean isCheckableItem() {
        return checkableItem;
    }

    public void setCheckableItem(boolean checkableItem) {
        this.checkableItem = checkableItem;
    }
}
