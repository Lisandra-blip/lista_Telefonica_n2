package br.univali.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import br.univali.contacts.databinding.FragmentContactDetailsBinding;

public class ContactDetailsFragment extends Fragment {
    private FragmentContactDetailsBinding binding;
    private ContactDetailViewModel viewModel;
    private Contact contact;
    private boolean isEditing;
    private int type = 0;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ContactDetailViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false);
        binding.contactType.setAdapter(
                ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.contact_type,
                        android.R.layout.simple_spinner_dropdown_item
                )
        );
        binding.contactType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                type = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        contact = (Contact) requireArguments().getSerializable("contact");
        isEditing = contact != null;
        if (contact == null) {
            contact = new Contact();
        }
        binding.name.getEditText().setText(contact.getName());
        binding.phone.getEditText().setText(contact.getPhone());
        binding.contactType.setSelection(contact.getType());
        if (!isEditing) {
            binding.buttonDelete.setVisibility(View.GONE);
        }
        binding.buttonSave.setOnClickListener(v -> {
            if (!validate()) return;
            contact.setName(binding.name.getEditText().getText().toString());
            contact.setPhone(binding.phone.getEditText().getText().toString());
            contact.setType(type);
            if (isEditing) {
                viewModel.update(requireContext(), contact);
            } else {
                viewModel.add(requireContext(), contact);
            }
            Navigation.findNavController(v).navigate(R.id.action_from_contact_details_to_contact_list);
        });
        binding.buttonDelete.setOnClickListener(v -> {
            viewModel.delete(requireContext(), contact);
            Navigation.findNavController(v).navigate(R.id.action_from_contact_details_to_contact_list);
        });
        return binding.getRoot();
    }

    private boolean validate() {
        String error = null;
        if (binding.name.getEditText().getText().toString().isEmpty()) {
            error = "Nome está vazio!";
        } else if (binding.phone.getEditText().getText().toString().isEmpty()) {
            error = "Telefone está vazio!";
        }
        if (error != null) {
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
        }
        return error == null;
    }
}
