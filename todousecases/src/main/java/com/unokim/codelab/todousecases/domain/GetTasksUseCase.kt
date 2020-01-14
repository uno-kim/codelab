package com.unokim.codelab.todousecases.domain

import com.unokim.codelab.todousecases.data.Result
import com.unokim.codelab.todousecases.data.Result.Success
import com.unokim.codelab.todousecases.data.Task
import com.unokim.codelab.todousecases.data.source.TasksRepository
import com.unokim.codelab.todousecases.tasks.TasksFilterType
import com.unokim.codelab.todousecases.tasks.TasksFilterType.*
import com.unokim.codelab.todousecases.util.wrapEspressoIdlingResource

class GetTasksUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(
        forceUpdate: Boolean = false,
        currentFiltering: TasksFilterType = ALL_TASKS
    ): Result<List<Task>> {

        wrapEspressoIdlingResource {

            val tasksResult = tasksRepository.getTasks(forceUpdate)

            // Filter tasks
            if (tasksResult is Success && currentFiltering != ALL_TASKS) {
                val tasks = tasksResult.data

                val tasksToShow = mutableListOf<Task>()
                // We filter the tasks based on the requestType
                for (task in tasks) {
                    when (currentFiltering) {
                        ACTIVE_TASKS -> if (task.isActive) {
                            tasksToShow.add(task)
                        }
                        COMPLETED_TASKS -> if (task.isCompleted) {
                            tasksToShow.add(task)
                        }
                        else -> NotImplementedError()
                    }
                }
                return Success(tasksToShow)
            }
            return tasksResult
        }
    }

}