package com.hk.ssm4.mapper.salve;

import java.util.List;

import com.hk.ssm4.entity.UserEntity;

public interface UserMapperSalve {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);

}
