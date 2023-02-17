import React from 'react'
import { createRoot } from 'react-dom/client'
import './index.sass'

import App from './components/App'

const container: Element = document.getElementById('root')! as HTMLElement
const root = createRoot(container)

root.render(
	<React.StrictMode>
		<App />
	</React.StrictMode>
)
