package br.univali.contacts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel : ViewModel() {
    private val _contacts = MutableLiveData<List<Contact>>(listOf())
    val contacts: LiveData<List<Contact>> = _contacts;

    fun findAll(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val contacts = ContactDatabase.getInstance(context).contactDao().findAll()
            withContext(Dispatchers.Main) {
                _contacts.value = contacts
            }
        }
    }

    fun add(context: Context, contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            ContactDatabase.getInstance(context).contactDao().add(contact)
            findAll(context)
        }
    }
}
