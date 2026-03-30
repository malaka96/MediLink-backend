package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.MedicineAlreadyExistsException;
import edu.malaka96.medilink.model.dto.MedicineRequestDto;
import edu.malaka96.medilink.model.dto.MedicineResponseDto;
import edu.malaka96.medilink.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineResponseDto> createMedicine(@RequestBody MedicineRequestDto medicineRequestDto) {
        MedicineResponseDto createdMedicine = medicineService.createMedicine(medicineRequestDto);
        return new ResponseEntity<>(createdMedicine, HttpStatus.CREATED);
    }

    @ExceptionHandler(MedicineAlreadyExistsException.class)
    public ResponseEntity<String> handleMedicineAlreadyExists(MedicineAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}