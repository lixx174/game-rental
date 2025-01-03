package com.jinx.infra.generator;

import com.jinx.application.generator.IdentityGenerator;
import com.jinx.application.generator.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jinx
 */
@Primary
@Component
@RequiredArgsConstructor
public class DelegateIdentityGenerator implements IdentityGenerator {

    private final List<IdentityGenerator> generators;

    @Override
    public String generate(Schema schema) {
        return null;
    }
}
