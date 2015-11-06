package de.davidartmann.charowin.adapter.training.model;

/**
 * Model class for a workout plan.
 * <p/>
 * Created by David on 08.10.2015.
 */
public class WorkoutPlanAdapterModel {

    private String name;
    private String description;
    private String amountDays;
    private Boolean currentFavorite;

    public WorkoutPlanAdapterModel() {}

    public WorkoutPlanAdapterModel(String name, String description, String amountDays, Boolean currentFavorite) {
        this.name = name;
        this.description = description;
        this.amountDays = amountDays;
        this.currentFavorite = currentFavorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(String amountDays) {
        this.amountDays = amountDays;
    }

    public Boolean isCurrentFavorite() {
        return currentFavorite;
    }

    public void setCurrentFavorite(Boolean currentFavorite) {
        this.currentFavorite = currentFavorite;
    }
}
