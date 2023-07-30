package com.exopen.binkssound.Identifiant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.exopen.binkssound.R;
import com.exopen.binkssound.databinding.ActivityVerificationBinding;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {

    private ActivityVerificationBinding binding;
   // private FirebaseAuth mAuth;

    private String verificationId;

    private ProgressDialog dialog;
    String verificationCode;
    private TextView resendOtpTextView;
    private Long timeoutSeconds = 60L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.envoie_du_otp));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //mAuth = FirebaseAuth.getInstance();

        String phoneNumber = getIntent().getStringExtra("phone");
        binding.phoneLbl.setText(getString(R.string.v_rifier)+phoneNumber);
        resendOtpTextView = findViewById(R.id.resend_otp_textview);

        startResendTimer();
       /* PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)// (optional) Activity for callback binding// If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallBack)

                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        mAuth.setLanguageCode("fr");

        binding.otpView.setOtpCompletionListener(otp -> {
            dialog = new ProgressDialog(this);
            dialog.setMessage(getString(R.string.veuillez_patienter));
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);

            mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    dialog.dismiss();
                    Intent intent = new Intent(Otp.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    dialog.dismiss();
                    Toast.makeText(Otp.this, "Echec.", Toast.LENGTH_SHORT).show();
                }
            });
        });

        resendOtpTextView.setOnClickListener((v)->{
            startResendTimer();
            PhoneAuthOptions options1 =
                    PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber(phoneNumber)       // Phone number to verify
                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                            .setActivity(this)// (optional) Activity for callback binding// If no activity is passed, reCAPTCHA verification can not be used.
                            .setCallbacks(mCallBack)
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options1);
            mAuth.setLanguageCode("fr");
        }); */
    }

   /* private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            dialog.dismiss();
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.

                //edtOTP.setText(code);
                binding.otpView.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(Otp.this, e.getMessage(), Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithCredential:success");

                        Intent intent = new Intent(Otp.this, MainActivity.class);
                        startActivity(intent);
                        finishAffinity();

                        FirebaseUser user = task.getResult().getUser();
                        // Update UI
                    } else {
                        // Sign in failed, display a message and update the UI
                        //Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(Otp.this, getString(R.string.erreur)+task.getException(), Toast.LENGTH_SHORT).show();
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                });
    } */

    void startResendTimer(){
        resendOtpTextView.setEnabled(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeoutSeconds--;
                resendOtpTextView.setText(getString(R.string.reenvoyer_l_otp_dans)+timeoutSeconds +getString(R.string.secondes));
                if(timeoutSeconds<=0){
                    timeoutSeconds =60L;
                    timer.cancel();
                    runOnUiThread(() -> {
                        resendOtpTextView.setEnabled(true);
                    });
                }
            }
        },0,1000);
    }

}
