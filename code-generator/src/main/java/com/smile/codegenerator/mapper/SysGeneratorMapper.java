package com.smile.codegenerator.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysGeneratorMapper {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String,Object> params);

    Map<String,String> queryTable(String tableName);

    List<Map<String,String>> queryColumns(String tableName);
}
