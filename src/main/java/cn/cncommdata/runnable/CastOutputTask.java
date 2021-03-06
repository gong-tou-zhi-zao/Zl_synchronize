package cn.cncommdata.runnable;

import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.dao.CastOutputDao;
import cn.cncommdata.dao.TCronTriggerDao;
import cn.cncommdata.entity.CastOutput;
import cn.cncommdata.enums.TaskEnum;
import cn.cncommdata.runnable.utils.CastOutputUtil;
import cn.cncommdata.runnable.utils.RunnableUtil;
import cn.cncommdata.utils.ApplicationContextProvider;
import cn.cncommdata.utils.Builder;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CastOutputTask implements Runnable {
    private TCronTriggerDao cronTriggerDao;
    private CastOutputDao castOutputDao;
    private HttpConfig httpConfig;

    @Override
    public void run() {
        //  此处仅为获取spring中的dao对象（定时任务中无法通过@Autowired来获取对象）
        this.httpConfig = ApplicationContextProvider.getBean(HttpConfig.class);
        this.cronTriggerDao = ApplicationContextProvider.getBean(TCronTriggerDao.class);
        this.castOutputDao = ApplicationContextProvider.getBean(CastOutputDao.class);

        List<CastOutput> httpList = CastOutputUtil.getFromHTTP(httpConfig);

        //  此处为查询数据库条件构造，产量修改不会修改一年以上的数据
        //  TODO:此处过滤条件若要精确到月份，则需修改底层dao结构（暂未考虑此种情况）
        CastOutput criteria =  Builder.of(CastOutput::new)
                .with(CastOutput::setYear, DateUtil.date().year())
                .build();

        List<CastOutput> dbList = castOutputDao.queryAll(criteria);
        //  提前过滤http请求与db完全相同的数据（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(CastOutputUtil.getSameList(httpList, dbList));

        List<CastOutput> needInserts = CastOutputUtil.needInsert(httpList, dbList);
        //  由于实际需要更新的数据一定不是需要插入的数据，此处为提高性能而做的处理。（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(needInserts);

        List<CastOutput> needUpdates = CastOutputUtil.needUpdate(httpList, dbList);

        needInserts.stream().forEach( (needInsert) -> {
            castOutputDao.insert(needInsert);
        });

        needUpdates.stream().forEach( (needUpdate) -> {
            castOutputDao.update(needUpdate);
        });

        //  反写数据库执行时间
        RunnableUtil.UpdateLastRunTime(cronTriggerDao, TaskEnum.CAST_OUTPUT_TASK);
    }
}