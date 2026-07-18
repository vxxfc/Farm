package com.farmmanager.app.data.repository

import com.farmmanager.app.data.AppDatabase
import com.farmmanager.app.data.entity.*

class FarmRepository(db: AppDatabase) {

    private val flockDao = db.flockDao()
    private val eggDao = db.eggDao()
    private val feedDao = db.feedDao()
    private val healthDao = db.healthDao()
    private val transactionDao = db.transactionDao()
    private val reminderDao = db.reminderDao()
    private val noteDao = db.noteDao()

    // Flocks
    fun getAllFlocks() = flockDao.getAll()
    suspend fun getFlockById(id: Long) = flockDao.getById(id)
    suspend fun insertFlock(flock: Flock) = flockDao.insert(flock)
    suspend fun updateFlock(flock: Flock) = flockDao.update(flock)
    suspend fun deleteFlock(flock: Flock) = flockDao.delete(flock)
    fun getTotalBirds() = flockDao.getTotalBirds()

    // Eggs
    fun getAllEggRecords() = eggDao.getAll()
    fun getEggRecordsByFlock(flockId: Long) = eggDao.getByFlock(flockId)
    suspend fun insertEggRecord(record: EggRecord) = eggDao.insert(record)
    suspend fun updateEggRecord(record: EggRecord) = eggDao.update(record)
    suspend fun deleteEggRecord(record: EggRecord) = eggDao.delete(record)
    fun getTotalEggsCollectedBetween(start: Long, end: Long) = eggDao.getTotalCollectedBetween(start, end)

    // Feeds
    fun getAllFeedRecords() = feedDao.getAll()
    fun getFeedRecordsByFlock(flockId: Long) = feedDao.getByFlock(flockId)
    suspend fun insertFeedRecord(record: FeedRecord) = feedDao.insert(record)
    suspend fun updateFeedRecord(record: FeedRecord) = feedDao.update(record)
    suspend fun deleteFeedRecord(record: FeedRecord) = feedDao.delete(record)
    fun getTotalFeedCostBetween(start: Long, end: Long) = feedDao.getTotalCostBetween(start, end)

    // Health
    fun getAllHealthRecords() = healthDao.getAll()
    fun getHealthRecordsByFlock(flockId: Long) = healthDao.getByFlock(flockId)
    suspend fun insertHealthRecord(record: HealthRecord) = healthDao.insert(record)
    suspend fun updateHealthRecord(record: HealthRecord) = healthDao.update(record)
    suspend fun deleteHealthRecord(record: HealthRecord) = healthDao.delete(record)

    // Transactions
    fun getAllTransactions() = transactionDao.getAll()
    suspend fun insertTransaction(transaction: TransactionEntity) = transactionDao.insert(transaction)
    suspend fun updateTransaction(transaction: TransactionEntity) = transactionDao.update(transaction)
    suspend fun deleteTransaction(transaction: TransactionEntity) = transactionDao.delete(transaction)
    fun getTotalByTypeBetween(type: TransactionType, start: Long, end: Long) =
        transactionDao.getTotalByTypeBetween(type, start, end)

    // Reminders
    fun getAllReminders() = reminderDao.getAll()
    suspend fun insertReminder(reminder: Reminder) = reminderDao.insert(reminder)
    suspend fun updateReminder(reminder: Reminder) = reminderDao.update(reminder)
    suspend fun deleteReminder(reminder: Reminder) = reminderDao.delete(reminder)

    // Notes
    fun getAllNotes() = noteDao.getAll()
    suspend fun insertNote(note: Note) = noteDao.insert(note)
    suspend fun updateNote(note: Note) = noteDao.update(note)
    suspend fun deleteNote(note: Note) = noteDao.delete(note)
}
