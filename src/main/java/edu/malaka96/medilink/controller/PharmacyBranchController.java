package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.PharmacyBranchAlreadyExistsException;
import edu.malaka96.medilink.exception.PharmacyNotFoundException;
import edu.malaka96.medilink.model.dto.PharmacyBranchRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyBranchResponseDto;
import edu.malaka96.medilink.service.PharmacyBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pharmacy-branches")
@RequiredArgsConstructor
public class PharmacyBranchController {

    private final PharmacyBranchService pharmacyBranchService;

    @PostMapping
    public ResponseEntity<PharmacyBranchResponseDto> createBranch(@RequestBody PharmacyBranchRequestDto pharmacyBranchRequestDto) {
        PharmacyBranchResponseDto createdBranch = pharmacyBranchService.createBranch(pharmacyBranchRequestDto);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @ExceptionHandler(PharmacyBranchAlreadyExistsException.class)
    public ResponseEntity<String> handleBranchAlreadyExists(PharmacyBranchAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PharmacyNotFoundException.class)
    public ResponseEntity<String> handlePharmacyNotFound(PharmacyNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}