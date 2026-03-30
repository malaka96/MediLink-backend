package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.MedicineAliasAlreadyExistsException;
import edu.malaka96.medilink.exception.MedicineNotFoundException;
import edu.malaka96.medilink.model.dto.MedicineAliasRequestDto;
import edu.malaka96.medilink.model.dto.MedicineAliasResponseDto;
import edu.malaka96.medilink.service.MedicineAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicine-aliases")
@RequiredArgsConstructor
public class MedicineAliasController {

    private final MedicineAliasService medicineAliasService;

    @PostMapping
    public ResponseEntity<MedicineAliasResponseDto> createAlias(@RequestBody MedicineAliasRequestDto medicineAliasRequestDto) {
        MedicineAliasResponseDto createdAlias = medicineAliasService.createAlias(medicineAliasRequestDto);
        return new ResponseEntity<>(createdAlias, HttpStatus.CREATED);
    }

    @ExceptionHandler(MedicineAliasAlreadyExistsException.class)
    public ResponseEntity<String> handleAliasAlreadyExists(MedicineAliasAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MedicineNotFoundException.class)
    public ResponseEntity<String> handleMedicineNotFound(MedicineNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}