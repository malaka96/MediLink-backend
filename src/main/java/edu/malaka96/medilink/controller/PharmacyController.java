package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.PharmacyAlreadyExistsException;
import edu.malaka96.medilink.exception.PharmacyNotFoundForUserException;
import edu.malaka96.medilink.exception.UserNotFoundException;
import edu.malaka96.medilink.model.dto.PharmacyRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyResponseDto;
import edu.malaka96.medilink.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @PostMapping
    public ResponseEntity<PharmacyResponseDto> createPharmacy(@RequestBody PharmacyRequestDto pharmacyRequestDto,
                                                              @AuthenticationPrincipal UserDetails userDetails) {
        PharmacyResponseDto createdPharmacy = pharmacyService.createPharmacy(pharmacyRequestDto.getName(), userDetails.getUsername());
        return new ResponseEntity<>(createdPharmacy, HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<PharmacyResponseDto> getMyPharmacy(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(pharmacyService.getMyPharmacy(userDetails.getUsername()));
    }

    @ExceptionHandler(PharmacyAlreadyExistsException.class)
    public ResponseEntity<String> handlePharmacyAlreadyExists(PharmacyAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PharmacyNotFoundForUserException.class)
    public ResponseEntity<String> handlePharmacyNotFoundForUser(PharmacyNotFoundForUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}