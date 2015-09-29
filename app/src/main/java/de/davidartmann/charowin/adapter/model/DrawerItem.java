package de.davidartmann.charowin.adapter.model;

/**
 * Model class for the {@link de.davidartmann.charowin.adapter.DrawerAdapter}.
 * Created by David on 28.09.2015.
 */
public class DrawerItem {
    private int icon;
    private String text;

    public DrawerItem(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
