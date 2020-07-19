package cn.cncommdata.webservice;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class WebserviceTest{
    @Test
    void test() {
        String result= HttpUtil.get("http://172.16.5.10/glserver_new/DataHandler.ashx");
        log.info(result);
    }
}
