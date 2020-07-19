package cn.cncommdata.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class IOTConstants {

    @Getter
    @AllArgsConstructor
    public enum IOTEnum {
        test1(100, WarmEnum.class),
        test2(101, ChuQiEnum.class),
        ;
        private int code;
        private Class enumClass;
    }

    @Getter
    @AllArgsConstructor
    public enum WarmEnum {
        test1(20, "透气砖1实际压力", "bar"),
        test2(21, "透气砖2实际压力", "bar"),
        ;
        private int code;
        private String description;
        private String unit;
    }

    @Getter
    @AllArgsConstructor
    public enum ChuQiEnum {
        test1(20, "铝液温度", "℃"),
        test2(21, "转子速度", "mm/min"),
        ;
        private int code;
        private String description;
        private String unit;
    }
}