package com.jinx.infra.persistence.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jinx.domain.primitive.Mobile;
import com.jinx.domain.primitive.Password;
import com.jinx.domain.primitive.Status;
import com.jinx.domain.repository.AccountRepository;
import com.jinx.domain.user.Account;
import com.jinx.infra.persistence.converter.AccountConverter;
import com.jinx.infra.persistence.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jinx
 */
@Repository
@RequiredArgsConstructor
public class DefaultAccountRepository implements AccountRepository {

    private final AccountMapper target;
    private final AccountConverter converter;

    @Override
    public Optional<Account> findByMobile(@NonNull Mobile mobile) {
        AccountEntity accountDo = target.selectOne(
                Wrappers.lambdaQuery(AccountEntity.class)
                        .eq(AccountEntity::getMobile, mobile)
        );

        if(accountDo == null){
            return Optional.empty();
        }
        return Optional.of(
                new Account(
                        accountDo.getId(),
                        new Mobile(accountDo.getMobile()),
                        new Password(accountDo.getPassword()),
                        Status.valueOf(accountDo.getStatus())
                )
        );
    }

    @Override
    public void save(@NonNull Account account) {
        target.insert(converter.convert(account));
    }


    @Mapper
    interface AccountMapper extends BaseMapper<AccountEntity> {

    }
}
