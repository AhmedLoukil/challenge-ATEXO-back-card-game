package test.atexo.cardgame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.atexo.cardgame.model.Card;
import test.atexo.cardgame.repository.CardsRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultCardsService implements CardsService {

    private List<Card> thePlayerHand;

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public synchronized List<Card> distributes(int cardsNumber) {
        if (cardsNumber > cardsRepository.getPackOfCards().size())
            throw new IllegalArgumentException("We can't distributes more cards than we have in the pack");

        if (thePlayerHand == null)
            thePlayerHand = getRandomCards(cardsNumber);

        return thePlayerHand;
    }

    @Override
    public synchronized List<Card> orderBy(Comparator<Card> cardComparator) {
        return thePlayerHand.stream().sorted(cardComparator).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public synchronized void clearHand() {
        if (thePlayerHand != null)
            thePlayerHand = null;
    }

    /**
     * This method is used only for test
     */
    public List<Card> getThePlayerHand(){
        return thePlayerHand;
    }

    /**
     * This method randomly selects a hand of cards from the pack of cards.
     *
     * @param cardsNumber is the number of cards in player hand
     * @return random cards
     */
    private List<Card> getRandomCards(int cardsNumber) {
        if (cardsNumber <= 0)
            return List.of();

        final Random random = new Random();
        final List<Card> randomCards = new ArrayList<>();
        List<Card> packOfCards = new ArrayList<>(cardsRepository.getPackOfCards());
        for (int i = 0; i < cardsNumber; i++) {
            randomCards.add(packOfCards.remove(random.nextInt(packOfCards.size())));
        }
        return Collections.unmodifiableList(randomCards);
    }

}
