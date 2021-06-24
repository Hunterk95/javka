package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody Task task) {
        taskService.create(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Task>> read() {
        final List<Task> tasks = taskService.readAll();

        return tasks != null &&  !tasks.isEmpty()
                ? new ResponseEntity<>(tasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> read(@PathVariable(name = "id") int id) {
        final Task task = taskService.read(id);

        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Task task) {
        final boolean updated = taskService.update(task, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = taskService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}