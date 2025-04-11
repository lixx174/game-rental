package com.jinx.api;

import com.jinx.application.Result;
import com.jinx.application.command.OrderCreateCommand;
import com.jinx.application.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @PostMapping("/create")
    public Result<Void> create(@RequestBody OrderCreateCommand command) {
        return Result.succeed(() -> service.create(command));
    }
}
