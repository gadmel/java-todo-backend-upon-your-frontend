import React from 'react'
import { KanbanColumn } from '../KanbanColumn'
import { AddTaskForm } from '../AddTaskForm'

import useKanbanApi from '../../hooks/useKanbanApi'

function App() {
	const { todos, kanbanColumns, addTask, advanceTask, loading } = useKanbanApi()

	return (
		<div id="app">
			<header className="header">
				<h1>Kanban Board</h1>
			</header>

			<main className="kanban">
				{kanbanColumns?.map(column => {
					return KanbanColumn({ column, tasks: todos, advanceTask })
				})}
			</main>

			<footer className="footer">
				<AddTaskForm addTask={addTask} />
			</footer>
		</div>
	)
}

export default App
