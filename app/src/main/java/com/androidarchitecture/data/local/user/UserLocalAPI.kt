package com.androidarchitecture.data.local.user

import android.arch.lifecycle.LiveData
import com.androidarchitecture.entity.User

/**
 * Created by dmobiledevcore on 5/18/17.
 */
interface UserLocalAPI {

    fun findByName(first: String): User?

    fun findById(uid: Int): User?

    fun saveUser(user: User)
}