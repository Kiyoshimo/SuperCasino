package com.example.azk.sc;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button btnFold,btnCheck,btnRaise;
    private ImageView ivCC1,ivCC2,ivCC3,ivCC4,ivCC5,ivHC1,ivHC2 ;
    private TextView tvMm,tvTmm,tvTam;
    private TextView tvTurn, tvCheck, tvCardType, tvChange;


    private DeckController deckController;
    private int turn_number;
    private boolean all_check, result_flag;
    private ArrayList<String> playerCard;

    //广播
    private PendingIntent pendingIntent;
    private Context context;
    private String CONTENT = "HELLO MY MESSAGE",PHONENUMBER="0978550131";

    //广播
    ArrayList<String> ccCard;
    CardTypeDiscriminator ctd;

    public wallet playerWallet;//玩家钱包
    public  int bonusPool;//每轮奖金池


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    //初始化
    protected void init(){
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
        tvMm=(TextView) findViewById(R.id.tv_mm);
        tvTmm=(TextView) findViewById(R.id.tv_tmm);
        tvTam=(TextView) findViewById(R.id.tv_tam);
        tvChange = (TextView) findViewById(R.id.tv_change);     //當局資金變化
        //其餘TextView們
        tvTurn = (TextView) findViewById(R.id.tv_turn);
        tvCheck = (TextView) findViewById(R.id.tv_check);
        tvCardType = (TextView) findViewById(R.id.tv_cardtype);
        //牌組控制器
        deckController = new DeckController();
        playerCard = new ArrayList<>();
        ccCard = new ArrayList<>();
        ctd = new CardTypeDiscriminator();
        //錢包
        playerWallet=new wallet();
        playerWallet.creatWallet(this);
        bonusPool=0;
        result_flag = false;
        resetGame();

    }

    public void resetGame(){
        int imageResource = getResources().getIdentifier("@drawable/jb", null, getPackageName());
        ivCC1.setImageResource(imageResource);
        ivCC2.setImageResource(imageResource);
        ivCC3.setImageResource(imageResource);
        ivCC4.setImageResource(imageResource);
        ivCC5.setImageResource(imageResource);
        ccCard.clear();
        playerCard.clear();
        deckController.resetDeck();
        String p1Card = deckController.drawCard();
        String connect = "@drawable/" + p1Card;
        imageResource = getResources().getIdentifier(connect, null, getPackageName());
        ivHC1.setImageResource(imageResource);
        String p2Card = deckController.drawCard();
        connect = "@drawable/" + p2Card;
        imageResource = getResources().getIdentifier(connect, null, getPackageName());
        ivHC2.setImageResource(imageResource);
        turn_number = 0;
        all_check = false;
        tvTurn.setText(String.valueOf(turn_number));
        tvCheck.setText(String.valueOf(all_check));
        playerCard.add(p1Card);
        playerCard.add(p2Card);
        ctd.resetDiscriminator();
        tvCardType.setText("");

        //金钱初始化
        bonusPool=0;
        tvTam.setText(String.valueOf(bonusPool));
        tvTmm.setText(String.valueOf(playerWallet.turnMyBetMoney)+"元");
        tvMm.setText(String.valueOf(playerWallet.walletMoney)+"元");
    }

    interface myListener{
        public void refreshActivity(String text);
    }

    //下注按钮ButtonRaise[android:onClick="ButtonRaise"]
    public void ButtonRaise(View view) {
        ButtonRaise MyButtonRaise=new ButtonRaise();
        MyButtonRaise.getContext(this);
         MyButtonRaise.showNormalDialog();
        //boolean tmp = MyButtonRaise.showBtnS();
        if(!all_check)  {
                turn_number += 1;
                all_check = true;
                tvCheck.setText(String.valueOf(all_check));
            if(turn_number <= 3) {
                playerWallet.bet(10);//下注十块，测试用
                bonusPool = bonusPool + 10;
            }
        }
        if(all_check) {
            if (turn_number == 1) {
                tvChange.setText("0");
                turnStart(1);
            } else if (turn_number == 2) {
                turnStart(2);
            } else if (turn_number == 3) {
                turnStart(3);
                tvTam.setText(String.valueOf(bonusPool) + "元");
                tvTmm.setText(String.valueOf(playerWallet.turnMyBetMoney) + "元");
                tvMm.setText(String.valueOf(playerWallet.walletMoney) + "元");
                //最後一輪翻牌後即判斷勝負，錢也在此計算
                result_flag = true;
                playerWallet.winOrLose(bonusPool,result_flag);//输赢
                if(result_flag)
                    tvChange.setText("+" + String.valueOf(bonusPool));
                else
                    tvChange.setText("-" + String.valueOf(bonusPool));
            } else {
                tvChange.setText("0");
                resetGame();
            }
        }
        all_check = false;
        tvTurn.setText(String.valueOf(turn_number));
        tvCheck.setText(String.valueOf(all_check));
        //playerWallet.bet(MyButtonRaise.M);//下注十块，测试用
        //bonusPool=bonusPool+MyButtonRaise.M;
        if(turn_number != 3) {
            tvTam.setText(String.valueOf(bonusPool) + "元");
            tvTmm.setText(String.valueOf(playerWallet.turnMyBetMoney) + "元");
            tvMm.setText(String.valueOf(playerWallet.walletMoney) + "元");
        }
    }
    //弃牌按钮ButtonFold[android:onClick="ButtonFold"]
    public void ButtonFold(View view) {
        ButtonFold MyButtonFold=new ButtonFold();
        deckController.resetDeck();
        if(turn_number < 3) {
            result_flag = false;
            tvChange.setText("-" + String.valueOf(bonusPool));
            playerWallet.winOrLose(bonusPool, result_flag);
        }else{
            tvChange.setText("0");
        }
        resetGame();
    }
    //看牌按钮ButtonCheck[android:onClick="ButtonCheck"]
    public void ButtonCheck(View view) {
        //ButtonCheck MyButtonCheck=new ButtonCheck();
        //String cc1Card, cc2Card, cc3Card, cc4Card, cc5Card;
        //int imageResource;
        if(!all_check){
            turn_number += 1;
            all_check = true;
        }
        if(all_check) {
            if (turn_number == 1) {
                tvChange.setText("0");
                turnStart(1);
            } else if (turn_number == 2) {
                turnStart(2);
            } else if (turn_number == 3) {
                turnStart(3);
                //最後一輪翻牌後即判斷勝負，錢也在此計算
                result_flag = true;
                playerWallet.winOrLose(bonusPool,result_flag);//输赢
                if(result_flag)
                    tvChange.setText("+" + String.valueOf(bonusPool));
                else
                    tvChange.setText("-" + String.valueOf(bonusPool));
            } else {
                tvChange.setText("0");
                resetGame();
            }
        }
        all_check = false;
        tvTurn.setText(String.valueOf(turn_number));
        tvCheck.setText(String.valueOf(all_check));
        }

        public void turnStart(int tn){
            int imageResource;
            if(tn == 1){
                String cc1Card, cc2Card, cc3Card;
                cc1Card = deckController.drawCard();
                cc2Card = deckController.drawCard();
                cc3Card = deckController.drawCard();
                String connect = "@drawable/" + cc1Card;
                imageResource = getResources().getIdentifier(connect, null, getPackageName());
                ivCC1.setImageResource(imageResource);
                connect = "@drawable/" + cc2Card;
                imageResource = getResources().getIdentifier(connect, null, getPackageName());
                ivCC2.setImageResource(imageResource);
                connect = "@drawable/" + cc3Card;
                imageResource = getResources().getIdentifier(connect, null, getPackageName());
                ivCC3.setImageResource(imageResource);
                ccCard.add(cc1Card);
                ccCard.add(cc2Card);
                ccCard.add(cc3Card);
            }else if(tn == 2){
                String cc4Card;
                cc4Card = deckController.drawCard();
                String connect = "@drawable/" + cc4Card;
                imageResource = getResources().getIdentifier(connect, null, getPackageName());
                ivCC4.setImageResource(imageResource);
                ccCard.add(cc4Card);
            }else if(tn == 3){
                String cc5Card;
                cc5Card = deckController.drawCard();
                String connect = "@drawable/" + cc5Card;
                imageResource = getResources().getIdentifier(connect, null, getPackageName());
                ivCC5.setImageResource(imageResource);
                ccCard.add(cc5Card);
                ctd.setCard(ccCard,playerCard);
                String result = ctd.discriminate();
                tvCardType.setText(result);
            }else{

            }
        }


    }





