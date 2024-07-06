package com.androiddevs.brailletranslator;

import android.os.Bundle;
import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DebugActivity extends Activity
{
	String[] ExceptionType = {
		"StringIndexOutOfBoundsException",
		"IndexOutOfBoundsException",
		"ArithmeticException",
		"NumberFormatException",
		"ActivityNotFoundException"
	};
	
	String[] ErrorMessages= {
		"Invalid string operation\n",
		"Invalid list operation\n",
		"Invalid arithmetical operation\n",
		"Invalid toNumber block operation\n",
		"Invalid intent operation"
	};
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String ErrorMessage = "";
		String MadeErrorMessage = "";
		if (intent != null)
		{
			ErrorMessage = intent.getStringExtra("error");
			String[] spilt = ErrorMessage.split("\n");
			try
			{
				for (int i = 0; i < ExceptionType.length; i++)
				{
					if (spilt[0].contains(ExceptionType[i]))
					{
						MadeErrorMessage = ErrorMessages[i];
						int addIndex = spilt[0].indexOf(ExceptionType[i]) + ExceptionType[i].length();
						MadeErrorMessage += spilt[0].substring(addIndex, spilt[0].length());
						break;
					}
				}
				if (MadeErrorMessage.isEmpty()) MadeErrorMessage = ErrorMessage;
			}
			catch (Exception e)
			{}
		}
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("An Error Occured");
		builder.setMessage(MadeErrorMessage);
		builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			});
		builder.create().show();
    }
}
