package de.davidartmann.charowin.adapter.training.model;

/**
 * Model class for a workout plan.
 *
 * Created by David on 08.10.2015.
 */
public class WorkoutPlanNew {

    private String name;
    private String description;
    private String amountDays;

    public WorkoutPlanNew() {}

    public WorkoutPlanNew(String name, String description, String amountDays) {
        this.name = name;
        this.description = description;
        this.amountDays = amountDays;
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
}
