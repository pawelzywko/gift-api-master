package pl.szlify.giftapi.model;

import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateChildCommand {

    String name;

    String surname;

    LocalDate birthDay;
}
