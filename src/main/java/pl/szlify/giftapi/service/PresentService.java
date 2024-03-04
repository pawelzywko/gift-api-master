package pl.szlify.giftapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szlify.giftapi.model.Child;
import pl.szlify.giftapi.model.CreatePresentCommand;
import pl.szlify.giftapi.model.Present;
import pl.szlify.giftapi.model.PresentDto;
import pl.szlify.giftapi.repository.ChildRepository;
import pl.szlify.giftapi.repository.PresentRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PresentService {

    private final PresentRepository presentRepository;
    private final ChildRepository childRepository;


    public PresentDto addPresentToChild(Long childId, CreatePresentCommand command) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono dziecka o ID: " + childId));

        if (child.getPresents().size() < 3) {
            Present present = new Present();
            present.setName(command.getName());
            present.setPrice(command.getPrice());
            present.setChild(child);
            present = presentRepository.save(present);
            return new PresentDto(present.getId(), present.getName(), present.getPrice());
        } else {
            throw new RuntimeException("Dziecko może mieć maksymalnie 3 prezenty.");
        }
    }

    public Set<PresentDto> getPresentsForChild(Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono dziecka o ID: " + childId));

        return child.getPresents().stream()
                .map(present -> new PresentDto(present.getId(), present.getName(), present.getPrice()))
                .collect(Collectors.toSet());
    }
}
