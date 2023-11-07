package com.cozentus.TrainingTracking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cozentus.TrainingTracking.Model.EvaluationName;
import com.cozentus.TrainingTracking.Repository.ExamRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
 
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private ExamRepository  examRepository;
 
    public void sendTeacherWelcomeEmail(String toEmail, String password) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
            helper.setTo(toEmail);
            helper.setSubject("Welcome to Our Platform");
            helper.setText("Welcome to our platform. Your username and password are as follows:\n\n"
                    + "Username: " + toEmail + "\nPassword: " + password);
 
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log it)
        }
    }
    
    public List<EvaluationName> getExamsByType(String type) {
        List<EvaluationName> exams = examRepository.findByType(type);
        return exams;
    }

}
