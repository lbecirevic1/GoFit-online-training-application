package com.GoFit.Training.DBConfig;

import com.GoFit.Training.Models.Exercise;
import com.GoFit.Training.Models.Training;
import com.GoFit.Training.Repositories.ExerciseRepository;
import com.GoFit.Training.Repositories.TrainingRepository;
import com.GoFit.Training.Requests.TrainingRequest;
import com.GoFit.Training.Services.ExerciseService;
import com.GoFit.Training.Services.TrainingService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class TrainingConfig {

    @Autowired
    TrainingService trainingService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Bean
    CommandLineRunner userCommandLineRunner() {
        return args -> {
            Exercise jumping_jacks = new Exercise("Jumping jacks", 30.0, "Keeping the knees bent, open the arms and legs out to the sides. Arms come above the head and legs wider than shoulders.", "https://images.pexels.com/photos/4853085/pexels-photo-4853085.jpeg?cs=srgb&dl=pexels-ketut-subiyanto-4853085.jpg&fm=jpg");
            Exercise high_knees = new Exercise("High knees", 30.0, "Stand with your feet hip-width apart. Lift up your left knee to your chest.Switch to lift your right knee to your chest. Continue the movement, alternating legs.", "https://images.pexels.com/photos/6339342/pexels-photo-6339342.jpeg?cs=srgb&dl=pexels-pavel-danilyuk-6339342.jpg&fm=jpg");
            Exercise squat_side = new Exercise("Squat + side jump", 30.0, "Place your feet shoulder-width apart. Bend at the knees while driving your hips back. Keep your chest up and your core tight. Remain in a squat stance as you forcefully push off the ground, jumping to the one side.", "https://images.pexels.com/photos/8846119/pexels-photo-8846119.jpeg?cs=srgb&dl=pexels-mart-production-8846119.jpg&fm=jpg");
            Exercise mountain_climbers = new Exercise("Mountain climbers", 30.0, "Get into a plank position.Pull your right knee into your chest as far as you can then switch legs.", "https://images.pexels.com/photos/8402214/pexels-photo-8402214.jpeg?cs=srgb&dl=pexels-rodnae-productions-8402214.jpg&fm=jpg");
            Exercise high_plank = new Exercise("High plank", 30.0, "Get into high plank position and hold.", "https://images.pexels.com/photos/6453942/pexels-photo-6453942.jpeg?cs=srgb&dl=pexels-marta-wave-6453942.jpg&fm=jpg");
            Exercise plank_jumping = new Exercise("Plank + jumping jacks", 30.0, "From plank position do jumping jack and repeat.", "https://images.pexels.com/photos/6999110/pexels-photo-6999110.jpeg?cs=srgb&dl=pexels-monstera-6999110.jpg&fm=jpg");
            Exercise squat_hold = new Exercise("Squat hold", 30.0, "Go into the squat position and hold.", "https://images.pexels.com/photos/8173430/pexels-photo-8173430.jpeg?cs=srgb&dl=pexels-kampus-production-8173430.jpg&fm=jpg");
            Exercise ball_push_ups = new Exercise("Ball push ups", 30.0, "Start in the plank position with your feet on the ball.", "https://images.pexels.com/photos/8033013/pexels-photo-8033013.jpeg?cs=srgb&dl=pexels-mart-production-8033013.jpg&fm=jpg");
            Exercise dolphin_hold = new Exercise("Dolphin hold", 30.0, "Put your forearms on the floor with your arms and feet shoulder-width apart and your hips raised so that your body forms an inverted V.", "https://images.pexels.com/photos/3822118/pexels-photo-3822118.jpeg?cs=srgb&dl=pexels-elina-fairytale-3822118.jpg&fm=jpg");
            Exercise crunches = new Exercise("Crunches", 40.0, "Place your hands on the floor by your side. Inhale, contract your abs toward your spine. Exhale and lift your feet off the floor and raise your knees upward and inward toward your chest.", "https://images.pexels.com/photos/4971061/pexels-photo-4971061.jpeg?cs=srgb&dl=pexels-gustavo-fring-4971061.jpg&fm=jpg");
            Exercise shoulder_stretch = new Exercise("Shoulder stretch", 50.0, "Relaxing your shoulder blades back and down, reach one arm across your body and gently use your other arm to deepen the stretch. ", "https://images.pexels.com/photos/634030/pexels-photo-634030.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=11");
            Exercise glute_bridge = new Exercise("Glute bridge", 50.0, "Lie on your back with your knees bent and your feet on the floor about hip-width apart. Gently tighten your stomach muscles and push your hips up toward the ceiling.", "https://images.pexels.com/photos/8846204/pexels-photo-8846204.jpeg?cs=srgb&dl=pexels-mart-production-8846204.jpg&fm=jpg");
            Exercise legs_stretch = new Exercise("Legs stretch", 40.0, "From standing position touch your toes and hold.", "https://images.pexels.com/photos/6453398/pexels-photo-6453398.jpeg?cs=srgb&dl=pexels-marta-wave-6453398.jpg&fm=jpg");
            Exercise legs_stretch_modified = new Exercise("Legs stretch modifies", 50.0, "Put your legs out to the sides (wider than shoulders) and touch floor with yout fingers and hold", "https://images.pexels.com/photos/6454068/pexels-photo-6454068.jpeg?cs=srgb&dl=pexels-marta-wave-6454068.jpg&fm=jpg");
            Exercise legs_stretch_modified_2 = new Exercise("Legs stretch toe touches", 60.0, "Put your legs to the sides and touch your left foot with right arm then change. Repeat.", "https://images.pexels.com/photos/6454064/pexels-photo-6454064.jpeg?cs=srgb&dl=pexels-marta-wave-6454064.jpg&fm=jpg");
            Exercise cobra_stretch = new Exercise("Cobra stretch", 30.0, "Lie on your stomach and place your hands flat beneath your shoulders. Tuck your elbows in by your sides and gently raise your head and chest.", "https://images.pexels.com/photos/6454072/pexels-photo-6454072.jpeg?cs=srgb&dl=pexels-marta-wave-6454072.jpg&fm=jpg");

            List<Exercise> exerciseList = new ArrayList<>();
            exerciseList.add(jumping_jacks);
            exerciseList.add(high_knees);
            exerciseList.add(squat_side);
            exerciseList.add(mountain_climbers);
            exerciseList.add(high_plank);
            exerciseList.add(plank_jumping);
            exerciseList.add(squat_hold);
            exerciseList.add(ball_push_ups);
            exerciseList.add(dolphin_hold);
            exerciseList.add(crunches);
            exerciseList.add(shoulder_stretch);
            exerciseList.add(glute_bridge);
            exerciseList.add(legs_stretch);
            exerciseList.add(legs_stretch_modified);
            exerciseList.add(legs_stretch_modified_2);
            exerciseList.add(cobra_stretch);
            exerciseService.saveExercises(exerciseList);


            List<Integer> list1 = new ArrayList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            list1.add(4);
            list1.add(5);
            list1.add(6);

            List<Integer> list2 = new ArrayList<>();
            list2.add(5);
            list2.add(6);
            list2.add(7);
            list2.add(8);
            list2.add(9);
            list2.add(10);

            TrainingRequest HIIT_calories_burn = new TrainingRequest(10.0, "HIIT Calories burn", "HIIT", "Full body", "https://www.youtube.com/embed/zr08J6wB53Y", "https://images.pexels.com/photos/2294403/pexels-photo-2294403.jpeg?cs=srgb&dl=pexels-li-sun-2294403.jpg&fm=jpg", "Ready for a KILLER routine? on a positive note: it's only 10 minutes & the music is amazing!", list1);
            trainingService.addTraining(HIIT_calories_burn);

            List<Integer> list3 = new ArrayList<>();
            list3.add(11);
            list3.add(12);
            list3.add(13);
            list3.add(14);
            list3.add(15);
            list3.add(16);

            TrainingRequest flexibility1 = new TrainingRequest(30.0, "Everyday stretch", "Flexibility", "Full body", "https://www.youtube.com/embed/AnYl6Nk9GOA", "https://images.pexels.com/photos/3823076/pexels-photo-3823076.jpeg?cs=srgb&dl=pexels-elina-fairytale-3823076.jpg&fm=jpg", "No Equipment necessary, no breaks and not much space needed :) If you need a break tho - take it! Just don't quit!", list3);
            trainingService.addTraining(flexibility1);

            TrainingRequest ab_workout = new TrainingRequest(20.0, "Ab workout/No equipment", "Strength", "Abs", "https://www.youtube.com/embed/AnYl6Nk9GOA", "https://images.pexels.com/photos/6453408/pexels-photo-6453408.jpeg?cs=srgb&dl=pexels-marta-wave-6453408.jpg&fm=jpg", "No Equipment necessary, no breaks and not much space needed :) If you need a break tho - take it! Just don't quit!", list2);
            trainingService.addTraining(ab_workout);


            List<Integer> list4 = new ArrayList<>();
            list4.add(9);
            list4.add(14);
            list4.add(15);
            list4.add(16);
            list4.add(11);

            TrainingRequest flexibility2 = new TrainingRequest(30.0, "Daily stretch", "Flexibility", "Full body", "https://www.youtube.com/embed/_IoYLhrTBqY", "https://images.pexels.com/photos/6453398/pexels-photo-6453398.jpeg?cs=srgb&dl=pexels-marta-wave-6453398.jpg&fm=jpg", "A full body routine for tight muscles, flexibility & mobility.", list4);
            trainingService.addTraining(flexibility2);

            TrainingRequest flexibility3 = new TrainingRequest(10.0, "10 minutes yoga strong", "Flexibility", "Full body", "https://www.youtube.com/embed/NCfOoyhTwQw", "https://images.pexels.com/photos/4056723/pexels-photo-4056723.jpeg?cs=srgb&dl=pexels-cliff-booth-4056723.jpg&fm=jpg", "Stretch, slow down & tone your body the relaxed kind of way for mind, body and soul!", list4);
            trainingService.addTraining(flexibility3);
        };
    }

}
