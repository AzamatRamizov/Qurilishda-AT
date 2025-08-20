package com.dev.qurilishdaat.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.qurilishdaat.R;

import java.util.List;

public class AttemptAdapter extends RecyclerView.Adapter<AttemptAdapter.AttemptViewHolder>{
    private final List<String> attempts;
    private final Context context;

    public AttemptAdapter(Context context, List<String> attempts) {
        this.context = context;
        this.attempts = attempts;
    }

    @NonNull
    @Override
    public AttemptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_attempt, parent, false);
        return new AttemptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttemptViewHolder holder, int position) {
        String attempt = attempts.get(position);
        holder.attemptTitle.setText((position + 1) + "-urinish");
        holder.attemptResult.setText(attempt);

        holder.shareButton.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, (position + 1) + "-urinish:\n" + attempt);
            context.startActivity(Intent.createChooser(shareIntent, "Natijani ulashish"));
        });
    }

    @Override
    public int getItemCount() {
        return attempts.size();
    }

    public static class AttemptViewHolder extends RecyclerView.ViewHolder {
        TextView attemptTitle, attemptResult;
        Button shareButton;

        public AttemptViewHolder(@NonNull View itemView) {
            super(itemView);
            attemptTitle = itemView.findViewById(R.id.attemptTitle);
            attemptResult = itemView.findViewById(R.id.attemptResult);
            shareButton = itemView.findViewById(R.id.shareButton);
        }
    }
}
