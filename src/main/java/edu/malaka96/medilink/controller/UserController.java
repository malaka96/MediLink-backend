package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.RoleNotFoundException;
import edu.malaka96.medilink.exception.UserAlreadyExistsException;
import edu.malaka96.medilink.exception.UserNotFoundException;
import edu.malaka96.medilink.model.dto.AdminUserRequestDto;
import edu.malaka96.medilink.model.dto.UserRequestDto;
import edu.malaka96.medilink.model.dto.UserResponseDto;
import edu.malaka96.medilink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<UserResponseDto> createUserByAdmin(@RequestBody AdminUserRequestDto adminUserRequestDto) {
        return new ResponseEntity<>(userService.createUserByAdmin(adminUserRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getUserByEmail(userDetails.getUsername()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFound(RoleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}