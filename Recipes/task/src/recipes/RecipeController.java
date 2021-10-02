package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    TaskRepository taskRepository;


    @PostMapping("/new")
    public ResponseEntity<String> postRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe newRecipe = taskRepository.save(recipe);
        long id = newRecipe.getId();
        return ResponseEntity.ok("{\"id\":" + id + "\n }");

    }

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipe(@PathVariable long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe not found for id = " + id);
        }
        return taskRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseStatusException delRecipe(@PathVariable long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            taskRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}