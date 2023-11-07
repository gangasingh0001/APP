package Models;

import java.util.Random;

public enum CardType {
    /**
     * bomb card
     */
    BOMB,

    /**
     * blockade card
     */
    BLOCKADE,

    /**
     * airlift card
     */
    AIRLIFT,

    /**
     * negotiate card
     */
    NEGOTIATE;

    /**
     * This method gives a random card from the enum CardType class
     * @return a random card name
     */
    public static CardType getRandomCard(){
        Random d_random = new Random();
        CardType d_cardType = values()[d_random.nextInt(values().length)];
        return d_cardType;
    }
}
