package com.unokim.codelab.todousecases.domain

import com.unokim.codelab.todousecases.data.Result
import com.unokim.codelab.todousecases.data.Task
import com.unokim.codelab.todousecases.data.source.TasksRepository
import com.unokim.codelab.todousecases.util.wrapEspressoIdlingResource

class GetTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(taskId: String, forceUpdate: Boolean = false): Result<Task> {

        wrapEspressoIdlingResource {
            return tasksRepository.getTask(taskId, forceUpdate)
        }
    }

}