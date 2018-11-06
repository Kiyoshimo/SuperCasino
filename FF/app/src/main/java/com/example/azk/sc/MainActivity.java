package com.example.azk.sc;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ButtonRaise MyButtonRaise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyButtonRaise = new ButtonRaise();
        MyButtonRaise.showNormalDialog();
        //showNormalDialog();
    }

//        public void showNormalDialog(){
//            /* @setIcon 设置对话框图标
//             * @setTitle 设置对话框标题
//             * @setMessage 设置对话框消息提示
//             * setXXX方法返回Dialog对象，因此可以链式设置属性
//             */
//            final AlertDialog.Builder normalDialog =
//                    new AlertDialog.Builder(MainActivity.this);
//            //normalDialog.setIcon(R.drawable.icon_dialog);
//            normalDialog.setTitle("我是一个普通Dialog");
//            normalDialog.setMessage("你要点击哪一个按钮呢?");
//            normalDialog.setPositiveButton("确定",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //...To-do
//                        }
//                    });
//            normalDialog.setNegativeButton("关闭",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //...To-do
//                        }
//                    });
//            // 显示
//            normalDialog.show();
//        }
//
    }



