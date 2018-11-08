package com.example.azk.sc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*  Version: 0.4.0
* 判斷牌型功能完成
* 但重複遊玩約六到八局會閃退
* 問題出在某些數值未重置，待解決
* */
public class MainActivity extends AppCompatActivity {
    private Button btnFold,btnCheck,btnRaise;
    private ImageView ivCC1,ivCC2,ivCC3,ivCC4,ivCC5,ivHC1,ivHC2 ;
    private TextView tv1,tv2,tv3;
    private TextView tvTurn, tvCheck, tvCardType;
    private DeckController deckController;
    private int turn_number;
    private boolean all_check;
    private ArrayList<String> playerCard;
    ArrayList<String> ccCard;
    CardTypeDiscriminator ctd;

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
        tv1=(TextView) findViewById(R.id.tv_tm2);
        tv2=(TextView) findViewById(R.id.tv_tm4);
        tv3=(TextView) findViewById(R.id.tv_tm5);
        //其餘TextView們
        tvTurn = (TextView) findViewById(R.id.tv_turn);
        tvCheck = (TextView) findViewById(R.id.tv_check);
        tvCardType = (TextView) findViewById(R.id.tv_cardtype);
        //牌組控制器
        deckController = new DeckController();
        playerCard = new ArrayList<>();
        ccCard = new ArrayList<>();
        ctd = new CardTypeDiscriminator();
        resetGame(0);
    }

    public void resetGame(int c){
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
        ctd.resetDiscriminator(c);
        tvCardType.setText("");
    }

    //下注按钮ButtonRaise[android:onClick="ButtonRaise"]
    public void ButtonRaise(View view) {
        ButtonRaise MyButtonRaise=new ButtonRaise();
        //MyButtonRaise.getContext(this);
        //MyButtonRaise.showNormalDialog();
        if(!all_check) {
            turn_number += 1;
            all_check = true;
        }
        tvCheck.setText(String.valueOf(all_check));
    }
    //弃牌按钮ButtonFold[android:onClick="ButtonRaise"]
    public void ButtonFold(View view) {
        ButtonFold MyButtonFold=new ButtonFold();
        deckController.resetDeck();
        resetGame(1);
    }
    //弃牌按钮ButtonCheck[android:onClick="ButtonRaise"]
    public void ButtonCheck(View view) {
        ButtonCheck MyButtonCheck=new ButtonCheck();
        String cc1Card, cc2Card, cc3Card, cc4Card, cc5Card;

        if(all_check) {
            if (turn_number == 1) {
                cc1Card = deckController.drawCard();
                cc2Card = deckController.drawCard();
                cc3Card = deckController.drawCard();
                String connect = "@drawable/" + cc1Card;
                int imageResource = getResources().getIdentifier(connect, null, getPackageName());
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
            } else if (turn_number == 2) {
                cc4Card = deckController.drawCard();
                String connect = "@drawable/" + cc4Card;
                int imageResource = getResources().getIdentifier(connect, null, getPackageName());
                ivCC4.setImageResource(imageResource);
                ccCard.add(cc4Card);
            } else if (turn_number == 3) {
                cc5Card = deckController.drawCard();
                String connect = "@drawable/" + cc5Card;
                int imageResource = getResources().getIdentifier(connect, null, getPackageName());
                ivCC5.setImageResource(imageResource);
                ccCard.add(cc5Card);
                ctd.setCard(ccCard,playerCard);
                String result = ctd.discriminate();
                tvCardType.setText(result);
            } else {
                resetGame(2);
            }
        }
        all_check = false;
        tvTurn.setText(String.valueOf(turn_number));
        tvCheck.setText(String.valueOf(all_check));
    }

    }



