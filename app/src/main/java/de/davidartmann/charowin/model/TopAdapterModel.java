package de.davidartmann.charowin.model;

/**
 * Model class for the {@link de.davidartmann.charowin.adapter.TopAdapter}
 *
 * Created by David on 29.09.2015.
 */
public class TopAdapterModel {
    private String title;
    private int moveVertivalImageResourceId;
    private String trainingOrMealName;
    private String trainingOrMealDay;
    private String amountExercisesOrEnergyValue;
    private int cardSettingsImageResourceId;
    private String timeSinceLastExercise;
    private int averageTrainingsImageResourceId;
    private String averageTrainingsTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMoveVertivalImageResourceId() {
        return moveVertivalImageResourceId;
    }

    public void setMoveVertivalImageResourceId(int moveVertivalImageResourceId) {
        this.moveVertivalImageResourceId = moveVertivalImageResourceId;
    }

    public String getTrainingOrMealName() {
        return trainingOrMealName;
    }

    public void setTrainingOrMealName(String trainingOrMealName) {
        this.trainingOrMealName = trainingOrMealName;
    }

    public String getTrainingOrMealDay() {
        return trainingOrMealDay;
    }

    public void setTrainingOrMealDay(String trainingOrMealDay) {
        this.trainingOrMealDay = trainingOrMealDay;
    }

    public String getAmountExercisesOrEnergyValue() {
        return amountExercisesOrEnergyValue;
    }

    public void setAmountExercisesOrEnergyValue(String amountExercisesOrEnergyValue) {
        this.amountExercisesOrEnergyValue = amountExercisesOrEnergyValue;
    }

    public int getCardSettingsImageResourceId() {
        return cardSettingsImageResourceId;
    }

    public void setCardSettingsImageResourceId(int cardSettingsImageResourceId) {
        this.cardSettingsImageResourceId = cardSettingsImageResourceId;
    }

    public String getTimeSinceLastExercise() {
        return timeSinceLastExercise;
    }

    public void setTimeSinceLastExercise(String timeSinceLastExercise) {
        this.timeSinceLastExercise = timeSinceLastExercise;
    }

    public int getAverageTrainingsImageResourceId() {
        return averageTrainingsImageResourceId;
    }

    public void setAverageTrainingsImageResourceId(int averageTrainingsImageResourceId) {
        this.averageTrainingsImageResourceId = averageTrainingsImageResourceId;
    }

    public String getAverageTrainingsTime() {
        return averageTrainingsTime;
    }

    public void setAverageTrainingsTime(String averageTrainingsTime) {
        this.averageTrainingsTime = averageTrainingsTime;
    }
}
