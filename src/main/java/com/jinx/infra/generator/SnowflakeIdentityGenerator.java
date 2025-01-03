package com.jinx.infra.generator;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jinx.application.generator.IdentityGenerator;
import com.jinx.application.generator.Schema;
import org.springframework.stereotype.Component;

/**
 * Snowflake id.
 *
 * @author Jinx
 */
@Component
public class SnowflakeIdentityGenerator implements IdentityGenerator {

    @Override
    public String generate(Schema schema) {
        return IdWorker.getIdStr();
    }
}
