package cn.cncommdata.exception;

import cn.cncommdata.exception.impl.SysExcImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 */
@Slf4j
//@SpringBootTest
public class MyTest {
    @Test
    public void test(){
        Class<?> clazz = SysExcImpl.classFind(o -> Class.forName(o), "cn.cncommdata.exception.impl.SysExcImpl");
    }
}