package com.example.abhishek.learningdisability;

import android.content.Intent;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class M1P1Test extends AppCompatActivity {
    TextView txvResult;
    TextView encodedText1;
    TextView encodedText2;
    TextView encodedText3;
    TextView encodedText4;
    TextView encodedText5;
    TextView resText;
    TextView currentScoreTextView;
    Boolean flag;
    String finalResult;
    TextToSpeech ttsobject;
    int result;
    String encodedVoice="";
    int numerator=0,denominator=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_p1_test);

        txvResult = (TextView) findViewById(R.id.txvResult);
        encodedText1 = (TextView) findViewById(R.id.encodedText1);
        encodedText2 = (TextView) findViewById(R.id.encodedText2);
        encodedText3 = (TextView) findViewById(R.id.encodedText3);
        encodedText4 = (TextView) findViewById(R.id.encodedText4);
        encodedText5 = (TextView) findViewById(R.id.encodedText5);
        resText = (TextView) findViewById(R.id.resText);
        currentScoreTextView = (TextView) findViewById(R.id.currentScoreTextView);

        ttsobject = new TextToSpeech(M1P1Test.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    result =  ttsobject.setLanguage(Locale.ENGLISH);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void doSomething(View view){
        switch(view.getId()){
            case R.id.imageButton1:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
                else{
                    ttsobject.speak("CITY",TextToSpeech.QUEUE_FLUSH,null);
                }
                break;

            case R.id.imageButton2:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
                else{
                    ttsobject.speak("HOUSE",TextToSpeech.QUEUE_FLUSH,null);
                }
                break;

            case R.id.imageButton3:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
                else{
                    ttsobject.speak("SNAKE",TextToSpeech.QUEUE_FLUSH,null);
                }
                break;

            case R.id.imageButton4:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
                else{
                    ttsobject.speak("GlASS",TextToSpeech.QUEUE_FLUSH,null);
                }
                break;

            case R.id.imageButton5:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
                else{
                    ttsobject.speak("PALACE",TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
        }
    }

    public void getSpeechInput(View view) {

        switch(view.getId()){
            case R.id.btnSpeak1:
                encodedVoice = encodedText1.getText().toString().toUpperCase();
                break;

            case R.id.btnSpeak2:
                encodedVoice = encodedText2.getText().toString().toUpperCase();
                break;

            case R.id.btnSpeak3:
                encodedVoice = encodedText3.getText().toString().toUpperCase();
                break;

            case R.id.btnSpeak4:
                encodedVoice = encodedText4.getText().toString().toUpperCase();
                break;

            case R.id.btnSpeak5:
                encodedVoice = encodedText5.getText().toString().toUpperCase();
                break;
        }

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0).toUpperCase());
                    //encodedVoice = encodedText.getText().toString().toUpperCase();
                    String recordedVoice = txvResult.getText().toString();
                    flag = encodedVoice.equals(recordedVoice);
                    if(flag){
                        finalResult = "Good Job!";
                        resText.setText(finalResult);
                        resText.setTextColor(Color.parseColor("green"));
                        numerator=numerator+1;
                        denominator=denominator+1;

                    }
                    else {
                        finalResult = "Oops, Try Again!";
                        resText.setText(finalResult);
                        resText.setTextColor(Color.parseColor("red"));
                        denominator=denominator+1;
                    }

                    currentScoreTextView.setText("Current Score = "+numerator+"/"+denominator);
                }
                break;
        }
    }
}
