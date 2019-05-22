package com.androidarchitecture.data.local.user

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

import com.androidarchitecture.entity.User

/**
 * Created by dmobiledevcore on 6/19/17.
 */
@Dao
interface UserDao: UserLocalAPI {

    @Query("SELECT * FROM user WHERE first_name LIKE :arg0 LIMIT 1")
    override fun findByName(first: String): User?

    @Query("SELECT * FROM user WHERE uid LIKE :arg0 LIMIT 1")
    override  fun findById(uid: Int): User?

    @Insert(onConflict = REPLACE)
    override fun saveUser(user: User)
}
