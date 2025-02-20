import apiController from '../api/apiController';
import { API_ROUTES } from '../api/routes';

export const companyService = {
    getAll: async () => {
        try {
            console.warn(" bloco: 0010 ");
            const response = await apiController.get(API_ROUTES.COMPANY.BASE);
            console.warn(" bloco: 0020 ");
            console.warn(" response ", response);

            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Failed to fetch companies');
        }
    },

    create: async (companyData) => {
        try {
            const response = await apiController.post(API_ROUTES.COMPANY.BASE, companyData);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Creation failed');
        }
    },

    getById: async (id) => {
        try {
            const response = await apiController.get(`${API_ROUTES.COMPANY.BASE}/${id}`);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Company not found');
        }
    },

    update: async (id, companyData) => {
        try {
            const response = await apiController.put(`${API_ROUTES.COMPANY.BASE}/${id}`, companyData);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Update failed');
        }
    },

    delete: async (id) => {
        try {
            await apiController.delete(`${API_ROUTES.COMPANY.BASE}/${id}`);
            return true;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Delete failed');
        }
    }
};