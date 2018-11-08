package com.example.azk.sc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button btnFold,btnCheck,btnRaise;
    private ImageView ivCC1,ivCC2,ivCC3,ivCC4,ivCC5,ivHC1,ivHC2 ;
    private TextView tv1,tv2,tv3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //初始化
    protected void  init(){
        //按钮们
        btnFold= (Button) findViewById(R.id.b_fold);
        btnRaise= (Button) findViewById(R.id.b_raise);
        btnCheck= (Button) findViewById(R.id.b_check);
        //扑克图片们
        ivCC1=(ImageView)findViewById(R.id.iv_cc1);
        ivCC2=(ImageView)findViewById(R.id.iv_cc2);
        ivCC3=(ImageView)findViewById(R.id.iv_cc3);
        ivCC4=(ImageView)findViewById(R.id.iv_cc4);
        ivCC5=(ImageView)findViewById(R.id.iv_cc5);
        ivHC1=(ImageView)findViewById(R.id.iv_h1);
        ivHC2=(ImageView)findViewById(R.id.iv_h2);
        //资金数据TextView们
        tv1=(TextView) findViewById(R.id.tv_tm2);
        tv2=(TextView) findViewById(R.id.tv_tm4);
        tv3=(TextView) findViewById(R.id.tv_tm5);

    }


    //下注按钮ButtonRaise[android:onClick="ButtonRaise"]
    public void ButtonRaise(View view) {
        ButtonRaise MyButtonRaise=new ButtonRaise();
        MyButtonRaise.getContext(this);
        MyButtonRaise.showNormalDialog();
    }
    //弃牌按钮ButtonFold[android:onClick="ButtonFold"]
    public void ButtonFold(View view) {
        ButtonFold MyButtonFold=new ButtonFold();
    }
    //看牌按钮ButtonCheck[android:onClick="ButtonCheck"]
    public void ButtonCheck(View view) {
        ButtonCheck MyButtonCheck=new ButtonCheck();
    }



    }



