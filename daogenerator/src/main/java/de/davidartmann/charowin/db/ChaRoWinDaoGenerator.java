package de.davidartmann.charowin.db;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ChaRoWinDaoGenerator {

    private static final String PROJECT_DIR =
            System.getProperty("user.dir").replace("\\", "/").replace("daogenerator", "");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    public static void main(String[] args) {
        Schema schema = new Schema(1, "de.davidartmann.charowin.db.model");
        addTables(schema);
        try {
            new DaoGenerator().generateAll(schema, OUT_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(Schema schema) {
        addMuscleEntity(schema);
        addUserEntity(schema);
        addExerciseEntity(schema);
    }

    private static void addExerciseEntity(Schema schema) {
        Entity entityExercise = schema.addEntity("exercise");
        entityExercise.addIdProperty();
        entityExercise.addStringProperty("name");
    }

    private static void addMuscleEntity(Schema schema) {
        Entity entityMuscle = schema.addEntity("muscle");
        entityMuscle.addIdProperty();
        entityMuscle.addStringProperty("name");
    }

    private static void addUserEntity(Schema schema) {
        Entity entityUser = schema.addEntity("user");
        entityUser.addIdProperty();
        entityUser.addStringProperty("name");
        entityUser.addDoubleProperty("body_weight");
        entityUser.addDoubleProperty("body_height");
        entityUser.addLongProperty("age");
        entityUser.addDoubleProperty("activity_index");
    }
}
