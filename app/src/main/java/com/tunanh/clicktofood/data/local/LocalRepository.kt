package com.tunanh.clicktofood.data.local

import com.tunanh.clicktofood.data.local.dao.*
import com.tunanh.clicktofood.data.local.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val favouriteQuoteDao: FavouriteQuoteDao,
    private val itemDao: ItemDao,
    private val userDao: UserDao,
    private val foodDao: FoodDao,
    private val foodDataDao: FoodDataDao,
    private val historySearchDao: HistorySearchDao
) {

    suspend fun addFavouriteQuote(favouriteQuote: FavouriteQuote) {
        favouriteQuoteDao.addQuote(favouriteQuote)
    }

    suspend fun deleteFavouriteQuote(favouriteQuote: FavouriteQuote) {
        favouriteQuoteDao.removeFromFavourite(favouriteQuote)
    }

    suspend fun getAllFavouriteQuotes(): List<FavouriteQuote> {
        return favouriteQuoteDao.getAllQuotes()
    }

    suspend fun addItem(item: Item) = itemDao.addItem(item)

    suspend fun getItemWithId(id: Int) = itemDao.getItemWithId(id)

    suspend fun getAllItems() = itemDao.getAllItems()

    suspend fun getFinishedItemCountByType(type: String) =
        itemDao.getFinishedItemCountByType(type)

    suspend fun getWatchedEpisodesSum() = itemDao.getWatchedEpisodesSum()

    suspend fun insertUser(user: User) = userDao.addUser(user)

    suspend fun isEmailIsExist(email: String) = userDao.isRowIsExist(email)

    suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    suspend fun deleteUser() {
        userDao.deleteUser()
    }

    suspend fun getUser() = userDao.getUser()

    suspend fun insertFood(food: Food) {
        foodDao.addFood(food)
    }

    suspend fun getAllFood() = foodDao.getAllFood()


    suspend fun deleteById(id: Long) {
        foodDao.deleteById(id)
    }

    suspend fun deleteAllFood() {
        foodDao.deleteAllFood()
    }

    suspend fun updateFood(food: Food) {
        foodDao.update(food)
    }

    suspend fun isRowIsExist(id: Long) = foodDao.isRowIsExist(id)

    suspend fun addFood(listFood: List<FoodData>) {
        foodDataDao.inserts(listFood)
    }

    suspend fun getAllFoodData() = foodDataDao.getFoodList()

    suspend fun findFoodByName(name: String): List<FoodData> = foodDataDao.findFoodByName(name)

    suspend fun insertHistory(keyword: KeyWorkSearch) {
        historySearchDao.insertHistory(keyword)
    }

    suspend fun deleteHistory(keyword: KeyWorkSearch) {
        historySearchDao.deleteHistory(keyword)
    }

    suspend fun getHistoryList(): List<KeyWorkSearch>? = historySearchDao.getHistoryList()

    suspend fun deleteAll() {
        historySearchDao.deleteAll()
    }

    suspend fun getHistoryByName(text: String): KeyWorkSearch? =
        historySearchDao.getHistoryByName(text)

}