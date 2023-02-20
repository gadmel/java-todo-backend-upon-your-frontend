import React from 'react'
import { TaskType } from '../components/Task'
import axios from 'axios'

export default {
	get,
	post,
	put,
}

const apiUrlSlug = '/api/todo'

function get(setTodos: React.Dispatch<React.SetStateAction<TaskType[]>>) {
	return axios
		.get(apiUrlSlug)
		.then(response => {
			setTodos(response.data)
		})
		.catch(error => {
			console.log('Error while fetching todos: ', error)
		})
}

function post(
	setTodos: React.Dispatch<React.SetStateAction<TaskType[]>>,
	task: TaskType
) {
	return axios
		.post(apiUrlSlug, task)
		.then(response => {
			setTodos(prevTasks => [...prevTasks, response.data])
		})
		.catch(error => {
			console.log('Error while posting todo: ', error)
		})
}

function put(
	setTodos: React.Dispatch<React.SetStateAction<TaskType[]>>,
	updatedTask: TaskType
) {
	return axios
		.put(`${apiUrlSlug}/${updatedTask.id}`, updatedTask)
		.then(response => {
			setTodos(prevTasks =>
				prevTasks.map(task => (task.id === updatedTask.id ? response.data : task))
			)
		})
		.catch(error => {
			console.log('Error while updating todo: ', error)
		})
}
