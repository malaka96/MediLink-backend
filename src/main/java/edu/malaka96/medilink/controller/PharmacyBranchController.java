package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.PharmacyBranchAlreadyExistsException;
import edu.malaka96.medilink.exception.PharmacyNotFoundException;
import edu.malaka96.medilink.exception.UnauthorizedPharmacyAccessException;
import edu.malaka96.medilink.model.dto.PharmacyBranchRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyBranchResponseDto;
import edu.malaka96.medilink.service.PharmacyBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy-branches")
@RequiredArgsConstructor
public class PharmacyBranchController {

    private final PharmacyBranchService pharmacyBranchService;

    @PostMapping
    public ResponseEntity<PharmacyBranchResponseDto> createBranch(@RequestBody PharmacyBranchRequestDto pharmacyBranchRequestDto,
                                                                   @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(pharmacyBranchService.createBranch(pharmacyBranchRequestDto, userDetails.getUsername()), HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<List<PharmacyBranchResponseDto>> getMyBranches(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(pharmacyBranchService.getBranchesByOwnerEmail(userDetails.getUsername()));
    }

    @ExceptionHandler(UnauthorizedPharmacyAccessException.class)
    public ResponseEntity<String> handleUnauthorizedAccess(UnauthorizedPharmacyAccessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
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