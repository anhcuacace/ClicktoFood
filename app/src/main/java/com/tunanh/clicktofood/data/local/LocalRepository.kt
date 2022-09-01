package com.tunanh.clicktofood.data.local

import com.tunanh.clicktofood.data.local.dao.FoodDao
import com.tunanh.clicktofood.data.local.dao.HistorySearchDao
import com.tunanh.clicktofood.data.local.dao.UserDao
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.local.model.KeyWorkSearch
import com.tunanh.clicktofood.data.local.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val userDao: UserDao,
    private val foodDao: FoodDao,
    private val historySearchDao: HistorySearchDao
) {



//user
    suspend fun insertUser(user: User) = userDao.addUser(user)

    suspend fun isEmailIsExist(email: String) = userDao.isRowIsExist(email)

    suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    suspend fun deleteUser() {
        userDao.deleteUser()
    }
//food
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

    suspend fun insertOrUpdate(food: Food ) {
        if (foodDao.isRowIsExist(food.id))
            foodDao.update(food)
        else
            foodDao.addFood(food)
    }
    suspend fun addFood(listFood: List<Food>) {
        foodDao.inserts(listFood)
    }
    suspend fun getCard(): List<Food> =foodDao.loadCard()


    suspend fun findFoodByName(name: String): List<Food> = foodDao.findFoodByName(name)

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