package de.davidartmann.charowin.adapter.model;

/**
 * Created by David on 01.10.2015.
 */
public class TrainingFragmentOverviewItem {
    private int icon;
    private String text;

    public TrainingFragmentOverviewItem(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {

        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
