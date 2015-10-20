package de.davidartmann.charowin.db.contract;

import java.util.List;

import de.davidartmann.charowin.db.model.Exercise;

/**
 * Created by David on 20.10.2015.
 */
public interface IExerciseManager {

    List<Exercise> getAll();

    Exercise get(Long id);

    Exercise create(Exercise exercise);
}
