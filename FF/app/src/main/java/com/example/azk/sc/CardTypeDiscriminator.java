package com.example.azk.sc;

import java.util.ArrayList;
import java.util.Collections;

public class CardTypeDiscriminator {
    private ArrayList<String> ccCard;
    private ArrayList<String> playerCard;
    private ArrayList<String> com1Card;
    private int [] color_num = {0,0,0,0};
    private int [] number_num = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int triple = 0;
    private int pair = 0;
    private int pHighCard = 0;
    private int c1HighCard = 0;
    private int playerStr = 0;
    private int com1Str = 0;
    private ArrayList<String> tmp_all;
    private boolean flag = false;

    public int [] getColorNum(){
        return color_num;
    }
    public void setCard(ArrayList<String> cc, ArrayList<String> pc){
        //setAllCard(cc);
        ccCard = cc;
        playerCard = pc;
    }
    public void setComCard(ArrayList<String> comcc){
        com1Card = comcc;

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
        pHighCard = 0;
        c1HighCard = 0;
        playerStr = 0;
        com1Str = 0;
        /*if(flag) {
            tmp_all.clear();
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
        flag = true;
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
                    playerStr = 10;
                    return "皇家同花順(Royal Straight Flush)";
                }
                for(int k = 0; k < 9; k++){
                    if(number_num[k] == 1 && number_num[k+1] == 1 && number_num[k+2] == 1
                            && number_num[k+3] == 1 && number_num[k+4] == 1){
                        playerStr = 9;
                        return "同花順(Straight Flush)";
                    }
                }
                playerStr = 6;
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
                case 4: playerStr = 8; return "鐵支(Four Of A Kind)";
                case 3:
                    triple += 1;
                    break;
                case 2:
                    pair += 1;
                    break;
                default:
                    pHighCard = k+1;
                    break;
            }
        }
        if(triple >= 1){
            if(pair >= 1){
                playerStr = 7;
                return "葫蘆(Full House)";
            }else{
                playerStr = 4;
                return "三條(Triple)";
            }
        }else{
            if(number_num[9] >= 1 && number_num[10] >= 1 && number_num[11] >= 1
                    && number_num[12] >= 1 && number_num[0] >= 1) {
                playerStr = 5;
                return "順子(Straight)";
            }
            for(int k = 0; k < 9; k++){
                if(number_num[k] >= 1 && number_num[k+1] >= 1 && number_num[k+2] >= 1
                        && number_num[k+3] >= 1 && number_num[k+4] >= 1){
                    playerStr = 5;
                    return "順子(Straight)";
                }
            }
            if(pair >= 2){
                playerStr = 3;
                return "兩對(Two Pair)";
            }else if(pair == 1){
                playerStr = 2;
                return "一對(One Pair)";
            }else{
                playerStr = 1;
                return "散牌(High card)";
            }
        }
    }
    public int discriminateWithCom(){
        triple = 0;
        pair = 0;
        for(int a=0; a<4; a++) {
            color_num[a] = 0;
        }
        for(int b=0; b<13; b++) {
            number_num[b] = 0;
        }

        ArrayList<String> all_card2 = ccCard;
        all_card2.add(com1Card.get(0));
        all_card2.add(com1Card.get(1));
        ArrayList<String> card_color = new ArrayList<>();
        ArrayList<String> card_number = new ArrayList<>();
        flag = true;
        //先整理花色
        for(int i = 0; i < all_card2.size(); i++){
            String cardColor = all_card2.get(i).substring(0,1);
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
            String cardNumber = all_card2.get(i).substring(1);
            card_number.add(cardNumber);
        }
        //若大於等於五張牌花色相同，則定義為同花
        for(int j = 0; j < 4; j++){
            if(color_num[j] >= 5){
                if(number_num[9] == 1 && number_num[10] == 1 && number_num[11] == 1
                        && number_num[12] == 1 && number_num[0] == 1) {
                    com1Str = 10;

                }
                for(int k = 0; k < 9; k++){
                    if(number_num[k] == 1 && number_num[k+1] == 1 && number_num[k+2] == 1
                            && number_num[k+3] == 1 && number_num[k+4] == 1){
                        com1Str = 9;

                    }
                }
                com1Str = 6;
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
                case 4: com1Str = 8;
                case 3:
                    triple += 1;
                    break;
                case 2:
                    pair += 1;
                    break;
                default:
                    c1HighCard = k+1;
                    break;
            }
        }
        if(triple >= 1){
            if(pair >= 1){
                com1Str = 7;
            }else {
                com1Str = 4;
            }
        }else{
            if(number_num[9] >= 1 && number_num[10] >= 1 && number_num[11] >= 1
                    && number_num[12] >= 1 && number_num[0] >= 1) {
                com1Str = 5;
            }
            for(int k = 0; k < 9; k++){
                if(number_num[k] >= 1 && number_num[k+1] >= 1 && number_num[k+2] >= 1
                        && number_num[k+3] >= 1 && number_num[k+4] >= 1){
                    com1Str = 5;
                }
            }
            if(pair >= 2){
                com1Str = 3;
            }else if(pair == 1){
                com1Str = 2;
            }else{
                com1Str = 1;
            }
        }
        if(playerStr > com1Str){
            return 2;
        }else if(playerStr == com1Str){
            if(playerStr == 1){
                if(pHighCard > c1HighCard){
                    return 2;
                }else if(pHighCard == c1HighCard){
                    return 1;
                }else{
                    return 0;
                }
            }else {
                return 1;
            }
        }else{
            return 0;
        }
    }
    public int[] getStr(){
        int[] res = {playerStr, com1Str};
        return res;
    }
    public void setAllCard(ArrayList<String> alcd){
        tmp_all = alcd;
    }
    public ArrayList<String> getAllCard(){
        return tmp_all;
    }
    public int getSize(){
        return tmp_all.size();
    }
}
