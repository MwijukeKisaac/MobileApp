package com.example.kakasaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;


public class ForgotPassword extends AppCompatActivity {

    private static final int SMS_PERMISSION_REQUEST_CODE = 1;
    private static final String EMAIL_SENDER = "your_email@example.com";
    private static final String EMAIL_PASSWORD = "your_email_password";

    private EditText contactInfoEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        contactInfoEditText = findViewById(R.id.editTextContactInfo);

        Button sendVerificationCodeButton = findViewById(R.id.buttonSendVerificationCode);
        sendVerificationCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });
    }

    private void sendVerificationCode() {
        String contactInfo = contactInfoEditText.getText().toString();

        if (isValidPhoneNumber(contactInfo)) {
            // Valid phone number, send SMS
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
            } else {
                sendSMS(contactInfo);
            }
        } else if (isValidEmail(contactInfo)) {
            // Valid email, send email
            sendEmail(contactInfo);
        } else {
            Toast.makeText(this, "Invalid contact information", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implement your phone number validation logic here
        // For example, you can use a regular expression or other validation techniques
        // Return true if the phone number is valid, false otherwise
        return true;
    }

    private boolean isValidEmail(String email) {
        // Implement your email validation logic here
        // For example, you can use a regular expression or other validation techniques
        // Return true if the email is valid, false otherwise
        return true;
    }

    private void sendSMS(String phoneNumber) {
        try {
            // Generate the verification code
            String verificationCode = generateVerificationCode();

            // Send the SMS
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, verificationCode, null, null);

            Toast.makeText(this, "Verification code sent successfully", Toast.LENGTH_SHORT).show();

            // Start the verification code activity to handle user input
            Intent intent = new Intent(this, VerificationCode.class);
            intent.putExtra("verification_code", verificationCode);
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, "Failed to send verification code", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void sendEmail(String email) {
        try {
            // Generate the verification code
            String verificationCode = generateVerificationCode();

            // Send the email
            String subject = "Password Reset Verification Code";
            String message = "Your verification code is: " + verificationCode;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose an email client"));

            Toast.makeText(this, "Verification code sent successfully", Toast.LENGTH_SHORT).show();

            // Start the verification code activity to handle user input
            Intent verificationCodeIntent = new Intent(this, VerificationCode.class);
            verificationCodeIntent.putExtra("verification_code", verificationCode);
            startActivity(verificationCodeIntent);

        } catch (Exception e) {
            Toast.makeText(this, "Failed to send verification code", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String generateVerificationCode() {
        // Generate the verification code logic goes here
        // You can use a library or create your own code generation algorithm
        // For example, you can use: return String.valueOf(new Random().nextInt(9000) + 1000);
        return "";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = contactInfoEditText.getText().toString();
                sendSMS(phoneNumber);
            } else {
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
