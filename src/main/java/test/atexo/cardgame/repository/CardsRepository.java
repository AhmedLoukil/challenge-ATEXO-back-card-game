package test.atexo.cardgame.repository;

import test.atexo.cardgame.model.Card;

import java.util.List;

/**
 * This interface describes a pack of cards
 */
public interface CardsRepository {

    /**
     * @return the pack of cards freshly unpacked :)
     */
    List<Card> getPackOfCards();

}
