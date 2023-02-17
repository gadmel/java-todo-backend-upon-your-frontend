import React from 'react'

export type TaskProps = {
	id: string
	title: string
	description: string
	status: 'OPEN' | 'IN_PROGRESS' | 'DONE'
}

function Task(props: { todo: TaskProps }) {
	return (
		<div className="task">
			<div className="task__title">{props.todo.title}</div>
			<div className="task__description">{props.todo.description}</div>
			<div className="task__button">Advance</div>
		</div>
	)
}

export { Task }
