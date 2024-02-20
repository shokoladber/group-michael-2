import axios from "axios";

axios.defaults.baseURL = process.env.REACT_APP_BACKEND_URL;
axios.defaults.headers.post["Content-Type"] = "application/json";

export const request = (method, url, data) => {
    return axios({
        method: method,
        url: url,
        data:data,
    });
}