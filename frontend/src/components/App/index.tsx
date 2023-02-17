import React, { useState } from 'react'
import { KanbanColumn } from '../KanbanColumn'
import { TaskProps } from '../Task'
import { AddTaskForm } from '../AddTaskForm'

function App() {
	let kanbanColumns = [
		{ slug: 'OPEN', label: 'To Do' },
		{ slug: 'IN_PROGRESS', label: 'In Progress' },
		{ slug: 'DONE', label: 'Done' },
	]

	const [todos, setTodos] = useState<TaskProps[]>([
		{ id: '1', title: 'test', description: 'test', status: 'OPEN' },
	])

	return (
		<div id="app">
			<header className="header">
				<h1>Kanban Board</h1>
			</header>

			<main className="kanban">
				{kanbanColumns.map(column => {
					return KanbanColumn({ column, tasks: todos })
				})}
			</main>

			<footer className="footer">
				<AddTaskForm onSubmit={() => {}} />
			</footer>
		</div>
	)
}

export default App
