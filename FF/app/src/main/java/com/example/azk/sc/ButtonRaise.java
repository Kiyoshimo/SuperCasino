package com.example.azk.sc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class ButtonRaise {

    public Context brContext;
    public int M;
    public boolean btnS;



    //要把那个啥穿进去
    public void getContext(Context bContext) {
        brContext = bContext;
    }
    //送出按钮状态
    public boolean showBtnS() {
        Toast.makeText(brContext, "now:" + String.valueOf(btnS), Toast.LENGTH_SHORT).show();
        return btnS;
    }

    public void showNormalDialog() {
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(brContext);
        // 获取布局
        View view2 = View.inflate(brContext, R.layout.activity_raise_seekbar, null);
        // 获取布局中的控件
        final SeekBar sb = (SeekBar) view2.findViewById(R.id.sb_raise);
        final TextView tv1 = (TextView) view2.findViewById(R.id.tv_raise_sb1);
        final TextView tv2 = (TextView) view2.findViewById(R.id.tv_raise_sb2);
        final TextView tv3 = (TextView) view2.findViewById(R.id.tv_raise_sb3);
        final Button btn = (Button) view2.findViewById(R.id.btn_raise_sb);
        // 设置参数
        builder.setTitle("下注").setView(view2);
        btn.setText("跟注");

        // 创建对话框
        final AlertDialog alertDialog = builder.create();

        //SeekBar监听
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //停止拖曳時觸發事件
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            //開始拖曳時觸發事件
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            //拖曳途中觸發事件，回傳參數 progress 告知目前拖曳數值
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                M = progress;
                if (M == 2) btn.setText("跟注");
                else if (M == 200) btn.setText("ALL IN！");
                else btn.setText("下注" + M + "元");
            }
        });

        //按钮监听

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnS = true;



                Toast.makeText(brContext, "下注！" + String.valueOf(btnS), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();// 对话框消失
            }
        });


        alertDialog.show();


    }
}




