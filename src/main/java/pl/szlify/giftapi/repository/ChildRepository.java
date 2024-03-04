package pl.szlify.giftapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szlify.giftapi.model.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
