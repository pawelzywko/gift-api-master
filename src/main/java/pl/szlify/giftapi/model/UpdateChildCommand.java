package pl.szlify.giftapi.model;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class UpdateChildCommand {
    private String name;
    private String surname;
    private LocalDate birthDay;

}
