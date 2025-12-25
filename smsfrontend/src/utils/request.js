import axios from "axios";
import { ElMessage } from "element-plus";
import router from "../router";

//创建axios实例对象
const request = axios.create({
    baseURL: "/api",
    timeout: 600000,
});

// 请求拦截器
request.interceptors.request.use(
    (config) => {
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// 响应拦截器
request.interceptors.response.use(
    (response) => {
        const res = response.data;
        return res;
    },
    (error) => {
        ElMessage.error("网络请求失败，请稍后重试");
        return Promise.reject(error);
    }
);

export default request;
