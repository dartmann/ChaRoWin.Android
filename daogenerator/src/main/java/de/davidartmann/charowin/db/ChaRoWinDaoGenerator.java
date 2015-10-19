package de.davidartmann.charowin.db;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class ChaRoWinDaoGenerator {

    //TODO: check how a MappedSuperClass could be realized

    public static void main(String[] args) {
        Schema schema = new Schema(1, "de.davidartmann.charowin.db.model");
        addTables(schema);
        try {
            new DaoGenerator().generateAll(schema, "../app/src/main/java");
        } catch (IOException e) {
            System.out.println("Problem with i/o while DaoGenerator.generateAll()");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("General problem while DaoGenerator.generateAll()");
            e.printStackTrace();
        }
    }

    private static void addTables(Schema schema) {
        /**
         * Entities
         */
        Entity muscle = addMuscleEntity(schema);
        Entity exercise = addExerciseEntity(schema);
        Entity muscle_exercise = addMuscleExerciseEntity(schema);
        Entity workout = addWorkoutEntity(schema);
        Entity exercise_workout = addExerciseWorkoutEntity(schema);
        Entity workoutSession = addWorkoutSessionEntity(schema);
        Entity workoutPlan = addWorkoutPlanEntity(schema);
        Entity user = addUserEntity(schema);
        Entity dietPlan = addDietPlanEntity(schema);
        Entity meal = addMealEntity(schema);
        Entity meal_dietPlan = addMealDietPlanEntity(schema);
        Entity food = addFoodEntity(schema);
        Entity food_meal = addFoodMealEntity(schema);
        /**
         * Properties
         */
        //----------------------1:m in Workout:WorkoutSession----------------------
        Property workoutIdForWorkoutSession =
                workoutSession.addLongProperty("workout_id").notNull().getProperty();
        //----------------------m:n in Muscle:Exercise----------------------
        Property muscleIdForMuscleExercise =
                muscle_exercise.addLongProperty("muscle_id_for_muscle_exercise").notNull().getProperty();
        Property exerciseIdForMuscleExercise =
                muscle_exercise.addLongProperty("exercise_id_for_muscle_exercise").notNull().getProperty();
        //----------------------m:n in Exercise:Workout----------------------
        Property exerciseIdForExerciseWorkout =
                exercise_workout.addLongProperty("exercise_id_for_exercise_workout").notNull().getProperty();
        Property workoutIdForExerciseWorkout =
                exercise_workout.addLongProperty("workout_id_for_exercise_workout").notNull().getProperty();
        //----------------------1:m in WorkoutPlan:Workout----------------------
        Property workoutPlanIdForWorkout =
                workout.addLongProperty("workoutPlan_id").notNull().getProperty();
        //----------------------1:m in User:WorkoutPlan----------------------
        Property userIdForWorkoutPlan =
                workoutPlan.addLongProperty("user_id").notNull().getProperty();
        //----------------------1:m in User:DietPlan----------------------
        Property userIdForDietPlan =
                dietPlan.addLongProperty("user_id").notNull().getProperty();
        //----------------------m:n in DietPlan:Meal----------------------
        Property mealIdForMealDietPlan =
                meal_dietPlan.addLongProperty("meal_id_for_meal_dietplan").notNull().getProperty();
        Property dietPlanIdForMealDietPlan =
                meal_dietPlan.addLongProperty("dietPlan_id_for_meal_dietplan").notNull().getProperty();
        //----------------------m:n in Food:Meal----------------------
        Property foodIdForFoodMeal =
                food_meal.addLongProperty("food_id_for_food_meal").notNull().getProperty();
        Property mealIdForFoodMeal =
                food_meal.addLongProperty("meal_id_for_food_meal").notNull().getProperty();
        /**
         * Relations
         */
        //----------------------1:m in Workout:WorkoutSession----------------------
        ToMany workoutToWorkoutSessions =
                workout.addToMany(workoutSession, workoutIdForWorkoutSession);
        workoutToWorkoutSessions.setName("workoutSession_id");
        //----------------------m:n in Muscle:Exercise----------------------
        ToMany musclesToMuscleExercises =
                muscle_exercise.addToMany(muscle, muscleIdForMuscleExercise);
        musclesToMuscleExercises.setName("muscles");
        ToMany exercisesToMuscleExercise =
                muscle_exercise.addToMany(exercise, exerciseIdForMuscleExercise);
        exercisesToMuscleExercise.setName("exercises");
        //----------------------m:n in Exercise:Workout----------------------
        ToMany exercisesToExerciseWorkout =
                exercise_workout.addToMany(exercise, exerciseIdForExerciseWorkout);
        exercisesToExerciseWorkout.setName("exercises");
        ToMany workoutsToExerciseWorkout =
                exercise_workout.addToMany(workout, workoutIdForExerciseWorkout);
        workoutsToExerciseWorkout.setName("workouts");
        //----------------------1:m in WorkoutPlan:Workout----------------------
        ToMany workoutPlanToWorkouts =
                workoutPlan.addToMany(workout, workoutPlanIdForWorkout);
        workoutPlanToWorkouts.setName("workout_id");
        //----------------------1:m in User:WorkoutPlan----------------------
        ToMany userToWorkoutPlans =
                user.addToMany(workoutPlan, userIdForWorkoutPlan);
        userToWorkoutPlans.setName("workoutPlan_id");
        //----------------------1:m in User:DietPlan----------------------
        ToMany userToDietPlans =
                user.addToMany(dietPlan, userIdForDietPlan);
        userToDietPlans.setName("dietPlan_id");
        //----------------------m:n in DietPlan:Meal----------------------
        ToMany mealsToMealDietPlan =
                meal_dietPlan.addToMany(meal, mealIdForMealDietPlan);
        mealsToMealDietPlan.setName("meals");
        ToMany dietPlanToMealDietPlan =
                meal_dietPlan.addToMany(dietPlan, dietPlanIdForMealDietPlan);
        dietPlanToMealDietPlan.setName("dietPlans");
        //----------------------m:n in Food:Meal----------------------
        ToMany foodsToFoodMeal =
                food_meal.addToMany(food, foodIdForFoodMeal);
        foodsToFoodMeal.setName("foods");
        ToMany mealsToFoodMeal =
                food_meal.addToMany(meal, mealIdForFoodMeal);
        mealsToFoodMeal.setName("meals");
    }

    private static Entity addFoodMealEntity(Schema schema) {
        Entity entityFoodMeal = schema.addEntity("food_meal");
        entityFoodMeal.addIdProperty().primaryKey().autoincrement();
        return entityFoodMeal;
    }

    private static Entity addMealDietPlanEntity(Schema schema) {
        Entity entityMealDietPlan = schema.addEntity("meal_dietplan");
        entityMealDietPlan.addIdProperty().primaryKey().autoincrement();
        return entityMealDietPlan;
    }

    private static Entity addExerciseWorkoutEntity(Schema schema) {
        Entity entityExerciseWorkout = schema.addEntity("exercise_workout");
        entityExerciseWorkout.addIdProperty().primaryKey().autoincrement();
        return entityExerciseWorkout;
    }

    private static Entity addMuscleExerciseEntity(Schema schema) {
        Entity entityMuscleExercise = schema.addEntity("muscle_exercise");
        entityMuscleExercise.addIdProperty().primaryKey().autoincrement();
        return entityMuscleExercise;
    }

    private static Entity addFoodEntity(Schema schema) {
        Entity entityFood = schema.addEntity("food");
        entityFood.addIdProperty().primaryKey().autoincrement();
        entityFood.addStringProperty("name");
        entityFood.addFloatProperty("weight");
        return entityFood;
    }

    private static Entity addMealEntity(Schema schema) {
        Entity entityMeal = schema.addEntity("meal");
        entityMeal.addIdProperty().primaryKey().autoincrement();
        entityMeal.addStringProperty("name");
        entityMeal.addLongProperty("eating_time");
        entityMeal.addStringProperty("weekday");
        return entityMeal;
    }

    private static Entity addMuscleEntity(Schema schema) {
        Entity entityMuscle = schema.addEntity("muscle");
        entityMuscle.addIdProperty().primaryKey().autoincrement();
        entityMuscle.addStringProperty("name");
        return entityMuscle;
    }

    private static Entity addExerciseEntity(Schema schema) {
        Entity entityExercise = schema.addEntity("exercise");
        entityExercise.addIdProperty().primaryKey().autoincrement();
        entityExercise.addStringProperty("name");
        return entityExercise;
    }


    private static Entity addWorkoutEntity(Schema schema) {
        Entity entityWorkout = schema.addEntity("workout");
        entityWorkout.addIdProperty().autoincrement().primaryKey();
        entityWorkout.addStringProperty("name");
        entityWorkout.addStringProperty("weekday");
        entityWorkout.addIntProperty("numberOfDay");
        return entityWorkout;
    }


    private static Entity addWorkoutSessionEntity(Schema schema) {
        Entity entityWorkoutSession = schema.addEntity("workoutSession");
        entityWorkoutSession.addIdProperty().primaryKey().autoincrement();
        entityWorkoutSession.addLongProperty("begin_of_workout");
        entityWorkoutSession.addLongProperty("end_of_workout");
        return entityWorkoutSession;
    }


    private static Entity addWorkoutPlanEntity(Schema schema) {
        Entity entityWorkoutPlan = schema.addEntity("workoutPlan");
        entityWorkoutPlan.addIdProperty().autoincrement().primaryKey();
        entityWorkoutPlan.addStringProperty("name");
        entityWorkoutPlan.addBooleanProperty("current");
        entityWorkoutPlan.addStringProperty("description");
        entityWorkoutPlan.addIntProperty("amount_days");
        return entityWorkoutPlan;
    }

    private static Entity addUserEntity(Schema schema) {
        Entity entityUser = schema.addEntity("user");
        entityUser.addIdProperty().primaryKey().autoincrement();
        entityUser.addStringProperty("name");
        entityUser.addFloatProperty("body_weight");
        entityUser.addFloatProperty("body_height");
        entityUser.addIntProperty("age");
        entityUser.addFloatProperty("activity_index");
        return entityUser;
    }


    private static Entity addDietPlanEntity(Schema schema) {
        Entity entityDietPlan = schema.addEntity("dietPlan");
        entityDietPlan.addIdProperty().primaryKey().autoincrement();
        entityDietPlan.addStringProperty("name");
        return entityDietPlan;
    }
}
