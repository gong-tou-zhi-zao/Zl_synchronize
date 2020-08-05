package cn.cncommdata.exception.interf;

@FunctionalInterface
public interface SysExcInterface {
    Class<?> className2Class(String className) throws ClassNotFoundException;
}
