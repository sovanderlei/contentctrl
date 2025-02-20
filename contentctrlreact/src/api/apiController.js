import axios from 'axios';
import { API_CONFIG } from './config';

const apiController = axios.create({
    baseURL: API_CONFIG.BASE_URL,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json'
    }
});

// Interceptor para adicionar token
apiController.interceptors.request.use(config => {
    const token = localStorage.getItem(API_CONFIG.TOKEN_KEY);
    if (token) {
        config.headers.Authorization = `${token}`; //Bearer
    }
    return config;
});

// Interceptor de resposta para tratamento de erros
apiController.interceptors.response.use(
    response => response,
    error => {
        if (error.response?.status === 401) {
            localStorage.removeItem(API_CONFIG.TOKEN_KEY);
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default apiController;