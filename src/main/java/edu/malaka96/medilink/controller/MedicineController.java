package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.MedicineAlreadyExistsException;
import edu.malaka96.medilink.exception.PharmacyBranchNotFoundException;
import edu.malaka96.medilink.exception.PharmacyNotFoundForUserException;
import edu.malaka96.medilink.exception.UnauthorizedBranchAccessException;
import edu.malaka96.medilink.model.dto.MedicineRequestDto;
import edu.malaka96.medilink.model.dto.MedicineResponseDto;
import edu.malaka96.medilink.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineResponseDto> createMedicine(@RequestBody MedicineRequestDto medicineRequestDto,
                                                              @AuthenticationPrincipal UserDetails userDetails) {
        MedicineResponseDto createdMedicine = medicineService.createMedicine(medicineRequestDto, userDetails.getUsername());
        return new ResponseEntity<>(createdMedicine, HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<List<MedicineResponseDto>> getMyPharmacyMedicines(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(medicineService.getMyPharmacyMedicines(userDetails.getUsername()));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<MedicineResponseDto>> getMedicinesByBranch(@PathVariable Long branchId,
                                                                          @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(medicineService.getMedicinesByBranch(branchId, userDetails.getUsername()));
    }

    @ExceptionHandler(MedicineAlreadyExistsException.class)
    public ResponseEntity<String> handleMedicineAlreadyExists(MedicineAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PharmacyBranchNotFoundException.class)
    public ResponseEntity<String> handlePharmacyBranchNotFound(PharmacyBranchNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedBranchAccessException.class)
    public ResponseEntity<String> handleUnauthorizedBranchAccess(UnauthorizedBranchAccessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PharmacyNotFoundForUserException.class)
    public ResponseEntity<String> handlePharmacyNotFoundForUser(PharmacyNotFoundForUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}