package net.ymka.mvidemo.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import net.ymka.mvidemo.data.LocalDataStore
import net.ymka.mvidemo.data.RemoteDataStore
import net.ymka.mvidemo.data.entity.UserEntity
import org.junit.Test

class DefaultUsersRepositoryTest {

    @Test
    fun testRequestWithEmptyLocalStorage() {
        val remoteDataStore = object : RemoteDataStore {
            override fun requestUsers(): Single<List<UserEntity>> {
                return Single.just(listOf(UserEntity("first"), UserEntity("second")))
            }
        }

        val localDataStore = object : LocalDataStore {
            override fun getAllUsers(): Single<List<UserEntity>> = Single.just(emptyList())

            override fun saveUsers(users: List<UserEntity>): Completable = Completable.complete()
        }

        val repository: UsersRepository = DefaultUsersRepository(remoteDataStore, localDataStore)
        val observer = TestObserver<List<UserEntity>>()

        repository.getUsersList().subscribe(observer)

        observer.assertNoErrors()
        observer.assertValueCount(1)
        observer.assertValue(listOf(UserEntity("first"), UserEntity("second")))
    }


    @Test
    fun testRequest() {
        val remoteDataStore = object : RemoteDataStore {
            override fun requestUsers(): Single<List<UserEntity>> {
                return Single.just(listOf(UserEntity("third"), UserEntity("second")))
            }
        }

        val localDataStore = object : LocalDataStore {
            override fun getAllUsers(): Single<List<UserEntity>> {
                return Single.just(listOf(UserEntity("first"), UserEntity("second")))
            }

            override fun saveUsers(users: List<UserEntity>): Completable = Completable.complete()
        }

        val repository: UsersRepository = DefaultUsersRepository(remoteDataStore, localDataStore)
        val observer = TestObserver<List<UserEntity>>()

        repository.getUsersList().subscribe(observer)

        observer.assertNoErrors()
        observer.assertValueCount(2)
        observer.assertValues(listOf(UserEntity("first"), UserEntity("second")),
                listOf(UserEntity("first"), UserEntity("second"), UserEntity("third")))
    }

    @Test
    fun testSaveReturnError() {
        val remoteDataStore = object : RemoteDataStore {
            override fun requestUsers(): Single<List<UserEntity>> {
                return Single.just(listOf(UserEntity("third"), UserEntity("second")))
            }
        }

        val localDataStore = object : LocalDataStore {
            override fun getAllUsers(): Single<List<UserEntity>> {
                return Single.just(listOf(UserEntity("first"), UserEntity("second")))
            }

            override fun saveUsers(users: List<UserEntity>): Completable = Completable.error(IllegalArgumentException())
        }

        val repository: UsersRepository = DefaultUsersRepository(remoteDataStore, localDataStore)
        val observer = TestObserver<List<UserEntity>>()

        repository.getUsersList().subscribe(observer)

        observer.assertNoErrors()
        observer.assertValueCount(2)
        observer.assertValues(listOf(UserEntity("first"), UserEntity("second")),
                listOf(UserEntity("first"), UserEntity("second"), UserEntity("third")))
    }

    @Test
    fun testMergingOfLocalAndRemoteData() {
        val remoteDataStore = object : RemoteDataStore {
            override fun requestUsers(): Single<List<UserEntity>> {
                return Single.just(listOf(UserEntity("second")))
            }
        }

        val localDataStore = object : LocalDataStore {
            override fun getAllUsers(): Single<List<UserEntity>> {
                return Single.just(listOf(UserEntity("first"), UserEntity("second")))
            }

            override fun saveUsers(users: List<UserEntity>): Completable = Completable.error(IllegalArgumentException())
        }

        val repository: UsersRepository = DefaultUsersRepository(remoteDataStore, localDataStore)
        val observer = TestObserver<List<UserEntity>>()

        repository.getUsersList().subscribe(observer)

        observer.assertNoErrors()
        observer.assertValueCount(1)
        observer.assertValue(listOf(UserEntity("first"), UserEntity("second")))
    }

}
