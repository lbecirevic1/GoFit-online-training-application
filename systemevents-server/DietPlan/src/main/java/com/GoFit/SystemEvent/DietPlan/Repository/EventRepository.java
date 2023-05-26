package com.GoFit.SystemEvent.DietPlan.Repository;

import com.GoFit.SystemEvent.DietPlan.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
