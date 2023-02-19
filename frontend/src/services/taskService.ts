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
		}
	}
	return { ...task, status: getNextStatus(task.status) }
}

export default {
	advance: advanceTask,
}
