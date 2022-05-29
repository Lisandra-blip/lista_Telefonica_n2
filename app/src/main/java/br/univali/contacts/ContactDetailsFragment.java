package br.univali.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import br.univali.contacts.databinding.FragmentContactDetailsBinding;

public class ContactDetailsFragment extends Fragment {
    private FragmentContactDetailsBinding binding;
    private ContactDetailViewModel viewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ContactDetailViewModel.class);
    }

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
        binding.buttonSave.setOnClickListener(v -> {
            Contact newContact = new Contact();
            newContact.setName(binding.name.getEditText().getText().toString());
            newContact.setPhone(binding.phone.getEditText().getText().toString());
            viewModel.add(requireContext(), newContact);
        });
        binding.buttonDelete.setOnClickListener(v -> {
            viewModel.delete(requireContext(), contact);
            Navigation.findNavController(v).navigate(R.id.action_from_contact_details_to_contact_list);
        });
        return binding.getRoot();
    }
}
