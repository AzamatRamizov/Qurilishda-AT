package com.dev.qurilishdaat.ui.slideshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dev.qurilishdaat.databinding.FragmentSlideshowBinding;
import com.dev.qurilishdaat.ui.AttemptAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences prefs = requireActivity().getSharedPreferences("TestHistory", Context.MODE_PRIVATE);
        int attemptCount = prefs.getInt("attemptCount", 0);

        List<String> attempts = new ArrayList<>();
        for (int i = 1; i <= attemptCount; i++) {
            String result = prefs.getString("attempt_" + i, null);
            if (result != null) attempts.add(result);
        }

        AttemptAdapter adapter = new AttemptAdapter(requireContext(), attempts);
        binding.recyclerAttempts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAttempts.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}