package com.smile.codegenerator.service;

import com.smile.codegenerator.mapper.SysGeneratorMapper;
import com.smile.codegenerator.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @Description:代码生成
 * @Author: wangqing
 * @CreateDate: 2018/6/25 17:23
 * @Version: 1.0
 */
@Service
public class SysGeneratorService {

    @Autowired
    private SysGeneratorMapper sysGeneratorMapper;

    /**
     * 查询表数据
     *
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryList(Map<String, Object> params) {
        return sysGeneratorMapper.queryList(params);
    }

    public int queryTotal(Map<String, Object> params) {
        return sysGeneratorMapper.queryTotal(params);
    }

    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    private List<Map<String,String>> queryColumns(String tableName) {
        return sysGeneratorMapper.queryColumns(tableName);
    }

    private Map<String,String> queryTable(String tableName) {
        return sysGeneratorMapper.queryTable(tableName);
    }
}
