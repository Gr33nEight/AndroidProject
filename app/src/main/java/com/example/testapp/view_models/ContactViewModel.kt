package com.example.testapp.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.contact.Contact
import com.example.testapp.models.contact.ContactRepository
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

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.updateContact(contact)
        }
    }
}