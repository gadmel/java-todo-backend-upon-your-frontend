import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vitejs.dev/config/
export default defineConfig({
	plugins: [react()],
	server: {
		origin: 'localhost:3000',
		proxy: {
			'/api/todo': {
				target: 'http://localhost:8080/',
			},
		},
	},
})
