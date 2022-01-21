package test.atexo.cardgame.model;

import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * This class is a card color and its order in owr card game
 */
public class Color implements Comparable<Color> {

    public static Builder builder() {
        return new Builder();
    }

    private final CardColor cardColor;
    private final Integer order;

    private Color(CardColor cardColor, Integer order) {
        this.cardColor = Objects.requireNonNull(cardColor, "cardColor is null");
        this.order = Objects.requireNonNull(order, "order is null");
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Color{" +
                "cardColor=" + cardColor +
                ", order=" + order +
                '}';
    }

    @Override
    public int compareTo(@NonNull Color color) {
        return this.order.compareTo(color.getOrder());
    }

    public static class Builder {
        private CardColor cardColor;
        private Integer order;

        public Builder withColor(CardColor cardColor) {
            this.cardColor = cardColor;
            return this;
        }

        public Builder withOrder(Integer order) {
            this.order = order;
            return this;
        }

        public Color build() {
            return new Color(cardColor, order);
        }
    }
}
