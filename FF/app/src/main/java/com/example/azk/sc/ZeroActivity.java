package com.example.azk.sc;

import android.app.Activity;
import android.os.Bundle;
import android.app.Application;
import android.view.View;
import android.content.Intent;

public class ZeroActivity extends Activity {
@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.zero);
}



    public void ButtonStart(View view) {
    Intent intent =new Intent();
    intent.setClass(ZeroActivity.this,MainActivity.class);
    startActivity(intent);
        }
    public void ButtonHelp(View view) {
        Intent intent =new Intent();
        intent.setClass(ZeroActivity.this,HelpActivity.class);
        startActivity(intent);
    }
        public void ButtonEnd(View view) {
            System.exit(0);
    }
    }


