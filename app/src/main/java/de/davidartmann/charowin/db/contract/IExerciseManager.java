package de.davidartmann.charowin.db.contract;

import java.util.List;

import de.davidartmann.charowin.db.model.Exercise;

/**
 * Interface for the Exercise DbManager.
 *
 * Created by David on 20.10.2015.
 */
public interface IExerciseManager {

    List<Exercise> getAllExercises();

    Exercise getExercise(Long id);

    Long createExercise(Exercise exercise);

    Exercise updateExerciseById(Long id, Exercise exercise);

    Boolean deleteExerciseById(Long id);
}
