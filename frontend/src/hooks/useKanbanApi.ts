import { useEffect, useState } from 'react'
import { TaskType } from '../components/Task'
import taskService from '../services/taskService'
import taskApiService from '../services/taskApiService'

function useKanbanApi() {
	const kanbanColumns = [
		{ slug: 'OPEN', label: 'To Do' },
		{ slug: 'IN_PROGRESS', label: 'In Progress' },
		{ slug: 'DONE', label: 'Done' },
	]

	const [todos, setTodos] = useState<TaskType[]>([])
	const [loading, setLoading] = useState<boolean>(true)

	function fetchTodos() {
		taskApiService.get(setTodos).finally(() => {
			setLoading(false)
		})
	}

	useEffect(() => {
		fetchTodos()
	}, [loading])

	function addTask(task: TaskType) {
		setLoading(true)
		taskApiService.post(todos, setTodos, task).finally(() => {
			setLoading(false)
		})
	}

	function advanceTask(task: TaskType) {
		setLoading(true)
		const updatedTask = taskService.advance(task)
		taskApiService.put(todos, setTodos, updatedTask).finally(() => {
			setLoading(false)
		})
	}

	function editTask(task: TaskType) {
		setLoading(true)
		taskApiService.put(todos, setTodos, task).finally(() => {
			setLoading(false)
		})
	}

	return { kanbanColumns, todos, setTodos, addTask, advanceTask, editTask, loading }
}

export default useKanbanApi
