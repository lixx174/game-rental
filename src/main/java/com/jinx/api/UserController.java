package com.jinx.api;

import com.jinx.application.Result;
import com.jinx.application.command.UserCreateCommand;
import com.jinx.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping("/create")
    public Result<Void> save(@RequestBody UserCreateCommand command) {
        return Result.succeed(() -> service.save(command));
    }
}
