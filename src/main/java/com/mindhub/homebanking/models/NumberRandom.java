package com.mindhub.homebanking.models;

public class NumberRandom {
        public static int getRandomNumber(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }
        public static String getCardNumber (int min, int max){
            return  getRandomNumber(min, max) + "-" + getRandomNumber(min, max) + "-" + getRandomNumber(min, max) + "-" + getRandomNumber(min, max);
        }
}
