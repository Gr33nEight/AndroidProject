package com.example.testapp.models.contact

import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {
    fun getContacts(): Flow<List<Contact>> = contactDao.getAllContacts()
    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)
    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)
    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)
}