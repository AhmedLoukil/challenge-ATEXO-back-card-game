package test.atexo.cardgame.confuguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a card properties to get the cards order from application.yml
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "card")
public class CardsProperties {

    private Map<String, Integer> colorOrders = new HashMap<>();
    private Map<String, Integer> valueOrders = new HashMap<>();

    {
        // Default configuration for tests
        colorOrders.put("DIAMONDS", 1);
        colorOrders.put("HEARTS", 1);
        colorOrders.put("SPADES", 1);
        colorOrders.put("CLUB", 1);

        valueOrders.put("AS", 1);
        valueOrders.put("V2", 1);
        valueOrders.put("V3", 1);
        valueOrders.put("V4", 1);
        valueOrders.put("V5", 1);
        valueOrders.put("V6", 1);
        valueOrders.put("V7", 1);
        valueOrders.put("V8", 1);
        valueOrders.put("V9", 1);
        valueOrders.put("V10", 1);
        valueOrders.put("JACK", 1);
        valueOrders.put("QUEEN", 1);
        valueOrders.put("KING", 1);
    }

    public Map<String, Integer> getColorOrders() {
        return colorOrders;
    }

    public void setColorOrders(Map<String, Integer> colorOrders) {
        this.colorOrders = colorOrders;
    }

    public Map<String, Integer> getValueOrders() {
        return valueOrders;
    }

    public void setValueOrders(Map<String, Integer> valueOrders) {
        this.valueOrders = valueOrders;
    }
}
