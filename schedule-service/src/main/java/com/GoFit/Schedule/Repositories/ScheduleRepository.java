package com.GoFit.Schedule.Repositories;

import com.GoFit.Schedule.Models.Schedule;
import com.GoFit.Schedule.Request.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public Schedule findById(int id);
}
