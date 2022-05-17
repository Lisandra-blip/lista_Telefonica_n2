package br.univali.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.univali.contacts.databinding.FragmentContactDetailsBinding;

public class ContactDetailsFragment extends Fragment {
    private FragmentContactDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false);
        Contact contact = (Contact) requireArguments().getSerializable("contact");
        if (contact != null) {
            assert (binding.name.getEditText() != null);
            assert (binding.phone.getEditText() != null);
            binding.name.getEditText().setText(contact.getName());
            binding.phone.getEditText().setText(contact.getPhone());
        }
        return binding.getRoot();
    }
}
