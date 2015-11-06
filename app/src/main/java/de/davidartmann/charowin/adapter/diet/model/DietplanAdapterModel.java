package de.davidartmann.charowin.adapter.diet.model;

/**
 * Model class for a dietplan.
 *
 * Created by David on 07.10.2015.
 */
public class DietplanAdapterModel {

    private String dietplanName;
    private String description;
    private String amountDays;
    private String amountMeals;
    private String energyKcal;
    private Boolean currentFavorite;

    public DietplanAdapterModel(String mealName, String description, String amountDays, String amountMeals, String energyKcal, Boolean currentFavorite) {
        this.dietplanName = mealName;
        this.description = description;
        this.amountDays = amountDays;
        this.amountMeals = amountMeals;
        this.energyKcal = energyKcal;
        this.currentFavorite = currentFavorite;
    }

    public String getDietplanName() {
        return dietplanName;
    }

    public void setDietplanName(String dietplanName) {
        this.dietplanName = dietplanName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnergyKcal() {
        return energyKcal;
    }

    public void setEnergyKcal(String energyKcal) {
        this.energyKcal = energyKcal;
    }

    public String getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(String amountDays) {
        this.amountDays = amountDays;
    }

    public String getAmountMeals() {
        return amountMeals;
    }

    public void setAmountMeals(String amountMeals) {
        this.amountMeals = amountMeals;
    }

    public Boolean isCurrentFavorite() {
        return currentFavorite;
    }

    public void setCurrentFavorite(Boolean currentFavorite) {
        this.currentFavorite = currentFavorite;
    }
}
