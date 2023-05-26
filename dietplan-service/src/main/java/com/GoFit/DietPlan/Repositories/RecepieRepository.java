package com.GoFit.DietPlan.Repositories;

import com.GoFit.DietPlan.Models.DietPlan;
import com.GoFit.DietPlan.Models.Recepie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepieRepository extends JpaRepository<Recepie, Integer> {
    List<Recepie> getAllByDedicated(int broj);
}
