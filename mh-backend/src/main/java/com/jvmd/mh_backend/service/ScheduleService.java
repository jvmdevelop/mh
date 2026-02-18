package com.jvmd.mh_backend.service;

import com.jvmd.mh_backend.model.Schedule;
import com.jvmd.mh_backend.repo.ScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScheduleService {
    
    private final ScheduleRepo scheduleRepo;
    
    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }
    
    public Schedule getScheduleById(UUID id) {
        return scheduleRepo.findById(id).orElse(null);
    }
    
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }
    
    public Schedule updateSchedule(UUID id, Schedule schedule) {
        Schedule existingSchedule = scheduleRepo.findById(id).orElse(null);
        if (existingSchedule != null) {
            existingSchedule.setTasks(schedule.getTasks());
            if (schedule.getMessage() != null) {
                existingSchedule.setMessage(schedule.getMessage());
            }
            return scheduleRepo.save(existingSchedule);
        }
        return null;
    }
    
    public boolean deleteSchedule(UUID id) {
        if (scheduleRepo.existsById(id)) {
            scheduleRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
