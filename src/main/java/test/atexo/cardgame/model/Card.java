package test.atexo.cardgame.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * This class describe the card model
 */
public class Card {

    public static Builder builder() {
        return new Builder();
    }

    private final Color color;
    private final Value value;


    private Card(Color color, Value value) {
        this.color = Objects.requireNonNull(color, "color is null");
        this.value = Objects.requireNonNull(value, "value is null");
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", value=" + value +
                '}';
    }

    public static class Builder {
        private Color color;
        private Value value;

        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder withValue(Value value) {
            this.value = value;
            return this;
        }

        public Card build() {
            return new Card(color, value);
        }
    }

    public static int compareByValue(@NonNull Card card1,@NonNull Card card2) {
        return card1.getValue().compareTo(card2.getValue());
    }

    public static int compareByColor(@NonNull Card card1,@NonNull Card card2) {
        return card1.getColor().compareTo(card2.getColor());
    }

    public static Comparator<Card> byValueThenByColor() {
        final Comparator<Card> byValue = Card::compareByValue;
        return byValue.thenComparing(Card::compareByColor);
    }

    public static Comparator<Card> byColorThenByValue() {
        final Comparator<Card> byColor = Card::compareByColor;
        return byColor.thenComparing(Card::compareByValue);
    }

}
