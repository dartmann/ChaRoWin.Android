package de.davidartmann.charowin.adapter.diet.model;

/**
 * Model class for a meal.
 *
 * Created by David on 07.10.2015.
 */
public class MealAdapterModel {

    private String imageUrl;
    private String mealName;
    private String mealTime;
    private String energyKcal;

    public MealAdapterModel(String imageUrl, String mealName, String mealTime, String energyKcal) {
        this.imageUrl = imageUrl;
        this.mealName = mealName;
        this.mealTime = mealTime;
        this.energyKcal = energyKcal;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public String getEnergyKcal() {
        return energyKcal;
    }

    public void setEnergyKcal(String energyKcal) {
        this.energyKcal = energyKcal;
    }
}
