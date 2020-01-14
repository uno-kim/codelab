package com.unokim.codelab.todousecases.domain

import com.unokim.codelab.todousecases.data.source.TasksRepository
import com.unokim.codelab.todousecases.util.wrapEspressoIdlingResource

class DeleteTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(taskId: String) {

        wrapEspressoIdlingResource {
            return tasksRepository.deleteTask(taskId)
        }
    }

}