import React from 'react'
import { TaskType } from '../components/Task'
import axios from 'axios/index'

export default {
	get,
	post,
	put,
}

const apiUrlSlug = '/api/todo'

type getType = {
	setTodos: React.Dispatch<React.SetStateAction<TaskType[]>>
}
function get({ setTodos }: getType) {
	return axios
		.get(apiUrlSlug)
		.then(response => {
			setTodos(response.data)
		})
		.catch(error => {
			console.log('Error while fetching todos: ', error)
		})
}

type postType = {
	todos: TaskType[]
	setTodos: React.Dispatch<React.SetStateAction<TaskType[]>>
	task: TaskType
}
function post({ todos, setTodos, task }: postType) {
	return axios
		.post(apiUrlSlug, task)
		.then(response => {
			setTodos([...todos, response.data])
		})
		.catch(error => {
			console.log('Error while posting todo: ', error)
		})
}

type putType = {
	todos: TaskType[]
	setTodos: React.Dispatch<React.SetStateAction<TaskType[]>>
	updatedTask: TaskType
}
function put({ todos, setTodos, updatedTask }: putType) {
	return axios
		.put(`${apiUrlSlug}/${updatedTask.id}`, updatedTask)
		.then(response => {
			setTodos(todos.map(task => (task.id === updatedTask.id ? response.data : task)))
		})
		.catch(error => {
			console.log('Error while updating todo: ', error)
		})
}
