package com.dev.qurilishdaat;

import java.util.List;

public class AccordionItem {
    private String title;
    private List<Topic> topics;
    private boolean isExpanded;

    public AccordionItem(String title, List<Topic> topics) {
        this.title = title;
        this.topics = topics;
        this.isExpanded = false;
    }

    public String getTitle() {
        return title;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public static class Topic {
        private String topicName;
        private String pdfFileName;

        public Topic(String topicName, String pdfFileName) {
            this.topicName = topicName;
            this.pdfFileName = pdfFileName;
        }

        public String getTopicName() {
            return topicName;
        }

        public String getPdfFileName() {
            return pdfFileName;
        }
    }
}
