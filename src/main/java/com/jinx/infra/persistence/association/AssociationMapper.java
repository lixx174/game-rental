package com.jinx.infra.persistence.association;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jinx
 */
@Mapper
public interface AssociationMapper<T> extends BaseMapper<T> {
}
