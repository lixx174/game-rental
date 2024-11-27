package com.jinx.app.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jinx
 */
@Getter
@Setter
public class Game extends Entity{

    private GameType type;

    private String name;

    private String logo;
}
