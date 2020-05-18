# Worksheet 7: State

## Question 1 - Discussion: Simple State Diagrams

### (a)

'Saved' state and 'Unsaved' state.

Editing moves from saved to unsaved and does the editing, if in unsaved just does the editing.

Saving moves from unsaved to saved, if in saved does nothing.

### (b)

'Logged In' and 'Guest' states.

Moving between is trivial.

Doing memberOnlyAction() in guest does nothing but display an error. Doing memberOnlyAction() in logged in performs the action.

## Question 2 - State Diagrams in Detail

