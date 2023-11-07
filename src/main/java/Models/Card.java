package Models;

import Orders.IOrders;

/**
 * Card class which to create a card and give a random card type
 */
public class Card {
    /**
     * card type variable
     */
    private CardType d_CardType;

    /**
     * default constructor
     */
    public Card(){
        d_CardType = CardType.getRandomCard();
    }

    /**
     * constructor to create a specific card type
     * @param p_CardType
     */
    public Card(CardType p_CardType){
        d_CardType = p_CardType;
    }

    /**
     * getter method to get the card type
     * @return card type
     */
    public CardType getCardType() {
        return d_CardType;
    }

    /**
     * setter method to set a card type
     * @param p_CardType
     */
    public void setCardType(CardType p_CardType) {
        d_CardType = p_CardType;
    }

    /**
     * override method to check if the card type is equal
     * @param p_o
     * @return
     */
    @Override
    public boolean equals(Object p_o) {
        if (this == p_o) return true;
        if (p_o == null || getClass() != p_o.getClass()) return false;

        Card card = (Card) p_o;

        return d_CardType == card.d_CardType;
    }

    /**
     * override method to print card type
     * @return card type
     */
    @Override
    public String toString() {
        return "Card{" +
                "d_CardType=" + d_CardType +
                '}';
    }


}
