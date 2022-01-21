package test.atexo.cardgame.model;

import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * This class is a card value and its order in owr card game
 */
public class Value implements Comparable<Value> {

    public static Builder builder() {
        return new Builder();
    }

    private final CardValue cardValue;
    private final Integer order; // TODO: to be improved with an abstract class

    private Value(CardValue cardValue, Integer order) {
        this.cardValue = Objects.requireNonNull(cardValue, "cardValue is null");
        this.order = Objects.requireNonNull(order, "order is null");
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public Integer getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Value{" +
                "cardValue=" + cardValue +
                ", order=" + order +
                '}';
    }

    @Override
    public int compareTo(@NonNull Value value) {
        return this.order.compareTo(value.getOrder());
    }

    public static class Builder {
        private CardValue cardValue;
        private Integer order;

        public Builder withValue(CardValue cardValue) {
            this.cardValue = cardValue;
            return this;
        }

        public Builder withOrder(Integer order) {
            this.order = order;
            return this;
        }

        public Value build() {
            return new Value(cardValue, order);
        }
    }
}
