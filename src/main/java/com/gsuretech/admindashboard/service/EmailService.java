package com.gsuretech.admindashboard.service;

import com.gsuretech.admindashboard.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
    void sendEmailWithAttachment(EmailDetails emailDetails);
}
