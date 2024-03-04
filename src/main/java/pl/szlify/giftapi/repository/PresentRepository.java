package pl.szlify.giftapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szlify.giftapi.model.Present;

public interface PresentRepository extends JpaRepository<Present, Long> {
}
