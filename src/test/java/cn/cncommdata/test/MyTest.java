package cn.cncommdata.test;
import java.lang.reflect.Field;
import	java.util.Calendar;

import cn.cncommdata.entity.CastOutput;
import cn.cncommdata.enums.EnumUtils;
import cn.cncommdata.enums.IOTConstants;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.extra.qrcode.QrCodeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 购物车测试类
 */
@Slf4j
public class MyTest {
    @Test
    void test1(){
        Product product1 = new Product();
        product1.setPrice(200D);
        product1.setQuantity(1);
        Product product2 = new Product();
        product2.setPrice(100D);
        product2.setQuantity(1);
        List list = CollUtil.newArrayList(product1, product2);
        System.out.println(TotalAmountOfShoppingCart(list));

        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("e:/qrcode.jpg"));
    }

    public double TotalAmountOfShoppingCart(List<Product> cartEventStream) {
        double result = cartEventStream.stream().filter(Objects::nonNull)
                // 分别计算每类商品金额
                .map(v -> v.getPrice() * v.getQuantity())
                // 计算每类商品满减后的商品金额
                .map(v -> (v > 199) ? (v - 40) : v)
                .mapToDouble(Double::doubleValue).sum();
        // 根据result判断是否免邮，得到最终总付款金额
        return (result > 500) ? result : (result + 50);
    }

    @Data
    static class Product{
        private Double price;
        private Integer quantity;
    }

    @Test
    void test2(){
        String str = "2020-07-08 10:13:01";
        Timestamp time = Timestamp.valueOf(str);
        Long timeLong = time.getTime();
        log.info(timeLong.toString());
    }
    @Test
    void test3(){
        Date time = new Date();
        String strn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        log.info(strn);
    }

    @Test
    void test4(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//要转换的时间格式
        String date = sdf.format(1594178985403L);
        log.info(date);
    }

    /**
     * 智能制造平台密码加密规则
     */
    @Test
    void MD5(){
        String a1 = SecureUtil.md5("Tstack@Gsd#");
        String strUpper = a1.toUpperCase();
        String a2 = SecureUtil.md5(strUpper);
        log.info(a2.toUpperCase());
    }

    @Test
    void test5() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field f = ReflectUtil.getField(CastOutput.class, "alloy");
        Class a = null;
        if (1 == 1) {
            IOTConstants.IOTEnum e = EnumUtils.valueOf(IOTConstants.IOTEnum.class, 100, IOTConstants.IOTEnum.class.getMethod("getCode"));
            a = e.getEnumClass();
        }
        if (null != a) {
            Enum<?> e1 = EnumUtils.valueOf(a, 20, a.getMethod("getCode"));
            Method m = e1.getClass().getMethod("getDescription");
            String result = (String) m.invoke(e1);
            log.info(result);
        }
    }
}