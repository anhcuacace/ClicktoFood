package com.tunanh.clicktofood.data.local

import com.tunanh.clicktofood.data.local.dao.FavouriteQuoteDao
import com.tunanh.clicktofood.data.local.dao.FoodDao
import com.tunanh.clicktofood.data.local.dao.ItemDao
import com.tunanh.clicktofood.data.local.dao.UserDao
import com.tunanh.clicktofood.data.local.model.FavouriteQuote
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.local.model.Item
import com.tunanh.clicktofood.data.local.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val favouriteQuoteDao: FavouriteQuoteDao,
    private val itemDao: ItemDao,
    private val userDao: UserDao,
    private val foodDao: FoodDao
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

    suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    suspend fun getUser(email: String) = userDao.getUser()

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
    suspend fun updateFood(food: Food){
        foodDao.update(food)
    }

    suspend fun isRowIsExist(id: Long)=foodDao.isRowIsExist(id)
}