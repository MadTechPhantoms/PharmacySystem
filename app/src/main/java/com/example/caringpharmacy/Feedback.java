package com.example.caringpharmacy;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Feedback implements Serializable {

        @Exclude
        private String key;
        private String email;
        private String feedbackdt;
        public Feedback(){}
        public Feedback(String email, String feedbackdt)
        {
            this.email = email;
            this.feedbackdt = feedbackdt;
        }

        public String getEmail()
        {
            return email;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public String getFeedbackdt()
        {
            return feedbackdt;
        }

        public void setFeedbackdt(String feedbackdt)
        {
            this.feedbackdt = feedbackdt;
        }
        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }
}
