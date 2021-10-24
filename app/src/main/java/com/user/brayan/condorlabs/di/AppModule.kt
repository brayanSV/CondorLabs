package com.user.brayan.condorlabs.di

import android.app.Application
import androidx.room.Room
import com.user.brayan.condorlabs.api.ApplicationApi
import com.user.brayan.condorlabs.caseuses.CustomGsonConverterFactory
import com.user.brayan.condorlabs.db.CondorLabsDb
import com.user.brayan.condorlabs.db.EventsDao
import com.user.brayan.condorlabs.db.TeamsDao
import com.user.brayan.condorlabs.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideApplicationApi(): ApplicationApi {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(CustomGsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApplicationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): CondorLabsDb {
        return Room.databaseBuilder(app, CondorLabsDb::class.java, "CondorLabs.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTeamsDao(db: CondorLabsDb): TeamsDao {
        return db.teamsDao()
    }

    @Singleton
    @Provides
    fun provideEventsDao(db: CondorLabsDb): EventsDao {
        return db.eventsDao()
    }
}