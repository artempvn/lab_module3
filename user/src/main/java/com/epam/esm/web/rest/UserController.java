package com.epam.esm.web.rest;

import com.epam.esm.dto.PaginationParameter;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.dto.UserDtoWithOrders;
import com.epam.esm.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDtoWithOrders> readUser(@PathVariable long id) {
    UserDtoWithOrders user = userService.read(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UserDto> readUsers(@Valid PaginationParameter parameter) {
    return userService.readAll(parameter);
  }

  @GetMapping("/popular_tag")
  public ResponseEntity<TagDto> readMostWidelyTagFromUserWithHighestCostOrders() {
    TagDto tag = userService.takeMostWidelyTagFromUserWithHighestCostOrders();
    return ResponseEntity.status(HttpStatus.OK).body(tag);
  }
}
