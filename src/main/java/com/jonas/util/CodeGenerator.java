package com.jonas.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/2/23 11:18
 * @description MyBatisPlus代码生成器
 */
public class CodeGenerator {

    private static final String url = "jdbc:mysql://127.0.0.1/local?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong";
    private static final String user = "root";
    private static final String pass = "123456";
    private static final String author = "shenjy";
    private static final String outDir = System.getProperty("user.dir") + "/src/main/java";
    private static final List<String> tables = Arrays.asList("wechat_session");

    public static void main(String[] args) {
        FastAutoGenerator.create(url, user, pass)
                .globalConfig(builder -> {
                    builder.author(author)
                            //.enableSwagger()    //开启 swagger 模式
                            //.fileOverride()       //覆盖已生成文件
                            .outputDir(outDir)      //指定输出目录
                    ;
                })
                .packageConfig(builder -> {
                    builder.parent("com.jonas")       // 设置父包名
                            .moduleName("")                    // 设置父包模块名
                            //.pathInfo(Collections.singletonMap(OutputFile.mapperXml, outDir + "/com/jonas/util/out"))
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables);
                })
                .execute();
    }
}
