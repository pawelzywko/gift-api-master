package pl.szlify.giftapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szlify.giftapi.model.CreatePresentCommand;
import pl.szlify.giftapi.model.Present;
import pl.szlify.giftapi.model.PresentDto;
import pl.szlify.giftapi.service.PresentService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/children/{childId}/presents")
@RequiredArgsConstructor
public class PresentController {


    private final PresentService presentService;

    @PostMapping
    public ResponseEntity<PresentDto> addPresentToChild(@PathVariable Long childId, @RequestBody CreatePresentCommand command) {
        PresentDto presentDto = presentService.addPresentToChild(childId, command);
        return new ResponseEntity<>(presentDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<PresentDto>> getPresentsForChild(@PathVariable Long childId) {
        Set<PresentDto> presentDtos = presentService.getPresentsForChild(childId);
        return new ResponseEntity<>(presentDtos, HttpStatus.OK);
    }
}
