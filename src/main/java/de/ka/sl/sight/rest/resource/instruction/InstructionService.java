package de.ka.sl.sight.rest.resource.instruction;

import de.ka.sl.sight.persistence.instruction.InstructionDAO;
import de.ka.sl.sight.persistence.instruction.InstructionEntity;
import de.ka.sl.sight.persistence.recipe.RecipeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Sebastian Luther (@url(https://github.com/luthesebas))
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InstructionService {

    private final InstructionDAO instructionDAO;
    private final InstructionMapper instructionMapper;

    //--------------------------------------
    // Methods
    //--------------------------------------

    public boolean exists(long instructionId) {
        return instructionDAO.existsById(instructionId);
    }

    //--------------------------------------
    // CRUD
    //--------------------------------------

    private InstructionEntity create(InstructionEntity entity) {
        return instructionDAO.save(entity);
    }

    public InstructionEntity create(InstructionEntity data, RecipeEntity recipeEntity) {
        InstructionEntity instruction = create(data);
        instruction.setRecipe(recipeEntity);
        return create(instruction);
    }

    public InstructionEntity update(long instructionId, InstructionEntity data, RecipeEntity recipe) {
        return read(instructionId, recipe.getId())
                .map(instruction -> {
                    instruction.updateFrom(data);
                    return create(instruction);
                })
                .orElseGet(() -> {
                    //data.setId(id);
                    data.setRecipe(recipe);
                    return create(data);
                });
    }

    public List<InstructionEntity> read(long recipeId) {
        return instructionDAO.findAllByRecipeId(recipeId);
    }

    public Optional<InstructionEntity> read(long instructionId, long recipeId) {
        return instructionDAO.findByIdAndRecipeId(instructionId, recipeId);
    }

    public void delete(long instructionId, long recipeId) {
        if (exists(instructionId)) {
            instructionDAO.deleteById(instructionId);
        }
    }

}
