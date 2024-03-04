package pl.szlify.giftapi.model;

import lombok.Value;

import java.math.BigDecimal;
@Value
public class CreatePresentCommand {

    private String name;
    private Double price;
}
