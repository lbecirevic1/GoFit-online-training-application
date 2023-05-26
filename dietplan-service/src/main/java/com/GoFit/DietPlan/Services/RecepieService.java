package com.GoFit.DietPlan.Services;

import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Models.Recepie;
import com.GoFit.DietPlan.Repositories.RecepieRepository;
import com.GoFit.DietPlan.Requests.addNewRecepieRequest;
import com.GoFit.DietPlan.Requests.deleteRecepieRequest;
import com.GoFit.DietPlan.Requests.editRecepieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecepieService {

    @Autowired
    private RecepieRepository recepieRepository;

    public String addNewRecepie(addNewRecepieRequest addNewRecepie){
        Recepie recepie=new Recepie();
        recepie.setRecepieText(addNewRecepie.getRecepieText());
        recepie.setDedicated(addNewRecepie.getDedicated());
        recepie.setSubject(addNewRecepie.getSubject());
        if(recepie.getSubject().equals("Breakfast")){
            recepie.setImagePath("https://images.pexels.com/photos/2103949/pexels-photo-2103949.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        }
        else if(recepie.getSubject().equals("Lunch")){
            recepie.setImagePath("https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&w=1600");
        }
        else if(recepie.getSubject().equals("Dinner")){
            recepie.setImagePath("https://images.pexels.com/photos/769289/pexels-photo-769289.jpeg?auto=compress&cs=tinysrgb&w=1600");
        }
        else if(recepie.getSubject().equals("Smoothie")){
            recepie.setImagePath("https://images.pexels.com/photos/775030/pexels-photo-775030.jpeg?auto=compress&cs=tinysrgb&w=1600");
        }

        recepieRepository.save(recepie);
        return "Added new recepie";
    }

    public List<Recepie> getAllRecepies(){
        List<Recepie> vracam=recepieRepository.findAll();
        return vracam;
    }

    public String deleteRecepie(int id){
        Recepie recepie=recepieRepository.findById(id).orElse(null);
        recepieRepository.delete(recepie);
        return "deleted";
    }

    public String editRecepie(int id,editRecepieRequest editRecepie){
        Recepie recepie=recepieRepository.getById(id);
        recepie.setRecepieText(editRecepie.getRecepieText());
        recepieRepository.save(recepie);
        return "edited";
    }

    public List<Recepie> saveRecepies(List<Recepie> recepti){
        return recepieRepository.saveAll(recepti);
    }
}
