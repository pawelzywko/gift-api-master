package pl.szlify.giftapi.service;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szlify.giftapi.controller.ChildController;
import pl.szlify.giftapi.model.Child;
import pl.szlify.giftapi.model.ChildDto;
import pl.szlify.giftapi.model.CreateChildCommand;
import pl.szlify.giftapi.model.Present;
import pl.szlify.giftapi.model.UpdateChildCommand;
import pl.szlify.giftapi.repository.ChildRepository;
import pl.szlify.giftapi.repository.PresentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final PresentRepository presentRepository;

    @Transactional
    public ChildDto create(CreateChildCommand command) {
        Child newChild = new Child(command.getName(), command.getSurname(), command.getBirthDay());
        Child savedChild = childRepository.save(newChild);
        return new ChildDto(savedChild.getId(), savedChild.getName(), savedChild.getSurname(), savedChild.getBirthDay());
    }

    @Transactional
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    public Child getChildById(Long id) {
        return childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dziecko o ID " + id + " nie znaleziono."));
    }

    @Transactional
    public ChildDto updateChild(Long childId, UpdateChildCommand command) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new IllegalStateException("Dziecko o ID " + childId + " nie istnieje."));

        child.setName(command.getName());
        child.setSurname(command.getSurname());
        child.setBirthDay(command.getBirthDay());

        Child updatedChild = childRepository.save(child);

        return new ChildDto(updatedChild.getId(), updatedChild.getName(), updatedChild.getSurname(), updatedChild.getBirthDay());
    }

    public void deleteChild(Long id) {
        if (!childRepository.existsById(id)) {
            throw new IllegalStateException("Dziecko o ID " + id + " nie istnieje.");
        }
        childRepository.deleteById(id);
    }

}
