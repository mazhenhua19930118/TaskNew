package com.example.task.mapper;

import com.example.task.sharding.UserSharding;

public interface ShardingMapper {
    int shardingInsert(UserSharding user);
    int shardingInsertDateBase(UserSharding user);
    UserSharding select(UserSharding user);
}
