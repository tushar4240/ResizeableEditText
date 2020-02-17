package com.example.resizeableedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int prvCount=0;
    int check=0,bck=0;
    int size=90,mcount=0,pLineCount=0,previousLength=0;
    private boolean backSpace;

    String prv="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

         final EditText editText=findViewById(R.id.autoResizeEditText);
         editText.setTextSize(35);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                backSpace = previousLength > s.length();

                if(!prv.equals(s.toString())) {
                    prv=s.toString();
                    if (!s.equals("")) {
                        int lineCount = editText.getLineCount();
                        if (check == 0) {
                            editText.setTextSize(size);
                            size -= 2.66;
                            check = 1;
                        } else   if (prv.length()%24==0 ) {
                            pLineCount=lineCount;
                            if(backSpace ){
                                s.replace(s.length(),s.length(),"");
                                size=36;
                            }else{
                                size = 90;
                                s.append("\n");
                            }

                            s.setSpan(new AbsoluteSizeSpan(36 * (int) getResources().getDisplayMetrics().scaledDensity), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            editText.setSelection(editText.getText().length());
                        }
                        else if (size >= 26  ) {
                            int part=(prv.length()/24)*24;
                            mcount++;
                            s.setSpan(new AbsoluteSizeSpan(size * (int) getResources().getDisplayMetrics().scaledDensity), part, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            editText.setSelection(editText.getText().length());
                            if(backSpace){
                                size+=2.66;
                                bck=1;
                            }else{
                                size -=2.66;
                            }                        }

                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                previousLength = s.length();

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


            }
        });
    }
}
