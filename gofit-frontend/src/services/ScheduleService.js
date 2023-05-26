import jwtDecode from "jwt-decode";
import CustomAxios from "../components/CustomAxios/CustomAxios";

const API_PATH = "http://localhost:8088";

const ADD_WORKOUT_PATH = "/schedule/workout";

class ScheduleService {
  getWorkouts = async () => {
    return await CustomAxios.get(
      `http://localhost:8088/schedule/workouts`).then(res=>res.data)
  }

  createSchedule = async () => {
    const token = localStorage.getItem("accessToken");
    const userId = jwtDecode(token).id;
    const email = jwtDecode(token).sub;

    return await CustomAxios.post(
      `http://localhost:8088/schedule/createSchedule`,
      {
        email: email,
        userId: userId,
        workouts: [],
      }
    ).then((res) => res);
  };

  getScheduleByUserId = async () => {
    const token = localStorage.getItem("accessToken");
    const userId = jwtDecode(token).id;
    //const token = localStorage.getItem("accessToken");
    console.log("token", jwtDecode(token));
    return await CustomAxios.get(
      `http://localhost:8088/schedule/byUser/${userId}`
    ).then((res) => {
      console.log("resdkfmskfsmkf", res);
      return res.data;
    });
  };

  addNewWorkout = async (workoutData) => {
    console.log('workoutData',workoutData)
    const token = localStorage.getItem("accessToken");
    const userId = jwtDecode(token).id;
    const email = jwtDecode(token).sub
    return await CustomAxios.get(
      `http://localhost:8088/schedule/byUser/${email}`
    ).then(async (res) => {
      if (res?.status === 200) {
        const reqData = {
          scheduleId: res.data.id,
          title: workoutData.title,
          start: workoutData.start.toString(),
          end: workoutData.end.toString(),
          note: workoutData.note,
          trainings: workoutData.trainings.join(","),
        };

        return await CustomAxios.post(
          `${API_PATH}${ADD_WORKOUT_PATH}`,
          reqData
        ).then((response) => {
          console.log("response", response);
          return response;
        });
      }
    });
   
  };

  deleteWorkout  = async id => {
    return await CustomAxios.delete(`http://localhost:8088/schedule/workout/${id}`).then(res=>res.data)
  }

  editWorkout  = async (id,data) => {
    return await CustomAxios.put(`http://localhost:8088/schedule/workout/${id}`,data).then(res=>res.data)
  }

  getTrainings = async () => {
    return await CustomAxios.get("http://localhost:8088/training/trainings").then(res=>res.data)
  }
}
export default new ScheduleService();
