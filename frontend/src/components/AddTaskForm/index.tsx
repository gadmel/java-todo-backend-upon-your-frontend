import React from 'react'

import { TaskType } from '../Task'

function AddTaskForm(props: { addTask: (task: TaskType) => void }) {
	const handleAddTask = (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault()
		const formData = new FormData(event.target as HTMLFormElement)
		const title = formData.get('title') as string
		const description = formData.get('description') as string
		props.addTask({ title, description, status: 'OPEN' })
	}

	return (
		<form className="controls" onSubmit={handleAddTask}>
			<label htmlFor="title">
				Title
				<input type="text" name="title" />
			</label>
			<label htmlFor="title">
				Description
				<input type="text" name="description" />
			</label>
			<button type="submit">Add</button>
		</form>
	)
}

export { AddTaskForm }
