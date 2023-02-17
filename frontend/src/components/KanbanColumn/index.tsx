import { Task, TaskProps } from '../Task'
import React from 'react'

export type KanbanColumnProps = {
	column: { slug: string; label: string }
	tasks: TaskProps[]
}

function KanbanColumn(Props: KanbanColumnProps) {
	return (
		<div className="kanban__column">
			<div className="kanban__column--title">{Props.column.label}</div>
			<div className="kanban__column--tasks">
				{Props.tasks.map((todo: TaskProps) => {
					if (todo.status === Props.column.slug) {
						return <Task todo={todo} />
					}
				})}
			</div>
		</div>
	)
}

export { KanbanColumn }
