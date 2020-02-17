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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int prvCount=0;
    int check=0;
    int size=100,mcount=0,pLineCount=0;
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

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(!prv.equals(s.toString())) {
                    prv=s.toString();
                    if (!s.equals("")) {
                        int lineCount = editText.getLineCount();
                        if (check == 0) {
                            editText.setTextSize(size);
                            size -= 2.66;
                            check = 1;
                        } else   if (prv.length()%24==0) {
                            pLineCount=lineCount;
                            size = 100;
                            mcount=0;
                            String dispStr = prv+"\n";
 //                           prv=prv+"\n";
                        }
                        else if (size >= 36  ) {
                            //   editText.setTextSize(size);
                            int part=(prv.length()/24)*24;
                          //  int part=prv.length()-mcount;
                            mcount++;
                            String dispStr1 = prv.substring(0,part);
                            String dispStr2 = prv.substring(part);

                            int startSpan1 = 0;
                            int endSpan1 = dispStr1.length();
                            Spannable spanRange1 = new SpannableString(dispStr1);
                            spanRange1.setSpan(new AbsoluteSizeSpan(36 * (int) getResources().getDisplayMetrics().scaledDensity), startSpan1, endSpan1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                            int startSpan2 = 0;
                            int endSpan2 = dispStr2.length();
                            Spannable spanRange2 = new SpannableString(dispStr2);
                            spanRange2.setSpan(new AbsoluteSizeSpan(size * (int) getResources().getDisplayMetrics().scaledDensity), startSpan2, endSpan2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            size -= 2.66;
                            editText.setText(TextUtils.concat(spanRange1,spanRange2));
                            editText.setSelection(editText.getText().length());
                        }

                    }
                }

            }
        });
    }
}
