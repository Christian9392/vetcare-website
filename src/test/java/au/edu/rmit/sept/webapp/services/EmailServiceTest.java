package au.edu.rmit.sept.webapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @Mock
    private MimeMessage mimeMessage;

    @Mock
    private MimeMessageHelper mimeMessageHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    // Test Case 1:  Test if the email service successfully sends an email without throwing any exceptions.
    // This test validates that the service behaves correctly when everything works as expected.
    @Test
    void testSendHealthRecord_Success() throws MessagingException, IOException {
        ByteArrayOutputStream healthRecordPdf = new ByteArrayOutputStream();
        healthRecordPdf.write("Mock PDF content".getBytes());
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);        
        assertDoesNotThrow(() -> emailService.sendHealthRecord("test@example.com", "Fluffy", healthRecordPdf));
        verify(javaMailSender, times(1)).send(any(MimeMessage.class));
    }
}
