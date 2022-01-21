package test.atexo.cardgame.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.atexo.cardgame.model.*;
import test.atexo.cardgame.services.CardsService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static test.atexo.cardgame.CardsUtils.*;

@ExtendWith(MockitoExtension.class)
class CardsControllerTest {

    @Mock
    private CardsService cardsService;

    @InjectMocks
    private CardsController cardsController;

    @Test
    void distributes_OK() {

        final Card card1 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1));
        final Card card2 = createCard(createColor(CardColor.CLUB, 1), createValue(CardValue.V2, 1));
        final Card card3 = createCard(createColor(CardColor.SPADES, 1), createValue(CardValue.QUEEN, 1));
        final Card card4 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.V8, 1));
        final Card card5 = createCard(createColor(CardColor.HEARTS, 1), createValue(CardValue.JACK, 1));

        final List<Card> playerHand = List.of(card1, card2, card3, card4, card5);

        when(cardsService.distributes(eq(5))).thenReturn(playerHand);

        final ResponseEntity<List<Card>> response = cardsController.distributes(5);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).hasSize(5).isEqualTo(playerHand);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void distributes_KO() {
        when(cardsService.distributes(eq(5))).thenThrow(IllegalArgumentException.class);

        final ResponseEntity<List<Card>> response = cardsController.distributes(5);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).hasSize(0);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void order_by() {

        final Card card1 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.AS, 1));
        final Card card2 = createCard(createColor(CardColor.CLUB, 1), createValue(CardValue.V2, 1));
        final Card card3 = createCard(createColor(CardColor.SPADES, 1), createValue(CardValue.QUEEN, 1));
        final Card card4 = createCard(createColor(CardColor.DIAMONDS, 1), createValue(CardValue.V8, 1));
        final Card card5 = createCard(createColor(CardColor.HEARTS, 1), createValue(CardValue.JACK, 1));

        final List<Card> playerHand = List.of(card1, card2, card3, card4, card5);

        when(cardsService.orderBy(any())).thenReturn(playerHand);

        ResponseEntity<List<Card>> response = cardsController.orderByColor();

        assertThat(response).isNotNull();
        assertThat(response.getBody()).hasSize(5).isEqualTo(playerHand);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = cardsController.orderByValue();

        assertThat(response).isNotNull();
        assertThat(response.getBody()).hasSize(5).isEqualTo(playerHand);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(cardsService, times(2)).orderBy(any());
    }

    @Test
    void clear_hand() {
        cardsController.clearHand();
        verify(cardsService, only()).clearHand();
    }
}
