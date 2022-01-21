package test.atexo.cardgame;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.atexo.cardgame.confuguration.CardsProperties;
import test.atexo.cardgame.controllers.CardsController;
import test.atexo.cardgame.model.Card;
import test.atexo.cardgame.model.CardColor;
import test.atexo.cardgame.model.CardValue;
import test.atexo.cardgame.repository.CardsRepository;
import test.atexo.cardgame.services.CardsService;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CardGameApplicationTests {

    @Test
    void main() {
        assertDoesNotThrow((Executable) CardGameApplication::main);
    }

    @Test
    void testContext() {
        try (final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(CardGameApplication.class)) {
            assertDoesNotThrow(() -> context.getBean(CardsService.class));
            assertDoesNotThrow(() -> context.getBean(CardsRepository.class));
            assertDoesNotThrow(() -> context.getBean(CardsController.class));
            assertDoesNotThrow(() -> context.getBean(CardsProperties.class));

            final CardsRepository cardsRepository = context.getBean(CardsRepository.class);
            final List<Card> allCards = cardsRepository.getPackOfCards();
            assertThat(allCards).hasSize(52);
            assertThat(allCards.stream().filter(isColor(CardColor.DIAMONDS)).count()).isEqualTo(13);
            assertThat(allCards.stream().filter(isColor(CardColor.HEARTS)).count()).isEqualTo(13);
            assertThat(allCards.stream().filter(isColor(CardColor.CLUB)).count()).isEqualTo(13);
            assertThat(allCards.stream().filter(isColor(CardColor.SPADES)).count()).isEqualTo(13);

            assertThat(allCards.stream().filter(isValue(CardValue.AS)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V2)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V3)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V4)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V5)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V6)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V7)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V8)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V9)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.V10)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.JACK)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.QUEEN)).count()).isEqualTo(4);
            assertThat(allCards.stream().filter(isValue(CardValue.KING)).count()).isEqualTo(4);
        }
    }

    @NotNull
    private Predicate<Card> isColor(CardColor cardColor) {
        return card -> card.getColor().getCardColor().equals(cardColor);
    }

    @NotNull
    private Predicate<Card> isValue(CardValue cardValue) {
        return card -> card.getValue().getCardValue().equals(cardValue);
    }
}
