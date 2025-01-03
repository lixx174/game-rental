package com.jinx.infra.persistence;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

/**
 * Common column of entity from mysql, the business table entity always need to extend this.
 *
 * @author Jinx
 */
@Getter
@Setter
public abstract class Entity {

    @TableId
    private String id;

    @TableLogic
    private boolean deleted;
}
