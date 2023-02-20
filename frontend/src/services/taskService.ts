import { TaskType } from '../components/Task'

function advanceTask(task: TaskType) {
	const getNextStatus = (statusToAdvance: string) => {
		switch (statusToAdvance) {
			case 'OPEN':
				return 'IN_PROGRESS'
			case 'IN_PROGRESS':
				return 'DONE'
			case 'DONE':
				return 'OPEN'
			default: {
				throw new Error('Invalid status')
			}
		}
	}
	const result: TaskType = { ...task, status: getNextStatus(task.status) }
	return result
}

function reactivateTask(task: TaskType) {
	return { ...task, status: 'OPEN' }
}

export default {
	advance: advanceTask,
	reactivate: reactivateTask,
}
