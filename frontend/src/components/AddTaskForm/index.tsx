import React from 'react'

function AddTaskForm(props: { onSubmit: () => void }) {
	return (
		<form className="controls" onSubmit={props.onSubmit}>
			<label htmlFor="title">
				Title
				<input type="text" name="title" />
			</label>
			<label htmlFor="title">
				Description
				<input type="text" name="description" />
			</label>
			<button type="submit">Add</button>
		</form>
	)
}

export { AddTaskForm }
