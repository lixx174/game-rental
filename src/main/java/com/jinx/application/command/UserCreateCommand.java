package com.jinx.application.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Jinx
 */
@Getter
@Setter
public class UserCreateCommand {

    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 生日
     */
    private LocalDate birthday;
}
