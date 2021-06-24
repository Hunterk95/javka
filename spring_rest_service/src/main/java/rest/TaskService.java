package rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> readAll() {
        return taskRepository.findAll();
    }

    public void create(Task task) {
        taskRepository.save(task);
    }

    public boolean update(Task task, int id) {
        if (task.getTaskid() == id){
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    public Task read(int id){
        return taskRepository.findById(id).get();
    }

    public boolean delete(int id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

