package com.farmmanager.app.ui.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farmmanager.app.data.entity.Reminder
import com.farmmanager.app.data.repository.FarmRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReminderViewModel(private val repository: FarmRepository) : ViewModel() {

    val reminders = repository.getAllReminders()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addReminder(title: String, dateTime: Long, notes: String) {
        viewModelScope.launch {
            repository.insertReminder(Reminder(title = title, dateTime = dateTime, notes = notes))
        }
    }

    fun toggleCompleted(reminder: Reminder) {
        viewModelScope.launch {
            repository.updateReminder(reminder.copy(isCompleted = !reminder.isCompleted))
        }
    }

    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch { repository.deleteReminder(reminder) }
    }
}
