package com.cvd.purchaserequestsystem;

import java.security.Timestamp;
import java.util.Date;
        public class PurchaseRequest {
            private int id;
            private String description;
            private String justification;
            private Date dateNeeded;
            private String deliveryMode;
            private String status;
            private double total;
            private Date submittedDate;
            private String reasonForRejection;
            private String UserName;
            private String FirstName;
            private String LastName;
            private String PhoneNumber;
            private String Email;

            public PurchaseRequest(int id, String description, String justification, String status, double total, String userName, String firstName, String lastName, String phoneNumber, String email) {
                this.id = id;
                this.description = description;
                this.justification = justification;
                this.status = status;
                this.total = total;
                UserName = userName;
                FirstName = firstName;
                LastName = lastName;
                PhoneNumber = phoneNumber;
                Email = email;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getJustification() {
                return justification;
            }

            public void setJustification(String justification) {
                this.justification = justification;
            }

            public Date getDateNeeded() {
                return dateNeeded;
            }

            public void setDateNeeded(Date dateNeeded) {
                this.dateNeeded = dateNeeded;
            }

            public String getDeliveryMode() {
                return deliveryMode;
            }

            public void setDeliveryMode(String deliveryMode) {
                this.deliveryMode = deliveryMode;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getTotal() {
                return total;
            }

            public void setTotal(double total) {
                this.total = total;
            }

            public Date getSubmittedDate() {
                return submittedDate;
            }

            public void setSubmittedDate(Date submittedDate) {
                this.submittedDate = submittedDate;
            }

            public String getReasonForRejection() {
                return reasonForRejection;
            }

            public void setReasonForRejection(String reasonForRejection) {
                this.reasonForRejection = reasonForRejection;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String userName) {
                UserName = userName;
            }

            public String getFirstName() {
                return FirstName;
            }

            public void setFirstName(String firstName) {
                FirstName = firstName;
            }

            public String getLastName() {
                return LastName;
            }

            public void setLastName(String lastName) {
                LastName = lastName;
            }

            public String getPhoneNumber() {
                return PhoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                PhoneNumber = phoneNumber;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String email) {
                Email = email;
            }
        }


