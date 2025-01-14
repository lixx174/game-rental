package com.jinx.domain.user;

import com.jinx.domain.primitive.Mobile;
import com.jinx.domain.primitive.Password;
import com.jinx.domain.primitive.Status;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @author Jinx
 */
@Getter
public class Account {

    private final String id;
    private Mobile mobile;
    private Password password;
    private Status status;

    public Account(String id, Mobile mobile, Password password, Status status) {
        Assert.notNull(id, "id mustn't be null!");
        Assert.notNull(mobile, "mobile mustn't be null!");
        Assert.notNull(password, "password mustn't be null!");
        Assert.notNull(status, "status mustn't be null!");

        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.status = status;
    }

    public void change() {
        // TODO Change one by one? or by object
    }
}