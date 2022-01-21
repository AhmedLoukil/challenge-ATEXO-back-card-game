package test.atexo.cardgame.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.atexo.cardgame.model.Card;
import test.atexo.cardgame.model.CardColor;
import test.atexo.cardgame.model.CardValue;
import test.atexo.cardgame.repository.CardsRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.*;
import static test.atexo.cardgame.CardsUtils.*;

@ExtendWith(MockitoExtension.class)
class CardsServiceTest {

    @Mock
    private CardsRepository cardsRepository;

    @InjectMocks
    private DefaultCardsService cardsService;

    @Test
    void distributes_some_cards() {
        final Card card1 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1));
        final Card card2 = createCard(createColor(CardColor.CLUB, 1), createValue(CardValue.V2, 1));
        final Card card3 = createCard(createColor(CardColor.SPADES, 1), createValue(CardValue.QUEEN, 1));
        final Card card4 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.V8, 1));
        final Card card5 = createCard(createColor(CardColor.HEARTS, 1), createValue(CardValue.JACK, 1));

        final List<Card> playerHand = List.of(card1, card2, card3, card4, card5);

        when(cardsRepository.getPackOfCards()).thenReturn(playerHand);

        final List<Card> cards = cardsService.distributes(3);

        assertThat(cards).isNotNull().hasSize(3);
        // we get all cards in the pack.
        assertThat(playerHand).containsAnyElementsOf(cards);
    }

    @Test
    void distributes_all_the_pack() {
        final Card card1 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1));
        final Card card2 = createCard(createColor(CardColor.CLUB, 1), createValue(CardValue.V2, 1));
        final Card card3 = createCard(createColor(CardColor.SPADES, 1), createValue(CardValue.QUEEN, 1));
        final Card card4 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.V8, 1));
        final Card card5 = createCard(createColor(CardColor.HEARTS, 1), createValue(CardValue.JACK, 1));

        final List<Card> playerHand = List.of(card1, card2, card3, card4, card5);

        when(cardsRepository.getPackOfCards()).thenReturn(playerHand);

        final List<Card> cards = cardsService.distributes(5);

        assertThat(cards).isNotNull().hasSize(5);
        // we get all cards in the pack.
        assertThat(cards).containsExactlyInAnyOrderElementsOf(playerHand);
    }

    @Test
    void distributes_0_cards() {
        final List<Card> playerHand = List.of(createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1)));
        when(cardsRepository.getPackOfCards()).thenReturn(playerHand);
        final List<Card> cards = cardsService.distributes(0);
        assertThat(cards).isNotNull().isEmpty();
    }

    @Test
    void order_by_color() {
        final Card card1 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1));
        final Card card2 = createCard(createColor(CardColor.CLUB, 4), createValue(CardValue.V2, 2));
        final Card card3 = createCard(createColor(CardColor.SPADES, 3), createValue(CardValue.QUEEN, 12));
        final Card card4 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.V8, 8));
        final Card card5 = createCard(createColor(CardColor.HEARTS, 2), createValue(CardValue.JACK, 11));
        final Card card6 = createCard(createColor(CardColor.HEARTS, 2), createValue(CardValue.V8, 8));

        final List<Card> playerHand = List.of(card1, card2, card3, card4, card5, card6);

        when(cardsRepository.getPackOfCards()).thenReturn(playerHand);

        cardsService.distributes(6);
        final List<Card> cards = cardsService.orderBy(Card.byColorThenByValue());

        assertThat(cards).isNotNull().hasSize(6);
        assertThat(cards).containsExactly(card1, card4, card6, card5, card3, card2);
    }

    @Test
    void order_by_value() {
        final Card card1 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1));
        final Card card2 = createCard(createColor(CardColor.CLUB, 4), createValue(CardValue.V2, 2));
        final Card card3 = createCard(createColor(CardColor.SPADES, 3), createValue(CardValue.QUEEN, 12));
        final Card card4 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.V8, 8));
        final Card card5 = createCard(createColor(CardColor.HEARTS, 2), createValue(CardValue.JACK, 11));
        final Card card6 = createCard(createColor(CardColor.HEARTS, 2), createValue(CardValue.V8, 8));
        final List<Card> playerHand = List.of(card1, card2, card3, card4, card5, card6);

        when(cardsRepository.getPackOfCards()).thenReturn(playerHand);

        cardsService.distributes(6);
        final List<Card> cards = cardsService.orderBy(Card.byValueThenByColor());

        assertThat(cards).isNotNull().hasSize(6);
        assertThat(cards).containsExactly(card1, card2, card4, card6, card5, card3);
    }


    @Test
    void distributes_KO() {
        final List<Card> playerHand = List.of(createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1)));
        when(cardsRepository.getPackOfCards()).thenReturn(playerHand);
        assertThatIllegalArgumentException().isThrownBy(() -> cardsService.distributes(5));
    }

    @Test
    void clear_hand() {
        final Card card1 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1));
        final Card card2 = createCard(createColor(CardColor.CLUB, 1), createValue(CardValue.V2, 1));
        final Card card3 = createCard(createColor(CardColor.SPADES, 1), createValue(CardValue.QUEEN, 1));
        final Card card4 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.V8, 1));
        final Card card5 = createCard(createColor(CardColor.HEARTS, 1), createValue(CardValue.JACK, 1));
        final List<Card> playerHand = List.of(card1, card2, card3, card4, card5);

        when(cardsRepository.getPackOfCards()).thenReturn(playerHand);

        List<Card> cards =  cardsService.distributes(2);
        assertThat(cards).isNotNull().hasSize(2);
        cardsService.clearHand();
        assertThat(cardsService.getThePlayerHand()).isNull();
    }
}
