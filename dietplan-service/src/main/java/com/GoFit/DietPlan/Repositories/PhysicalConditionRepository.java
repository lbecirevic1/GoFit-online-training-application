package com.GoFit.DietPlan.Repositories;

import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalConditionRepository extends JpaRepository <PhysicalCondition, Integer>{

   PhysicalCondition findPhysicalConditionByUser_Id(int id);
   PhysicalCondition findPhysicalConditionByUser(User user);




}


