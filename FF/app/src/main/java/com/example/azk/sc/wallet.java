package com.example.azk.sc;

import android.content.Context;
import android.view.View;

import android.widget.SeekBar;
import android.widget.TextView;

public class wallet {
    public TextView tvMm, tvTmm, tvTam;
    public int walletMoney=2000;
    public int turnMyBetMoney;//一局内自己下注的钱
    public Context brContext;

    //新建钱包
    public void creatWallet(Context bContext) {
        walletMoney = 2000;
        brContext = bContext;
        View view2 = View.inflate(brContext, R.layout.activity_raise_seekbar, null);
        tvMm = (TextView) view2.findViewById(R.id.tv_mm);//我的钱
        tvTmm = (TextView) view2.findViewById(R.id.tv_tmm);//我本局投资
        tvTam = (TextView) view2.findViewById(R.id.tv_tam);//本局所有投资
    }

    //下注
    public void bet(int betMoney) {
        walletMoney = walletMoney - betMoney;
        turnMyBetMoney = turnMyBetMoney + betMoney;
        //tvMm.setText(String.valueOf(walletMoney));
       // tvTmm.setText(String.valueOf(turnMyBetMoney));


    }
    //结算
    public void winOrLose(int turnAllBetMoney, boolean winState) {
        if (winState) {
            walletMoney = walletMoney + turnAllBetMoney;
            turnMyBetMoney = 0;

        }

    }
}