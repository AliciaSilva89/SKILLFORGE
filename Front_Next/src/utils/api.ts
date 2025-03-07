// utils/api.ts
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/', // Substitua com o endereço da sua API Java
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
