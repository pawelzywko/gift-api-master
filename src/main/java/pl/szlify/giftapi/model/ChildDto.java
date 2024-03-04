package pl.szlify.giftapi.model;

import java.time.LocalDate;

public record ChildDto(Long id, String name, String surname, LocalDate birthDay) {
}
