package com.user.brayan.condorlabs.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.brayan.condorlabs.model.Events

@Dao
abstract class EventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: List<Events>)

    @Query("select * from events where idTeam = :idTeam")
    abstract fun load(idTeam: Long): LiveData<List<Events>>
}