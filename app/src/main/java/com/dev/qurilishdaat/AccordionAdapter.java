package com.dev.qurilishdaat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccordionAdapter extends RecyclerView.Adapter<AccordionAdapter.ViewHolder> {
    private List<AccordionItem> items;
    private Context context;

    public AccordionAdapter(Context context, List<AccordionItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accordion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccordionItem item = items.get(position);
        holder.tvTitle.setText(item.getTitle());

        // Kontentni ko'rsatish/yashirish
        holder.llContent.setVisibility(item.isExpanded() ? View.VISIBLE : View.GONE);
        holder.tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                item.isExpanded() ? R.drawable.baseline_arrow_drop_up_24 : R.drawable.baseline_arrow_drop_down_24, 0);

        // Mavzularni dinamik qo'shish
        holder.llContent.removeAllViews();
        for (int i = 0; i < item.getTopics().size(); i++) {
            AccordionItem.Topic topic = item.getTopics().get(i);
            TextView topicView = new TextView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, i == 0 ? 0 : 4, 4, 20); // Mavzular orasida masofa
            topicView.setLayoutParams(params);
            topicView.setText(topic.getTopicName());
            topicView.setTextSize(24);
            topicView.setPadding(60, 15, 12, 15);
            topicView.setBackgroundResource(R.drawable.gradient_blue);
            topicView.setTextColor(context.getResources().getColor(android.R.color.white));
            topicView.setElevation(2f); // Nozik soya effekti (API 21+)
            topicView.setOnClickListener(v -> openPdf(topic.getPdfFileName()));
            holder.llContent.addView(topicView);
        }

        // Sarlavhaga bosilganda ochish/yopish
        holder.tvTitle.setOnClickListener(v -> {
            item.setExpanded(!item.isExpanded());
            notifyItemChanged(position);
        });
    }

    private void openPdf(String pdfFileName) {
        Intent intent = new Intent(context, FilePages.class);
        intent.putExtra("pdfFileName", pdfFileName);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        LinearLayout llContent;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            llContent = itemView.findViewById(R.id.ll_content);
        }
    }
}
