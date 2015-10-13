package de.davidartmann.charowin.adapter.user.model;

/**
 * ListView item for the user view.
 * 
 * Created by David on 06.10.2015.
 */
public class UserElement {

    private Integer icon;
    private String headline;
    private String subHeadline;
    private boolean checkableItem;

    public UserElement() {}

    public UserElement(int icon, String headline, String subHeadline, boolean checkableItem) {
        this.icon = icon;
        this.headline = headline;
        this.subHeadline = subHeadline;
        this.checkableItem = checkableItem;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
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
