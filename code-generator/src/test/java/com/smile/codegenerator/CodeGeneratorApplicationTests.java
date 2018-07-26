package com.smile.codegenerator;

import org.apache.commons.lang.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeGeneratorApplicationTests {

    @Test
    public void contextLoads() {
        String replace = WordUtils.capitalizeFully("tb_name", new char[]{'_'}).replace("_", "");
        System.out.println(replace);
    }

}
