import axios from 'axios'

const testing = (gender,bodyType,height,weight,bmi,physicalActivity) => axios.post("http://localhost:8080/dietplan/physicalCondition", { gender,bodyType,height,weight,bmi,physicalActivity });


const TestService = {
    testing
};

export default TestService;