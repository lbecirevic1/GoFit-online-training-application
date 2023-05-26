package com.GoFit.DietPlan.Seeder;

import com.GoFit.DietPlan.Models.DietPlan;
import com.GoFit.DietPlan.Models.PhysicalCondition;
import com.GoFit.DietPlan.Models.Recepie;
import com.GoFit.DietPlan.Models.User;
import com.GoFit.DietPlan.Services.AthleteService;
import com.GoFit.DietPlan.Services.DietPlanService;
import com.GoFit.DietPlan.Services.PhysicalConditionService;
import com.GoFit.DietPlan.Services.RecepieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder {
    @Autowired
    private DietPlanService dietPlanService;

    @Autowired
    private PhysicalConditionService physicalConditionService;

    @Autowired
    private RecepieService recepieService;

    @Autowired
    private AthleteService athleteService;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seed();
    }

    private void seed() {

        //frecepti


        String recept1 = " Whisk together egg, almond flour, and baking powder. Stir in mozzarella cheese and set batter aside."+
        "Preheat a waffle iron according to manufacturer's instructions. Spray both sides of the preheated waffle iron with cooking spray." +
                " Pour 1/2 of the batter onto the waffle iron and spread it out from the center with a spoon. " +
                "Close the waffle maker and cook until chaffle reaches your desired doneness, about 3 minutes. " +
                "Carefully lift chaffle out of the waffle iron and repeat with remaining batter. " +
                "Allow chaffles to cool for 2 to 3 minutes, and they will begin to crisp up.";

        String recept2 =
                "Heat oil in a large skillet on high heat. Add chicken cubes; cook until browned, about 2 minutes per side. " +
                        "Reduce heat to medium-high and add coconut cream and curry sauce. Cook until chicken is no longer pink in the center and the juices run clear, " +
                        "about 5 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)." +
                "Fill a large pot with lightly salted water and bring to a rolling boil; stir in vermicelli pasta and return to a boil. " +
                        "Cook pasta uncovered, stirring occasionally, until the pasta is tender yet firm to the bite, 4 to 5 minutes. Drain." +
                "Reduce skillet heat to simmer. Add the noodles and let simmer until flavors are absorbed, about 5 minutes. " +
                        "Divide chicken and noodles among individual serving bowls.";

        String recept3 = "Whisk together olive brine and buttermilk. Put the turkey breast into a resealable plastic bag and pour brine-buttermilk mixture into the bag." +
                " Add rosemary and thyme sprigs. Seal bag and refrigerate for 8 hours." +
                "Take the bag out of the fridge and allow to rest until the breast reaches room temperature." +
                "Preheat an air fryer to 350 degrees F (175 degrees C)." +
                "Cook the breast in the air fryer for 15 minutes. Flip over the breast and cook for 5 minutes until turkey breast is no " +
                "longer pink in the center and the juices run clear. An instant-read thermometer inserted into the center should read at least 165 degrees F " +
                "(74 degrees C). If internal temperature is lower, keep cooking at 5 minute intervals until the correct temperature is reached.";

        String recep4= "Preheat oven to 250 degrees F (120 degrees C). Lightly grease a baking sheet." +
                "In a large bowl, combine oats, peanuts, and wheat germ." +
                "In a separate bowl, combine honey, brown sugar, vegetable oil, water, salt, and vanilla. Stir well; then pour into the oat mixture, and stir. Spread out on a cookie sheet.\n" +
                "Bake for 1 hour, stirring every 15 minutes. Remove from oven, and cool before serving.";

        String recept5="Bring a large pot of lightly salted water to a boil. Cook chicken breasts in the boiling water until no longer pink in the center and the juices run clear," +
                " 12 to 15 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C). Rinse chicken in cold water to cool, cut into small chunks," +
                " and refrigerate to cool completely." +
                "Bring a large pot of lightly salted water to a boil. Cook shell pasta in the boiling water, stirring occasionally, until cooked through but firm to the bite, 8 minutes; drain. " +
                "Rinse pasta with cold water until cool; drain." +
                "Stir mayonnaise, yogurt, and curry powder together in a large bowl. " +
                "Add cold chicken, cold pasta, peas, radish slices, and green onion slices; stir to combine." +
                "Cover bowl with plastic wrap and refrigerate at least 3 hours before serving.";

        String recept6="Preheat the oven to 325 degrees Fahrenheit." +
                "Remove the florets from the head of cauliflower. In a large bowl combine the florets with all of the spices listed. Add the olive oil and combine well to make sure that all of the florets are covered nicely. Add more oil, if needed.\n" +
                "Pour the mixed cauliflower onto a baking sheet (better if you have parchment paper to line the pan with). Bake in the oven for 25-30 minutes, or until golden brown.";

        String recept7="Place chocolate bar and banana in a blender then add the milk. Cover and blend for up to two minutes," +
                " or until the chocolate and banana are completely blended together and the texture of the drink is smooth." +
                "Pour and serve or chill until ready to serve.";

        String recept8="Heat oil in large sautè pan." +
                "Add salt to the pan to avoid any sticking. Add spinach." +
                "Move spinach around with tongs and turn to avoid any burning. Add the garlic." +
                "Cover with lid, reduce heat and cook for 10-12 minutes. The steam inside will help to cook it thoroughly." +
                "Remove lid and turn spinach a few more times." +
                "Remove from heat and serve with fresh lemon juice squeezed on top.";

        String recept9="Place the chickpeas and the yogurt in two separate bowls, the chickpea bowl being at least twice as large as the yogurt bowl. Add the spices to the chickpeas." +
                "Clean and chop all fresh ingredients, removing any excess liquid from them." +
                "Pour the yogurt over the chickpeas and combine everything together until the spices have completely blended." +
                "Add the freshly chopped produce to the bowl of yogurt plus and chickpeas. Top with freshly chopped cilantro or parsley and combine well." +
                "Serve immediately or chill if serving later. Serve with a slice of fresh pita bread or as a side to rice dishes.";

        Recepie recepie1 = new Recepie("Breakfast", recept1, 1, "https://images.pexels.com/photos/2103949/pexels-photo-2103949.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        Recepie recepie2 = new Recepie("Lunch", recept2, 1, "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&w=1600");
        Recepie recepie3 = new Recepie("Dinner", recept3, 1, "https://images.pexels.com/photos/769289/pexels-photo-769289.jpeg?auto=compress&cs=tinysrgb&w=1600");
        Recepie recepie4=new Recepie("Breakfast",recep4,2,"https://images.pexels.com/photos/1495534/pexels-photo-1495534.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        Recepie recepie5=new Recepie("Lunch",recept5,2,"https://images.pexels.com/photos/764925/pexels-photo-764925.jpeg?auto=compress&cs=tinysrgb&w=1600");
        Recepie recepie6=new Recepie("Dinner",recept6,2,"https://images.pexels.com/photos/4963458/pexels-photo-4963458.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        Recepie recepie7=new Recepie("Breakfast",recept7,3,"https://images.pexels.com/photos/2103945/pexels-photo-2103945.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        Recepie recepie8=new Recepie("Lunch",recept8,3,"https://images.pexels.com/photos/6659557/pexels-photo-6659557.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        Recepie recepie9=new Recepie("Dinner",recept9,3,"https://images.pexels.com/photos/4871174/pexels-photo-4871174.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");


        List<Recepie> recepies=new ArrayList<>();
        recepies.add(recepie1);
        recepies.add(recepie2);
        recepies.add(recepie3);
        recepies.add(recepie4);
        recepies.add(recepie5);
        recepies.add(recepie6);
        recepies.add(recepie7);
        recepies.add(recepie8);
        recepies.add(recepie9);

        recepieService.saveRecepies(recepies);

        //sportiste

        User user1 = new User("korisnik1", "korisnik1@gmail.com");
        User user2 = new User("korisnik2", "korisnik2@gmail.com");
        User user3 = new User("korisnik3", "korisnik3@gmail.com");
        List<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        athleteService.saveUsers(users);

        //fizicke kondicije

        PhysicalCondition physicalCondition1 = new PhysicalCondition(user1, "M", "normal", 189.0, 89.0, 1.0);
        PhysicalCondition physicalCondition2 = new PhysicalCondition(user2, "F", "fat", 165.0, 85.0, 3.0);
        PhysicalCondition physicalCondition3 = new PhysicalCondition(user3, "M", "skinny", 175.0, 55.0, 1.0);

        List<PhysicalCondition> physicalConditionList=new ArrayList<>();
        physicalConditionList.add(physicalCondition1);
        physicalConditionList.add(physicalCondition2);
        physicalConditionList.add(physicalCondition3);
        physicalConditionService.savePhysicalConditions(physicalConditionList);




     DietPlan dietPlan1 = new DietPlan("breakfast", recept1, physicalCondition1, false);
        DietPlan dietPlan2 = new DietPlan("lunch", recept2, physicalCondition2, false);
        DietPlan dietPlan3 = new DietPlan("dinner", recept3, physicalCondition3, false);

        List<DietPlan> dietPlanList=new ArrayList<>();
        dietPlanList.add(dietPlan1);
        dietPlanList.add(dietPlan2);
        dietPlanList.add(dietPlan3);

        dietPlanService.saveDietPlans(dietPlanList);
    }

}
