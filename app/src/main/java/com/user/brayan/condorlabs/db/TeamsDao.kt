package com.user.brayan.condorlabs.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.brayan.condorlabs.model.Teams

@Dao
abstract class TeamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: List<Teams>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertById(item: Teams)

    @Query("select * from teams where idLeague = :idLeague ")
    abstract fun load(idLeague: Long): LiveData<List<Teams>>

    @Query("select * from teams where idTeam = :idTeam ")
    abstract fun loadById(idTeam: Long): LiveData<Teams>
}