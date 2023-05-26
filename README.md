# GoFit

GoFit is a web application for online training and it is based on microservice architecture. Users can choose which type of training they want based on type or target area and then they can choose between hundreds of trainings available for specified category. After choosing desired training, users can see details about its each exercise and follow training through video. After finishing training, they can rate it, and it is saved in their profile under the training history. <br/>
Users can also create a schedule for workouts where they can choose when they want to workout, and they can specify desired workout. The mail is sent to user few hours before scheduled training. <br/>
GoFit application also offers users the option of creating a diet plan. All they need to do is take a quick test about their physical activity and eating habits and their test will be automatically generated with recommended recipes for meals. 

[Check out GoFit application video:]([url](https://drive.google.com/file/d/1s0ncIntaRmsA48iQQG7pX7ZonjNWfsh7/view?usp=sharing)) https://drive.google.com/file/d/1s0ncIntaRmsA48iQQG7pX7ZonjNWfsh7/view?usp=sharing .

# How to use
To run this application you need Java, Maven, Git, PostgreSQL, JS
```
$ https://github.com/PNWT-GoFit/GoFitFinal.git
```
After cloning repository, you should run services by order specified below:
1. eureka-service
2. config-server
3. api-gateway
4. system-event-server
5. training-service
6. dietplan-service
7. schedule-service
8. user-service
9. frontend application

# Contributors:
Lejla Bečirević - lbecirevic1@etf.unsa.ba <br />
Medina Kapo - mkapo2@etf.unsa.ba <br />
Lejla Mujić - lmujic1@etf.unsa.ba <br />
