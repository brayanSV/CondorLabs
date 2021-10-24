package com.user.brayan.condorlabs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.user.brayan.condorlabs.model.Events
import com.user.brayan.condorlabs.model.Teams

@Database(
    entities = [
        Teams::class,
        Events::class
    ],
    version = 2
)

abstract class CondorLabsDb: RoomDatabase() {
    abstract fun teamsDao(): TeamsDao
    abstract fun eventsDao(): EventsDao
}