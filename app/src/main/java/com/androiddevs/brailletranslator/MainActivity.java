package com.androiddevs.brailletranslator;

import java.util.*;
import android.os.*;
import android.net.*;
import android.app.*;
import android.view.*; 
import android.widget.*;
import android.view.View;
import android.app.Activity;
import android.text.Editable;
import android.widget.Toast;
import android.graphics.Color;
import android.widget.Switch;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipData;
import android.text.TextWatcher;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.speech.tts.TextToSpeech;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.content.ClipboardManager;
import android.speech.RecognitionListener;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity
{
	private Switch switch1;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private EditText edittext1;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private String ClipboardText;
	private String TranslatedText = "";
	private String RecognizedText = "";
	private TextToSpeech texttospeech;
	private String TranslatedBrailleDots = "";
	private ClipboardManager clipboardmanager;
	private static int SPEECH_RECOGNIZER_REQUEST_CODE = 1995;
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		switch1 = findViewById(R.id.switch1);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		button3 = findViewById(R.id.button3);
		button4 = findViewById(R.id.button4);
		button5 = findViewById(R.id.button5);
		button6 = findViewById(R.id.button6);
		edittext1 = findViewById(R.id.edittext1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		texttospeech = new TextToSpeech(getApplicationContext(), null);

		switch1.setTypeface(Typeface.createFromAsset(getAssets(), "titilliumweb_regular.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(), "titilliumweb_regular.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(), "titilliumweb_regular.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(), "titilliumweb_regular.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(), "titilliumweb_regular.ttf"), 0);

		RoundButton(linear4, "#795548", 50);
		RoundButton(linear5, "#795548", 50);

		edittext1.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence charsequence, int start, int count, int after)
				{

				}
				@Override
				public void onTextChanged(CharSequence charsequence, int start, int before, int count)
				{
					String Text = charsequence.toString();
					if (Text.length() > 0)
					{
						if (switch1.isChecked())
						{
							textview2.setText(translateToText(Text));
						}
						else
						{
							textview2.setText(translateToBrailleDots(Text));
						}
					}
					else
					{
						if (Text.length() == 0)
						{
							textview2.setText("");
						}
				    }
				}
				@Override
				public void afterTextChanged(Editable editable)
				{

				}
			});

		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundbutton, boolean isChecked)
				{
					if (edittext1.getText().toString().length() > 0)
					{
						if (isChecked)
						{
							textview2.setText(translateToText(edittext1.getText().toString()));
						}
						else
						{
							textview2.setText(translateToBrailleDots(edittext1.getText().toString()));
						}
					}
				}
			});

		textview3.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse("https://t.me/SE_BIBEL_MEK"));
					startActivity(intent);
				}
			});

		button1.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					if ((edittext1.getText().toString().length() > 0) && !texttospeech.isSpeaking())
					{
						texttospeech.speak(edittext1.getText().toString(), TextToSpeech.QUEUE_ADD, null);
					}
				}
			});

		button2.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
					intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");
					try
					{
						startActivityForResult(intent, SPEECH_RECOGNIZER_REQUEST_CODE);
					}
					catch (Exception exception)
					{
						exception.printStackTrace();
					}
				}
			});

		button3.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					clipboardmanager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
					ClipData clipdata = clipboardmanager.getPrimaryClip();
					ClipData.Item item = clipdata.getItemAt(0);
					ClipboardText = item.getText().toString();
					edittext1.setText(ClipboardText);
					edittext1.setSelection(edittext1.getText().toString().length());
				}
			});

		button4.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					if (edittext1.getText().toString().length() > 0)
					{
						edittext1.setText("");
					}
				}
			});

		button5.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview2.getText().toString()));
					showMessage("Braille Dots Copied To Clipboard.");
				}
			});

		button6.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_TEXT, textview2.getText().toString());
					startActivity(Intent.createChooser(intent, "Share Using"));
				}
			});

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SPEECH_RECOGNIZER_REQUEST_CODE)
		{
			if (resultCode == Activity.RESULT_OK && data != null)
			{
				ArrayList<String> Results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); 
				RecognizedText = Objects.requireNonNull(Results).get(0);
				edittext1.setText(RecognizedText);
				edittext1.setSelection(edittext1.getText().toString().length());
			}
		}	
	}

	private void RoundButton(View view, String StrokeColor, int Radius)
	{
		GradientDrawable gradientdrawable = new GradientDrawable();
		gradientdrawable.setCornerRadius(Radius);
		gradientdrawable.setStroke(2, Color.parseColor(StrokeColor));
		view.setBackground(gradientdrawable);
	}

	private void showMessage(String Message)
	{
		Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
	}

	private String translateToBrailleDots(String Text)
	{
		BrailleTranslator brailletranslator = new BrailleTranslator(Text);
		TranslatedBrailleDots = brailletranslator.translateToBrailleDots();
		return TranslatedBrailleDots;
	}

	private String translateToText(String BrailleDots)
	{
		BrailleTranslator brailletranslator = new BrailleTranslator(BrailleDots);
		TranslatedText = brailletranslator.translateToText();
		return TranslatedText;
	}
}
