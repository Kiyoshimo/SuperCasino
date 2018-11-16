package com.example.azk.sc;

import java.util.ArrayList;
import java.util.Collections;

public class CardTypeDiscriminator {
    private ArrayList<String> ccCard;
    private ArrayList<String> playerCard;
    private int [] color_num = {0,0,0,0};
    private int [] number_num = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int triple = 0;
    private int pair = 0;
    private int highCard = 0;

    public int [] getColorNum(){
        return color_num;
    }
    public void setCard(ArrayList<String> cc, ArrayList<String> pc){
        ccCard = cc;
        playerCard = pc;
    }
    public void resetDiscriminator(){
        for(int i = 0; i < 4; i++) {
            color_num[i] = 0;
        }
        for(int j = 0; j < 13; j++) {
            number_num[j] = 0;
        }
        triple = 0;
        pair = 0;
        highCard = 0;
        /*if(c == 2) {
            ccCard.clear();
            playerCard.clear();
        }*/
    }
    public String discriminate(){
        ArrayList<String> all_card = ccCard;
        all_card.add(playerCard.get(0));
        all_card.add(playerCard.get(1));
        ArrayList<String> card_color = new ArrayList<>();
        ArrayList<String> card_number = new ArrayList<>();
        //先整理花色
        for(int i = 0; i < all_card.size(); i++){
            String cardColor = all_card.get(i).substring(0,1);
            card_color.add(cardColor);
            switch(cardColor){
                case "c":
                    color_num[0] += 1;
                    break;
                case "d":
                    color_num[1] += 1;
                    break;
                case "h":
                    color_num[2] += 1;
                    break;
                case "s":
                    color_num[3] += 1;
                    break;
            }
            String cardNumber = all_card.get(i).substring(1);
            card_number.add(cardNumber);
        }
        //若大於等於五張牌花色相同，則定義為同花
        for(int j = 0; j < 4; j++){
            if(color_num[j] >= 5){
                if(number_num[9] == 1 && number_num[10] == 1 && number_num[11] == 1
                        && number_num[12] == 1 && number_num[0] == 1) {
                    return "皇家同花順(Royal Straight Flush)";
                }
                for(int k = 0; k < 9; k++){
                    if(number_num[k] == 1 && number_num[k+1] == 1 && number_num[k+2] == 1
                            && number_num[k+3] == 1 && number_num[k+4] == 1){
                        return "同花順(Straight Flush)";
                    }
                }
                return "同花(Flush)";
            }
        }
        //再來將牌的數字排序整理，判斷其餘牌型
        Collections.sort(card_number);
        for(int j = 0; j < 7; j++){
            int tmp = Integer.parseInt(card_number.get(j));
            number_num[tmp-1] += 1;
        }
        for(int k = 0; k < 13; k++){
            switch(number_num[k]){
                case 4: return "鐵支(Four Of A Kind)";
                case 3:
                    triple += 1;
                    break;
                case 2:
                    pair += 1;
                    break;
                default:
                    highCard = k+1;
                    break;
            }
        }
        if(triple >= 1){
            if(pair >= 1){
                return "葫蘆(Full House)";
            }else{
                return "三條(Triple)";
            }
        }else{
            if(number_num[9] >= 1 && number_num[10] >= 1 && number_num[11] >= 1
                    && number_num[12] >= 1 && number_num[0] >= 1) {
                return "順子(Straight)";
            }
            for(int k = 0; k < 9; k++){
                if(number_num[k] >= 1 && number_num[k+1] >= 1 && number_num[k+2] >= 1
                        && number_num[k+3] >= 1 && number_num[k+4] >= 1){
                    return "順子(Straight)";
                }
            }
            if(pair >= 2){
                return "兩對(Two Pair)";
            }else if(pair == 1){
                return "一對(One Pair)";
            }else{
                return "散牌(High card)";
            }
        }
    }

}
