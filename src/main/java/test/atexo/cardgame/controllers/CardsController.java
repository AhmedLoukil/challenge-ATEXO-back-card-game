package test.atexo.cardgame.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.atexo.cardgame.model.Card;
import test.atexo.cardgame.services.CardsService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * {@link CardsController} describe all cards endpoint
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200") //TODO: Add in properties
@RequestMapping("/cards")
public class CardsController {

    private static final Logger LOGGER = getLogger(CardsController.class);

    @Autowired
    private CardsService cardsService;

    /**
     * This endpoint distributes a new hand of N card, N is the cards number passed as param
     * This is called when requesting GET /cards/distributes/{Integer}
     * You can try it by running 'curl -X GET localhost:8080/cards/distributes/10'
     *
     * @param cardsNumber is the number of cards to distribute
     * @return ResponseEntity of list of cards
     */
    @GetMapping("/distributes/{cardsNumber}")
    public ResponseEntity<List<Card>> distributes(@PathVariable int cardsNumber) {
        try {
            final var playerHand = cardsService.distributes(cardsNumber);
            LOGGER.debug("Distributed cards : {}", playerHand);
            return ResponseEntity.ok(playerHand);
        } catch (RuntimeException e) {
            LOGGER.error("Error bad request :", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
        }
    }

    /**
     * This endpoint order the cards principally by color and for greater clarity we order by value.
     * This is called when requesting GET /cards/order-by-color
     * You can try it by running 'curl -X GET localhost:8080/cards/order-by-color'
     *
     * @return ResponseEntity of ordered list of cards
     */
    @GetMapping("/order-by-color")
    public ResponseEntity<List<Card>> orderByColor() {
        final var playerHand = cardsService.orderBy(Card.byColorThenByValue());
        LOGGER.debug("Ordered cards by color : {}", playerHand);
        return ResponseEntity.ok(playerHand);
    }

    /**
     * This endpoint order the cards principally by value and for greater clarity we order by color.
     * This is called when requesting GET /cards/order-by-value
     * You can try it by running 'curl -X GET localhost:8080/cards/order-by-value'
     *
     * @return ResponseEntity of ordered list of cards
     */
    @GetMapping("/order-by-value")
    public ResponseEntity<List<Card>> orderByValue() {
        final var playerHand = cardsService.orderBy(Card.byValueThenByColor());
        LOGGER.debug("Ordered cards by value : {}", playerHand);
        return ResponseEntity.ok(playerHand);
    }

    /**
     * This endpoint clear the hand to distributes a new cards
     * This is called when requesting DELETE /cards/clear-hand
     * You can try it by running 'curl -X DELETE localhost:8080/cards/clear-hand'
     */
    @DeleteMapping("/clear-hand")
    public void clearHand() {
        cardsService.clearHand();
        LOGGER.debug("Cleared player hand");
    }

}
