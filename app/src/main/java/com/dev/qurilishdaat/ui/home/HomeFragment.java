package com.dev.qurilishdaat.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dev.qurilishdaat.Accordion;
import com.dev.qurilishdaat.AmaliyList;
import com.dev.qurilishdaat.FilePages;
import com.dev.qurilishdaat.MAruzaList;
import com.dev.qurilishdaat.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.card1.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FilePages.class);
            intent.putExtra("pdfFileName", "obyektivka.pdf");
            startActivity(intent);
        });
        binding.card2.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FilePages.class);
            intent.putExtra("pdfFileName", "Shuxrat_medali.pdf");
            startActivity(intent);
        });
        binding.amaliy.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AmaliyList.class);
            startActivity(intent);
        });
        binding.maruza.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), Accordion.class);
            startActivity(intent);
        });
        binding.adabiyot.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FilePages.class);
            intent.putExtra("pdfFileName", "Adabiyotlar.pdf");
            startActivity(intent);
        });
        binding.nazorat.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MAruzaList.class);
            startActivity(intent);
        });
        binding.kirish.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FilePages.class);
            intent.putExtra("pdfFileName", "kirish.pdf");
            startActivity(intent);
        });
        binding.glossary.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FilePages.class);
            intent.putExtra("pdfFileName", "glossary.pdf");
            startActivity(intent);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}