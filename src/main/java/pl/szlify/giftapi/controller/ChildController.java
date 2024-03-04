package pl.szlify.giftapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szlify.giftapi.model.Child;
import pl.szlify.giftapi.model.ChildDto;
import pl.szlify.giftapi.model.CreateChildCommand;
import pl.szlify.giftapi.model.UpdateChildCommand;
import pl.szlify.giftapi.service.ChildService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/children")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping
    public ResponseEntity<ChildDto> create(@RequestBody CreateChildCommand command) {
        ChildDto newChildDto = childService.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(newChildDto);
    }

    @GetMapping
    public ResponseEntity<List<Child>> getAllChildren() {
        List<Child> children = childService.getAllChildren();
        return new ResponseEntity<>(children, HttpStatus.OK);
    }

    @GetMapping("/{childId}")
    public ResponseEntity<Child> getChildById(@PathVariable Long childId) {
        Child child = childService.getChildById(childId);
        return new ResponseEntity<>(child, HttpStatus.OK);
    }

    @PutMapping("/{childId}")
    public ResponseEntity<ChildDto> updateChild(@PathVariable Long childId, @RequestBody UpdateChildCommand command) {
        ChildDto updatedChildDto = childService.updateChild(childId, command);
        return new ResponseEntity<>(updatedChildDto, HttpStatus.OK);
    }

    @DeleteMapping("/{childId}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long childId) {
        childService.deleteChild(childId);
        return ResponseEntity.noContent().build();
    }
}
