import { Task, TaskType } from '../Task'
import React, { Dispatch, SetStateAction } from 'react'

export type KanbanColumnProps = {
	column: { slug: string; label: string }
	tasks: TaskType[]
	advanceTask: (task: TaskType) => Dispatch<SetStateAction<TaskType>> | void
}

function KanbanColumn(props: KanbanColumnProps) {
	return (
		<div className="kanban__column">
			<div className="kanban__column--title">{props.column.label}</div>
			<div className="kanban__column--tasks">
				{props.tasks.map((todo: TaskType) => {
					if (todo.status === props.column.slug) {
						return (
							<Task key={todo.id} todo={todo} advanceTask={props.advanceTask} />
						)
					}
				})}
			</div>
		</div>
	)
}

export { KanbanColumn }
