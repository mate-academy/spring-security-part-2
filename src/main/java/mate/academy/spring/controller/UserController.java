package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.response.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;

    public UserController(UserService userService,
                          ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper) {
        this.userService = userService;
        this.userResponseDtoMapper = userResponseDtoMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found"));
        return userResponseDtoMapper.mapToDto(user);
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User bob = new User();
        bob.setEmail("Bob@gm.com");
        bob.setPassword("Anderson");

        User alice = new User();
        alice.setEmail("Alice@gm.com");
        alice.setPassword("Waterspoon");

        User david = new User();
        david.setEmail("David@gm.com");
        david.setPassword("Jonson");

        User mike = new User();
        mike.setEmail("Mike@gm.com");
        mike.setPassword("Shoolermaster");

        userService.add(bob);
        userService.add(alice);
        userService.add(david);
        userService.add(mike);
        return "Done!";
    }
}
