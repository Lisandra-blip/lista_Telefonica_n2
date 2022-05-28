package br.univali.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import br.univali.contacts.databinding.FragmentContactListBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactListFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentContactListBinding binding = FragmentContactListBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Contact> contacts = new ArrayList<>();
        binding.recyclerView.setAdapter(new ContactListAdapter(contacts));
        return binding.getRoot();
    }
}
