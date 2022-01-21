package test.atexo.cardgame;

import test.atexo.cardgame.model.*;

public class CardsUtils {

    public static Card createCard(Color color, Value value) {
        return Card.builder()
                .withColor(color)
                .withValue(value)
                .build();
    }

    public static Value createValue(CardValue cardValue, int order) {
        return Value.builder()
                .withValue(cardValue)
                .withOrder(order)
                .build();
    }

    public static Color createColor(CardColor cardColor, int order) {
        return Color.builder()
                .withColor(cardColor)
                .withOrder(order)
                .build();
    }
}
