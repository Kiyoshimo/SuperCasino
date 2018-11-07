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

public class ButtonRaise{

    public Context brContext;

    public void getContext(Context bContext){
        brContext=bContext;
    }
    public void showNormalDialog(){
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(brContext);
        // 获取布局
        View view2 = View.inflate(brContext, R.layout.activity_raise_seekbar, null);
        // 获取布局中的控件
        final SeekBar sb = (SeekBar) view2.findViewById(R.id.sb_raise);
        final TextView tv = (TextView) view2.findViewById(R.id.tv_raise_sb);
        final Button btn = (Button) view2.findViewById(R.id.btn_raise_sb);
        // 设置参数
        builder.setTitle("下注").setView(view2);
        // 创建对话框
        final AlertDialog alertDialog = builder.create();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //String uname = username.getText().toString().trim();
                Toast.makeText(brContext, "下注！", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();// 对话框消失
            }

        });
        alertDialog.show();
    }
}
