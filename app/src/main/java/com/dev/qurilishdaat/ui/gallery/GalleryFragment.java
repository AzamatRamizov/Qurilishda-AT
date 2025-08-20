package com.dev.qurilishdaat.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dev.qurilishdaat.R;
import com.dev.qurilishdaat.TestActivity;
import com.dev.qurilishdaat.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.startBtn.setOnClickListener(view -> {
            String name = binding.inputName.getText().toString().trim();
            String surname = binding.inputSurname.getText().toString().trim();
            String group = binding.inputGroup.getText().toString().trim();
            String variant = binding.selectSpinner.getSelectedItem().toString();

            if (name.isEmpty() || surname.isEmpty() || group.isEmpty()) {
                Toast.makeText(getActivity(), "Iltimos, barcha maydonlarni to'ldiring", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getActivity(), TestActivity.class);
                intent.putExtra("variant", variant);
                intent.putExtra("name", name);
                intent.putExtra("surname", surname);
                intent.putExtra("group", group);
                startActivity(intent);
            }
        });
        Spinner spinner = binding.selectSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.select_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}