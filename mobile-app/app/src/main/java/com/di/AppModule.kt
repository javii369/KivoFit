package com.KivoFit.di

import com.KivoFit.data.repository.RemoteAuthRepository
import com.KivoFit.data.repository.RemoteChatRepository
import com.KivoFit.data.repository.RemoteSesionesRepository
import com.KivoFit.data.repository.RemoteUserRepository
import com.KivoFit.domain.repository.auth.AuthRepository
import com.KivoFit.domain.repository.chat.ChatRepository
import com.KivoFit.domain.repository.schedule.SesionesRepository
import com.KivoFit.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: RemoteAuthRepository): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: RemoteUserRepository): UserRepository

    @Binds
    @Singleton
    abstract fun bindSesionesRepository(impl: RemoteSesionesRepository): SesionesRepository

    @Binds
    @Singleton
    abstract fun bindChatRepository(impl: RemoteChatRepository): ChatRepository
}
