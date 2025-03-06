package com.example.hanyarunrun.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {
    @Insert
    suspend fun insert(data: DataEntity)

    @Update
    suspend fun update(data: DataEntity)

    @Query("SELECT * FROM data_table ORDER BY id DESC")
    fun getAll(): LiveData<List<DataEntity>>

    @Query("SELECT * FROM data_table WHERE id = :dataId")
    suspend fun getById(dataId: Int): DataEntity?

    @Delete
    suspend fun delete(data: DataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDataEntity)

    @Query("SELECT * FROM user_table WHERE id_user = :id LIMIT 1")
    fun getUserById(id: Int): LiveData<UserDataEntity>

    @Query("SELECT * FROM user_table WHERE id_user = :id LIMIT 1")
    suspend fun getUserByIdSync(id: Int): UserDataEntity?

    @Update
    suspend fun updateUser(user: UserDataEntity)
}


