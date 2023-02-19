import React, { Dispatch, SetStateAction } from 'react'

export type TaskType = {
	id?: string
	title: string
	description: string
	status: 'OPEN' | 'IN_PROGRESS' | 'DONE'
}

type TaskProps = {
	todo: TaskType
	advanceTask: (task: TaskType) => Dispatch<SetStateAction<TaskType>> | void
}

function Task(props: TaskProps) {
	const handleAdvance = (event: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
		event.preventDefault()
		props.advanceTask(props.todo)
	}

	return (
		<div className="task">
			<div className="task__title">{props.todo.title}</div>
			<div className="task__description">{props.todo.description}</div>
			<div className="task__button" onClick={event => handleAdvance(event)}>
				Advance
			</div>
		</div>
	)
}

export { Task }
