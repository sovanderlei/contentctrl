import apiController from '../api/apiController';
import { API_ROUTES } from '../api/routes';

export const branchService = {
    getAll: async () => {
        try {
            const response = await apiController.get(API_ROUTES.BRANCH.BASE);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Failed to fetch branches');
        }
    },

    create: async (branchData) => {
        try {
            const response = await apiController.post(API_ROUTES.BRANCH.BASE, branchData);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Creation failed');
        }
    },

    getById: async (id) => {
        try {
            const response = await apiController.get(`${API_ROUTES.BRANCH.BASE}/${id}`);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Branch not found');
        }
    },

    update: async (id, branchData) => {
        try {
            const response = await apiController.put(`${API_ROUTES.BRANCH.BASE}/${id}`, branchData);
            return response.data;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Update failed');
        }
    },

    delete: async (id) => {
        try {
            await apiController.delete(`${API_ROUTES.BRANCH.BASE}/${id}`);
            return true;
        } catch (error) {
            throw new Error(error.response?.data?.message || 'Delete failed');
        }
    }
};