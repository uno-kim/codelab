package com.unokim.codelab.todousecases.domain

import com.unokim.codelab.todousecases.data.source.TasksRepository
import com.unokim.codelab.todousecases.util.wrapEspressoIdlingResource

class ClearCompletedTasksUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke() {

        wrapEspressoIdlingResource {
            tasksRepository.clearCompletedTasks()
        }
    }
}