package net.ymka.mvidemo.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import net.ymka.mvidemo.data.LocalDataStore
import net.ymka.mvidemo.data.RemoteDataStore
import net.ymka.mvidemo.data.entity.UserEntity

class DefaultUsersRepository(private val remoteStore: RemoteDataStore,
                             private val localStore: LocalDataStore) : UsersRepository {

    override fun getUsersList(): Observable<List<UserEntity>> {
        return Observable.merge(
                observeLocalStorage(),
                observeRemoteStorage())
                .filter { it.isNotEmpty() }
                .scan { oldOne: List<UserEntity>, newOne: List<UserEntity> ->
                    val list = oldOne.toMutableList()
                    list.addAll(newOne)
                    return@scan list
                }
    }

    private fun observeLocalStorage() = localStore.getAllUsers().toObservable()

    private fun observeRemoteStorage(): Observable<List<UserEntity>> {
        return remoteStore.requestUsers()
                .filter { it.isNotEmpty() }
                .flatMapObservable {
                    Single.zip<List<UserEntity>, List<UserEntity>, List<UserEntity>>(
                            Single.just(it),
                            localStore.getAllUsers(),
                            BiFunction { remoteList, localList ->
                                val list = remoteList.toMutableList()
                                list.removeAll(localList)
                                return@BiFunction list
                            })
                            .filter { it.isNotEmpty() }
                            .flatMapObservable {
                                localStore.saveUsers(it).onErrorComplete().andThen(Observable.just(it))
                            }
                }
    }

}
