package com.xe.Axe;                                                //~1726I~

import android.widget.EditText;

public interface EditTextListener                                  //~1730R~
{                                                                  //~1726I~
	public void onEditTextChanged(int Pviewid,int PintValue); //by Onkey//+0728R~
	public void onEditTextChanged(int Pviewid,String Pvalue); //by OnKey     //~0703I~//+0728R~
    public void beforeTextChangedETF(EditText PeditText, String Ptext, int start, int count, int after);//~0704R~
    public void onTextChangedETF(EditText PeditText,String Ptext,int start,int before,int count);//~0704R~
    public void afterTextChangedETF(EditText PeditText,String Ptext);//~0704R~
}                                                                  //~1726R~
