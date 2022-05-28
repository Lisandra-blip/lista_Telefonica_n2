package br.univali.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.univali.contacts.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {
    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentContactListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val contacts = ContactDatabase.getInstance(requireActivity()).contactDao().findAll()
        binding.recyclerView.adapter = ContactListAdapter(contacts)
        return binding.root
    }
}
