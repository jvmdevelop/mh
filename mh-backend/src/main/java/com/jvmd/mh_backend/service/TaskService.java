package com.jvmd.mh_backend.service;

import com.jvmd.mh_backend.model.Task;
import com.jvmd.mh_backend.repo.TaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    
    private final TaskRepo taskRepo;
    
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }
    
    public Task getTaskById(UUID id) {
        return taskRepo.findById(id).orElse(null);
    }
    
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }
    
    public Task updateTask(UUID id, Task task) {
        Task existingTask = taskRepo.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setName(task.getName());
            existingTask.setContext(task.getContext());
            if (task.getSchedule() != null) {
                existingTask.setSchedule(task.getSchedule());
            }
            return taskRepo.save(existingTask);
        }
        return null;
    }
    
    public boolean deleteTask(UUID id) {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Task> getTasksByScheduleId(UUID scheduleId) {
        return taskRepo.findByScheduleId(scheduleId);
    }
}
