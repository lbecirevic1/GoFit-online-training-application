import axios from 'axios';

const CustomAxios = axios.create({});

CustomAxios.interceptors.request.use(
  request => {
    const token = localStorage.getItem("accessToken");
    request.headers.authorization = `Bearer ${token}`;
    return request; 
  },
  error => {
    return Promise.reject(error)
  }
);

CustomAxios.interceptors.response.use(
  response => {return response;},
  error => { 
    const originalRequest = error?.config;
    const status = error.response ? error.response.status :null;
    // if (status === 401) {
    //   //uzimamo refresh token
    //   return axios.post('ruta za refresh token', {
    //     refreshToken:localStorage.getItem("refreshToken")
    //   }).then(response => {
    //     localStorage.setItem("accessToken", response.data.accessToken);
    //     localStorage.setItem("refreshToken", response.data.refreshToken);
    //     console.log("New tokens have been received");
    //     return CustomAxios(originalRequest);
    //   }).catch(err => {
    //     localStorage.removeItem("accessToken");
    //     localStorage.removeItem("refreshToken");
    //     localStorage.removeItem('persist:main-root');
    //     window.location.replace("/login");
    //   })
    // }
  return Promise.reject(error);
  });

export default CustomAxios;