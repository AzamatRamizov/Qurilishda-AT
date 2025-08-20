package com.dev.qurilishdaat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Accordion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accordion);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ma'lumotlarni tayyorlash
        List<AccordionItem> accordionItems = new ArrayList<>();
        accordionItems.add(new AccordionItem("I.BOB", Arrays.asList(
                new AccordionItem.Topic("I-BOB 1-mavzu", "file_1_1.pdf"),
                new AccordionItem.Topic("I-BOB 2-mavzu", "file_1_2.pdf")
        )));
        accordionItems.add(new AccordionItem("II.BOB", Arrays.asList(
                new AccordionItem.Topic("II-BOB 1-mavzu", "file_2_1.pdf"),
                new AccordionItem.Topic("II-BOB 2-mavzu", "file_2_2.pdf"),
                new AccordionItem.Topic("II-BOB 3-mavzu", "file_2_3.pdf")
        )));
        accordionItems.add(new AccordionItem("III.BOB", Arrays.asList(
                new AccordionItem.Topic("III.BOB 1-mavzu", "file_3_1.pdf"),
                new AccordionItem.Topic("III.BOB 2-mavzu", "file_3_2.pdf"),
                new AccordionItem.Topic("III.BOB 3-mavzu", "file_3_3.pdf"),
                new AccordionItem.Topic("III.BOB 4-mavzu", "file_3_4.pdf")
        )));
        accordionItems.add(new AccordionItem("IV.BOB", Arrays.asList(
                new AccordionItem.Topic("IV.BOB 1-mavzu", "file_4_1.pdf"),
                new AccordionItem.Topic("IV.BOB 2-mavzu", "file_4_2.pdf")
        )));
        accordionItems.add(new AccordionItem("V.BOB", Arrays.asList(
                new AccordionItem.Topic("V.BOB 1-mavzu", "file_5_1.pdf"),
                new AccordionItem.Topic("V.BOB 2-mavzu", "file_5_2.pdf")
        )));
        accordionItems.add(new AccordionItem("VI.BOB", Arrays.asList(
                new AccordionItem.Topic("VI.BOB 1-mavzu", "file_6_1.pdf"),
                new AccordionItem.Topic("VI.BOB 2-mavzu", "file_6_2.pdf")
        )));
        accordionItems.add(new AccordionItem("VII.BOB", Arrays.asList(
                new AccordionItem.Topic("VII.BOB 1-mavzu", "file_7_1.pdf"),
                new AccordionItem.Topic("VII.BOB 2-mavzu", "file_7_2.pdf"),
                new AccordionItem.Topic("VII.BOB 3-mavzu", "file_7_3.pdf")
        )));
        accordionItems.add(new AccordionItem("VIII.BOB", Arrays.asList(
                new AccordionItem.Topic("VIII.BOB 1-mavzu", "file_8_1.pdf"),
                new AccordionItem.Topic("VIII.BOB 2-mavzu", "file_8_2.pdf")
        )));
        accordionItems.add(new AccordionItem("IX.BOB", Arrays.asList(
                new AccordionItem.Topic("IX.BOB 1-mavzu", "file_9_1.pdf"),
                new AccordionItem.Topic("IX.BOB 2-mavzu", "file_9_2.pdf"),
                new AccordionItem.Topic("IX.BOB 3-mavzu", "file_9_3.pdf")
        )));
        accordionItems.add(new AccordionItem("X.BOB", Arrays.asList(
                new AccordionItem.Topic("X.BOB 1-mavzu", "file_10_1.pdf"),
                new AccordionItem.Topic("X.BOB 2-mavzu", "file_10_2.pdf"),
                new AccordionItem.Topic("X.BOB 3-mavzu", "file_10_3.pdf")
        )));

        // Adapter sozlash
        AccordionAdapter adapter = new AccordionAdapter(this, accordionItems);
        recyclerView.setAdapter(adapter);
    }
}