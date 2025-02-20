import { userService } from './services/userService';
import { companyService } from './services/companyService';
import { branchService } from './services/branchService';

// Exemplo de uso:
const fetchData = async () => {
    try {
        // Login
        const user = await userService.login({
            username: 'testuser',
            password: 'password123'
        });

        // Buscar empresas
        const companies = await companyService.getAll();

        // Criar nova filial
        const newBranch = await branchService.create({
            name: 'New Branch',
            address: '123 Main St',
            company: { id: 1 }
        });

    } catch (error) {
        console.error('Error:', error.message);
    }
};