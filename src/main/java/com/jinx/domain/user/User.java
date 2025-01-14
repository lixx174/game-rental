package com.jinx.domain.user;

import com.jinx.domain.primitive.Status;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;


/**
 * @author Jinx
 */
@Getter
public class User {

    private final String id;
    private String name;
    private LocalDate birthday;
    private Status status;


    public User(String id, String name, LocalDate birthday, Status status) {
        Assert.notNull(id, "id mustn't be null!");
        Assert.isTrue(
                name != null && name.matches("^[\\u4e00-\\u9fa5]{2,5}$"),
                "illegal name [%s]!".formatted(name)
        );
        Assert.isTrue(
                birthday != null && birthday.isBefore(LocalDate.now()),
                "sex mustn't be null!"
        );
        Assert.notNull(status, "status mustn't be null!");

        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.status = status;
    }

    /**
     * FIXME How to check the name is valid and is unique? Deliver the repo from outside?
     *
     * @param name latest user name
     */
    public void rename(String name) {

        this.name = name;
    }
}
