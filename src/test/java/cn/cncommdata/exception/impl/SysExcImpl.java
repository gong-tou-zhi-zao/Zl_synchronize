package cn.cncommdata.exception.impl;

import cn.cncommdata.exception.interf.SysExcInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SysExcImpl {

    public static Class<?> classFind(SysExcInterface myInterface, String className) {
        Class<?> clazz = null;
        try{
            clazz = myInterface.className2Class(className);
        } catch (ClassNotFoundException e) {
            log.error("............" + e.getMessage() + ".............");
        }
        return clazz;
    }
}
