package com.smile.codegenerator.controller;

import com.alibaba.fastjson.JSON;
import com.smile.codegenerator.service.SysGeneratorService;
import com.smile.codegenerator.utils.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description:首页
 * @Author: wangqing
 * @CreateDate: 2018/6/25 14:43
 * @Version: 1.0
 */

@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<Map<String, Object>> list = sysGeneratorService.queryList(params);
        int total = sysGeneratorService.queryTotal(params);
        return Result.ok().put("rows", list).put("total",total);
    }


    /**
     * 生成代码
     * @param request
     * @param response
     */
    @RequestMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tableNames=new String[]{};
        String tables = request.getParameter("tables");
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        byte[] data = sysGeneratorService.generatorCode(tableNames);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");


        IOUtils.write(data, response.getOutputStream());
    }

}
