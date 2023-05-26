package com.GoFit.DietPlan.Repositories;

import com.GoFit.DietPlan.Models.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietPlanRepository extends JpaRepository <DietPlan, Integer>{

    DietPlan findById(int id);

    DietPlan findDietPlanByCondition_Id(int id);



}
