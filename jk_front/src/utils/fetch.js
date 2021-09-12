import axios from 'axios';
import Cookies from 'js-cookie';
import env from '../../build/env';

const ajaxUrl = env === 'development'
    ? 'http://127.0.0.1:8080'
    : env === 'production'
        ? 'https://www.url.com'
        : 'https://debug.url.com';
// 创建axios实例
const service = axios.create({
    baseURL: ajaxUrl, // api的base_url
    timeout: 5000 // 请求超时时间
});

// request拦截器
service.interceptors.request.use(config => {
    // Do something before request is sent
    let access_token = Cookies.get('token');
    if (access_token) {
        var params = config.params;
        if (!params) {
            config.params =
                {
                    'access_token': access_token // 让每个请求携带参数access_token
                };
        } else {
            params.access_token = access_token;// 追加
        }
    }
    return config;
}, error => {
    // Do something with request error
    console.log(error); // for debug
    Promise.reject(error);
});

// respone拦截器
service.interceptors.response.use(
    response => response,
    error => {
        console.log('err' + error);// for debug
        // // alert(error);
        // vue.$Message.error({
        //                 message: error.message,
        //                 duration: 5 * 1000,
        //                 closable: true
        //             });
        return Promise.reject(error);
    }
);

export default service;
