package com.unokim.codelab.todousecases.domain

import com.unokim.codelab.todousecases.data.Task
import com.unokim.codelab.todousecases.data.source.TasksRepository
import com.unokim.codelab.todousecases.util.wrapEspressoIdlingResource

class ActivateTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(task: Task) {

        wrapEspressoIdlingResource {
            tasksRepository.activateTask(task)
        }
    }
}