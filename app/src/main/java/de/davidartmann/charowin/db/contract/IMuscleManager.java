package de.davidartmann.charowin.db.contract;

import java.util.List;

import de.davidartmann.charowin.db.model.Exercise;
import de.davidartmann.charowin.db.model.Muscle;

/**
 * Interface for the Muscle DbManager.
 *
 * Created by David on 20.10.2015.
 */
public interface IMuscleManager {

    List<Muscle> getAllMuscles();

    Muscle getMuscle(Long id);

    Long createMuscle(Muscle muscle);

    Boolean updateMuscleById(Long id, Muscle muscle);

    Boolean deleteMuscleById(Long id);
}
