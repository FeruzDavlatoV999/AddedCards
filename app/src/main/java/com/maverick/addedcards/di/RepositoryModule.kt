package com.maverick.addedcards.di

import android.content.SharedPreferences
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.maverick.addedcards.repository.CardRepository
import com.maverick.addedcards.repository.CardRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        database: FirebaseFirestore,
    ): CardRepository{
        return CardRepositoryImp(database)
    }
}