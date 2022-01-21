package test.atexo.cardgame.repository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;
import test.atexo.cardgame.confuguration.CardsProperties;
import test.atexo.cardgame.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class InMemoryCardsRepository implements CardsRepository, SmartLifecycle {

    private static final Logger LOGGER = getLogger(InMemoryCardsRepository.class);

    @Autowired
    private CardsProperties cardsProperties;

    private boolean running;
    private List<Card> packOfCards;

    @Override
    public List<Card> getPackOfCards() {
        return packOfCards;
    }

    @Override
    public void start() {
        running = true;
        // we create all cards, 4 colors for each value => 4 * 13 = 52 cards.
        packOfCards = Arrays.stream(CardValue.values())
                .flatMap(cardValue -> Arrays.stream(CardColor.values())
                        .map(cardColor -> createCard(cardValue, cardColor)))
                .collect(Collectors.toUnmodifiableList());
        LOGGER.debug("New pack of cards : {}", packOfCards);
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private Card createCard(CardValue cardValue, CardColor cardColor) {
        return Card.builder()
                .withColor(Color.builder()
                        .withColor(cardColor)
                        .withOrder(cardsProperties.getColorOrders().get(cardColor.name()))
                        .build())
                .withValue(Value.builder()
                        .withValue(cardValue)
                        .withOrder(cardsProperties.getValueOrders().get(cardValue.name()))
                        .build())
                .build();
    }
}
