package kr.co.mapper.web;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
    String selectUUID();
    String reservationId();
}
