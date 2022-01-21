package test.atexo.cardgame.services;

import test.atexo.cardgame.model.Card;

import java.util.Comparator;
import java.util.List;

/**
 * This interface describes the cards service, we handel only one player hand in this challenge.
 */
public interface CardsService {

    /**
     * This method distributes N cards number to the player.
     *
     * @param cardsNumber is the number of cards to distribute
     * @return the hand distributed
     */
    List<Card> distributes(int cardsNumber);

    /**
     * This method order the player hand cards by using the comparator passed as param
     *
     * @param cardComparator the comparator to use for sort
     * @return ordered cards
     */
    List<Card> orderBy(Comparator<Card> cardComparator);

    /**
     * This method clear the player hand at the end of the game.
     */
    void clearHand();
}
