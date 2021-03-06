package cn.cncommdata.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (ColdRollOutput)实体类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Getter
@Setter
public class ColdRollOutput implements Serializable {
    private static final long serialVersionUID = 235124407648265551L;
    /**
    * 冷轧产量表id
    */
    private Long id;
    /**
    * 单月单合金产量（T）
    */
    private Double weight;
    /**
    * 合金分类
    */
    private String alloy;
    /**
    * 月总产量
    */
    private Double totalWeight;
    /**
    * 当前产量所属年份
    */
    private Integer year;
    /**
    * 当前产量所属月份
    */
    private Integer month;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;

    /**
     * 对象相等
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof ColdRollOutput)) {
            return false;
        } else {
            ColdRollOutput object = (ColdRollOutput) o;
            if (!StrUtil.equals(alloy, object.getAlloy())) {
                return false;
            }
            if (!weight.equals(object.getWeight())) {
                return false;
            }
            if (!totalWeight.equals(object.getTotalWeight())) {
                return false;
            }
            if (!year.equals(object.getYear())) {
                return false;
            }
            if (!month.equals(object.getMonth())) {
                return false;
            }
            return true;
        }
    }

    /**
     * 数据库已经存在
     * @param o
     * @return
     */
    public boolean alreadyInDB(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof ColdRollOutput)) {
            return false;
        } else {
            ColdRollOutput object = (ColdRollOutput) o;
            if (!StrUtil.equals(object.getAlloy(), alloy)) {
                return false;
            }
            return true;
        }
    }
}