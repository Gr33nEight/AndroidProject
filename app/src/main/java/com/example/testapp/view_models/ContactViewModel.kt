package com.example.testapp.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.Contact
import com.example.testapp.models.ContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactViewModel(private val contactRepository: ContactRepository): ViewModel() {
    val contacts = contactRepository.getContacts().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addContact(name: String, phoneNumber: String) {
        viewModelScope.launch {
            contactRepository.insertContact(Contact(name = name, phoneNumber = phoneNumber))
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.deleteContact(contact)
        }
    }
}