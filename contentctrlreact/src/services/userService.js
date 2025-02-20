import apiController from '../api/apiController';
import { API_ROUTES } from '../api/routes';
import { API_CONFIG } from '../api/config'

export const userService = {
    login: async (credentials) => {
        try {
            console.log(" credentials = ", credentials)
            const response = await apiController.post(API_ROUTES.USER.LOGIN, credentials);
            console.log(" response = ", response);
            console.log(" bloco: 00010 ");
            console.log(" response.data.data = ", response.data);
            localStorage.setItem(API_CONFIG.TOKEN_KEY, response.data);
            console.log(" bloco: 00020 ");
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Login failed');
        }
    },

    register: async (userData) => {
        try {
            console.log("API_ROUTES.USER.REGISTER", API_ROUTES.USER.REGISTER);
            const response = await apiController.post(API_ROUTES.USER.REGISTER, userData);
            return response.data;
        } catch (error) {
            console.error("Erro completo ao registrar usuário:", error);

            if (error.response) {
                console.error("Resposta de erro da API:", error.response);
                console.error("Mensagem de erro:", error.response?.data?.message);
            }
            throw new Error(error.response?.data?.message || 'Registration failed');
        }
    },


    getAll: async () => {
        try {
            const response = await apiController.get(API_ROUTES.USER.BASE);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Failed to fetch users');
        }
    },

    getById: async (id) => {
        try {
            const response = await apiController.get(`${API_ROUTES.USER.BASE}/${id}`);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'User not found');
        }
    },

    update: async (id, userData) => {
        try {
            const response = await apiController.put(`${API_ROUTES.USER.BASE}/${id}`, userData);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Update failed');
        }
    },

    delete: async (id) => {
        try {
            await apiController.delete(`${API_ROUTES.USER.BASE}/${id}`);
            return true;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Delete failed');
        }
    }
};