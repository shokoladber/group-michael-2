import axios from 'axios';


const instance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
    },
});


function getAuthToken() {
    return localStorage.getItem("auth_token");
}


instance.interceptors.request.use(
    (config) => {
        const token = getAuthToken();
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);


instance.interceptors.response.use(
    (response) => {

        return response;
    },
    (error) => {

        if (error.response && error.response.status === 401) {

            localStorage.removeItem("auth_token");

            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default instance;
