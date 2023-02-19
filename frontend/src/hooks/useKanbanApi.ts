import { useEffect, useState } from 'react'
import axios from 'axios'
import { TaskType } from '../components/Task'
import taskService from '../services/taskService'

function useKanbanApi() {
	const kanbanColumns = [
		{ slug: 'OPEN', label: 'To Do' },
		{ slug: 'IN_PROGRESS', label: 'In Progress' },
		{ slug: 'DONE', label: 'Done' },
	]

	const [todos, setTodos] = useState<TaskType[]>([])
	const [loading, setLoading] = useState<boolean>(true)

	function fetchTodos() {
		axios
			.get('/api/todo')
			.then(response => {
				setTodos(response.data)
			})
			.catch(error => {
				console.log('Error while fetching todos: ', error)
			})
	}

	useEffect(() => {
		fetchTodos()
	}, [loading])

	function addTask(task: TaskType) {
		setLoading(true)
		axios
			.post('/api/todo', task)
			.then(response => {
				setTodos([...todos, response.data])
			})
			.finally(() => {
				setLoading(false)
			})
	}

	function advanceTask(task: TaskType) {
		setLoading(true)
		const updatedTask = taskService.advance(task)
		axios
			.put(`/api/todo/${updatedTask.id}`, updatedTask)
			.then(response => {
				setTodos(
					todos.map(task => (task.id === updatedTask.id ? response.data : task))
				)
			})
			.finally(() => {
				setLoading(false)
			})
	}

	function editTask(props: TaskType) {
		setLoading(true)
		axios
			.put(`/api/todo/${props.id}`, props)
			.then(response => {
				setTodos(todos.map(task => (task.id === props.id ? response.data : task)))
			})
			.finally(() => {
				setLoading(false)
			})
	}

	return { kanbanColumns, todos, setTodos, addTask, advanceTask, editTask, loading }
}

export default useKanbanApi
