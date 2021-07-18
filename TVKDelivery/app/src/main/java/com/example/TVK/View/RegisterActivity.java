package com.example.TVK.View;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.TVK.Controller.IRegisterController;
import com.example.TVK.Controller.RegisterController;
import com.example.TVK.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    public Dialog dialogLoading, dialogOTP;
    public CountDownTimer countDownTimer;
    public TextView countdown;
    public Button btnCancel, btnVerify;
    public TextView btResend;
    public EditText txtnumber1,txtnumber2,txtnumber3,txtnumber4,txtnumber5, txtnumber6;
    private String txtOTP=null,verifyId=null, smsCode=null;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private final int START_COUNTDONW = 60000;

    Button btnDangky;
    TextView btnquaylai;
    EditText txtUsername,txtPassword,txtConfirmPassword , txtPhone;
    IRegisterController iRegisterController;
    Context context;
    private boolean isCheck;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken token;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iRegisterController = new RegisterController(this);
        anhxa();

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                requestPermission(phoneAuthCredential.getSmsCode());
                smsCode = phoneAuthCredential.getSmsCode();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT);
            }

            @Override
            public void onCodeSent(@NonNull String verifycationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verifyId = verifycationID;
                token = forceResendingToken;
                open_dialog(Gravity.CENTER);
                //showToast("otp_code: "+otp_code);
            }
        };

        // [END phone_auth_callbacks]

        btnquaylai.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
        context = this;
        btnDangky.setOnClickListener(v ->
        {
            iRegisterController.OnCheckDataAndSendOTP(txtUsername,txtPassword,txtConfirmPassword,txtPhone,context);
            //open_dialog(Gravity.CENTER);
        });
        txtUsername.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtConfirmPassword.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtPassword.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });
        txtPhone.setOnFocusChangeListener((v, hasFocus) -> {
            hideKeyboard(v,hasFocus);
        });


    }
    private void anhxa(){
        btnquaylai = findViewById(R.id.btBack);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtPhone = findViewById(R.id.txtPhone);
        btnDangky = findViewById(R.id.btndangki);
    }

    private void StartTimer()
    {
        if(countDownTimer==null)
        {
            countDownTimer = new CountDownTimer(START_COUNTDONW,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countdown.setText(Long.toString(millisUntilFinished/1000));
                }

                @Override
                public void onFinish() {
                    btResend.setVisibility(View.VISIBLE);
                }
            }.start();
        }
        else
        {
            countDownTimer.start();
        }

    }

    public void open_dialogLoading(int gravity, Context context) {
        dialogLoading = new Dialog(context);
        dialogLoading.setContentView(R.layout.layout_dialog_loading);
        dialogLoading.setCancelable(false);

        //dialogOTPLoading alignment
        Window window = dialogLoading.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity=gravity;
        window.setAttributes(windowAttributes);
        //startTimerAPI();
        dialogLoading.show();

    }

    @Override
    public void open_dialog(int gravity) {
        dialogOTP = new Dialog(context);
        dialogOTP.setContentView(R.layout.layout_dialog_verifyotp);
        dialogOTP.setCancelable(false);

        //dialogOTPLoading alignment
        Window window = dialogOTP.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity=gravity;
        window.setAttributes(windowAttributes);

        //Mapping
        //txtOTP =dialogOTPLoading.findViewById(R.id.txtOTP);
        txtnumber1 = dialogOTP.findViewById(R.id.txtnumber1);
        txtnumber2 = dialogOTP.findViewById(R.id.txtnumber2);
        txtnumber3 = dialogOTP.findViewById(R.id.txtnumber3);
        txtnumber4 = dialogOTP.findViewById(R.id.txtnumber4);
        txtnumber5 = dialogOTP.findViewById(R.id.txtnumber5);
        txtnumber6 = dialogOTP.findViewById(R.id.txtnumber6);
        btnCancel = dialogOTP.findViewById(R.id.btCancel);
        btnVerify = dialogOTP.findViewById(R.id.btVerify);
        btResend = dialogOTP.findViewById(R.id.btResend);

        txtnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1) txtnumber2.requestFocus();
            }
        });
        txtnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1) txtnumber3.requestFocus();
            }
        });
        txtnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1) txtnumber4.requestFocus();
            }
        });
        txtnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1) txtnumber5.requestFocus();
            }
        });
        txtnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1) txtnumber6.requestFocus();
            }
        });


        //btResend.setVisibility(View.GONE);

        //TextView txtPhoneContent = dialogOTPLoading.findViewById(R.id.txtPhoneContent);
        countdown = dialogOTP.findViewById(R.id.countdown);


        //txtPhoneContent.setText("Vui lòng nhập mã OTP được gửi về SĐT "+ phone+" để đăng ký tài khoản TVK Delivery");

        //close dialogOTPLoading
        btnCancel.setOnClickListener(v ->
        {
            dialogOTP.dismiss();
            dialogLoading.dismiss();
            verifyId = null;
            countDownTimer.onFinish();
        });

        //txtOTP.setOnFocusChangeListener((v, hasFocus) -> {

        //});
        btnVerify.setOnClickListener(v ->{
            btnVerifyClick(v);
        });

        btResend.setOnClickListener(v -> {
            dialogOTP.dismiss();
            dialogLoading.dismiss();
            verifyId = null;
            countDownTimer.onFinish();
            sendOTPMessage(txtPhone.getText().toString().trim());
        });

        StartTimer();
        dialogOTP.show();
    }

    @Override
    public void showToast(String str_error) {
        Toast.makeText(RegisterActivity.this,str_error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void combackLoginActivity() {
        btnquaylai.callOnClick();
    }

    @Override
    public void open_dialogLoading(int gravity) {
        dialogLoading = new Dialog(this);
        dialogLoading.setContentView(R.layout.layout_dialog_loading);
        dialogLoading.setCancelable(false);

        //dialogOTPLoading alignment
        Window window = dialogLoading.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity=gravity;
        window.setAttributes(windowAttributes);
        //startTimerAPI();
        dialogLoading.show();
    }

    @Override
    public void close_dialogLoading() {
        dialogLoading.dismiss();
    }

    @Override
    public void hideKeyboard(View view, boolean hasFocus) {
        if(!hasFocus)
        {
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void btnVerifyClick(View view)
    {
        //get OTP
        txtOTP = txtnumber1.getText().toString().trim() + txtnumber2.getText().toString().trim()
                +txtnumber3.getText().toString().trim()+txtnumber4.getText().toString().trim()
                +txtnumber5.getText().toString().trim() +txtnumber6.getText().toString().trim();
        if(txtOTP.length()<6)
        {
            txtnumber5.requestFocus();
            txtnumber5.setError("Need fill enough 6 digits of OTP");
            return;
        }
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                verifyId,
                txtOTP
        );
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful())
                 {
                     //mã đúng
                     //thêm người dùng
                     iRegisterController.OnAddNewCustomer(txtUsername,txtPassword,txtPhone,txtOTP,context);
                 }
                 else
                 {
                     //mã otp không đúng
                     btResend.setVisibility(View.VISIBLE);
                     Toast.makeText(RegisterActivity.this, "Mã OTP không đúng !", Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
    @Override
    public EditText getEditText(String name_editText)
    {
        switch (name_editText)
        {
            case "Username":
                return txtUsername;
            case "Password":
                return txtPassword;
            case "ConfirmPassword":
                return  txtConfirmPassword;
            case "Phone":
                return  txtPhone;
            default:
                return null;
        }

    }

    @Override
    public void sendOTPMessage(String phone_number) {
        //send OTP

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84"+phone_number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void requestPermission(String otp)
    {
        if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,Manifest.permission.RECEIVE_SMS))
            {
                showMessageOKCancel("You need to allow access to SMS",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {ActivityCompat.requestPermissions(RegisterActivity.this,new String[] {Manifest.permission.RECEIVE_SMS},
                                    REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{
                    Manifest.permission.RECEIVE_SMS},REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        char[] charotp = otp.toCharArray();
        txtnumber1.setText(String.valueOf(charotp[0]));
        txtnumber2.setText(String.valueOf(charotp[1]));
        txtnumber3.setText(String.valueOf(charotp[2]));
        txtnumber4.setText(String.valueOf(charotp[3]));
        txtnumber5.setText(String.valueOf(charotp[4]));
        txtnumber6.setText(String.valueOf(charotp[5]));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    char[] charotp = smsCode.toCharArray();
                    txtnumber1.setText(String.valueOf(charotp[0]));
                    txtnumber2.setText(String.valueOf(charotp[1]));
                    txtnumber3.setText(String.valueOf(charotp[2]));
                    txtnumber4.setText(String.valueOf(charotp[3]));
                    txtnumber5.setText(String.valueOf(charotp[4]));
                    txtnumber6.setText(String.valueOf(charotp[5]));
                } else {
                    // Permission Denied
                    Toast.makeText(RegisterActivity.this, "RECEIVE_SMS Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(RegisterActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }



}
