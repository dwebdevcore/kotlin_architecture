package com.androidarchitecture.data.local.task

import android.arch.lifecycle.LiveData
import com.androidarchitecture.entity.Task

/**
 * Created by dmobiledevcore on 7/12/17.
 */
interface TaskLocalAPI {

    fun getAllTask(): LiveData<List<Task>>

    fun saveTask(task: Task)

    fun removeTask(task: Task)

}