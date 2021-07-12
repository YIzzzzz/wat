package com.jan.wat.pojo;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WatFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Equipment_ID")
    private String equipmentId;

    @TableField("Caliber_ID")
    private Integer caliberId;

    @TableField("Classify_ID")
    private Integer classifyId;

    @TableField("Model")
    private String model;

    @TableField("ClientName")
    private String clientname;

    @TableField("BookNo")
    private String bookno;

    @TableField("Price")
    private Double price;

    @TableField("Areas")
    private Double areas;

    @TableField("Persons")
    private Double persons;

    @TableField("Category")
    private Integer category;

    @TableField("Status")
    private Boolean status;

    @TableField("PositiveTotalAccumulate")
    private Double positivetotalaccumulate;

    @TableField("NegativeTotalAccumulate")
    private Double negativetotalaccumulate;

    @TableField("TotalAccumulate")
    private Double totalaccumulate;

    @TableField("FlowType_ID")
    private Integer flowtypeId;

    @TableField("StoreMode")
    private String storemode;

    @TableField("SiteNo")
    private String siteno;

    @TableField("Des")
    private String des;

    @TableField("AvgHourAccumulateT0")
    private Double avghouraccumulatet0;

    @TableField("AvgHourAccumulateT1")
    private Double avghouraccumulatet1;

    @TableField("AvgHourAccumulateT2")
    private Double avghouraccumulatet2;

    @TableField("AvgHourAccumulateT3")
    private Double avghouraccumulatet3;

    @TableField("AvgHourAccumulateT4")
    private Double avghouraccumulatet4;

    @TableField("AvgHourAccumulateT5")
    private Double avghouraccumulatet5;

    @TableField("AvgHourAccumulateT6")
    private Double avghouraccumulatet6;

    @TableField("AvgHourAccumulateT7")
    private Double avghouraccumulatet7;

    @TableField("AvgHourAccumulateT8")
    private Double avghouraccumulatet8;

    @TableField("AvgHourAccumulateT9")
    private Double avghouraccumulatet9;

    @TableField("AvgHourAccumulateT10")
    private Double avghouraccumulatet10;

    @TableField("AvgHourAccumulateT11")
    private Double avghouraccumulatet11;

    @TableField("AvgHourAccumulateT12")
    private Double avghouraccumulatet12;

    @TableField("AvgHourAccumulateT13")
    private Double avghouraccumulatet13;

    @TableField("AvgHourAccumulateT14")
    private Double avghouraccumulatet14;

    @TableField("AvgHourAccumulateT15")
    private Double avghouraccumulatet15;

    @TableField("AvgHourAccumulateT16")
    private Double avghouraccumulatet16;

    @TableField("AvgHourAccumulateT17")
    private Double avghouraccumulatet17;

    @TableField("AvgHourAccumulateT18")
    private Double avghouraccumulatet18;

    @TableField("AvgHourAccumulateT19")
    private Double avghouraccumulatet19;

    @TableField("AvgHourAccumulateT20")
    private Double avghouraccumulatet20;

    @TableField("AvgHourAccumulateT21")
    private Double avghouraccumulatet21;

    @TableField("AvgHourAccumulateT22")
    private Double avghouraccumulatet22;

    @TableField("AvgHourAccumulateT23")
    private Double avghouraccumulatet23;

    @TableField("AvgHourNumT0")
    private Double avghournumt0;

    @TableField("AvgHourNumT1")
    private Double avghournumt1;

    @TableField("AvgHourNumT2")
    private Double avghournumt2;

    @TableField("AvgHourNumT3")
    private Double avghournumt3;

    @TableField("AvgHourNumT4")
    private Double avghournumt4;

    @TableField("AvgHourNumT5")
    private Double avghournumt5;

    @TableField("AvgHourNumT6")
    private Double avghournumt6;

    @TableField("AvgHourNumT7")
    private Double avghournumt7;

    @TableField("AvgHourNumT8")
    private Double avghournumt8;

    @TableField("AvgHourNumT9")
    private Double avghournumt9;

    @TableField("AvgHourNumT10")
    private Double avghournumt10;

    @TableField("AvgHourNumT11")
    private Double avghournumt11;

    @TableField("AvgHourNumT12")
    private Double avghournumt12;

    @TableField("AvgHourNumT13")
    private Double avghournumt13;

    @TableField("AvgHourNumT14")
    private Double avghournumt14;

    @TableField("AvgHourNumT15")
    private Double avghournumt15;

    @TableField("AvgHourNumT16")
    private Double avghournumt16;

    @TableField("AvgHourNumT17")
    private Double avghournumt17;

    @TableField("AvgHourNumT18")
    private Double avghournumt18;

    @TableField("AvgHourNumT19")
    private Double avghournumt19;

    @TableField("AvgHourNumT20")
    private Double avghournumt20;

    @TableField("AvgHourNumT21")
    private Double avghournumt21;

    @TableField("AvgHourNumT22")
    private Double avghournumt22;

    @TableField("AvgHourNumT23")
    private Double avghournumt23;

    @TableField("AvgDayAccumulateT1")
    private Double avgdayaccumulatet1;

    @TableField("AvgDayAccumulateT2")
    private Double avgdayaccumulatet2;

    @TableField("AvgDayAccumulateT3")
    private Double avgdayaccumulatet3;

    @TableField("AvgDayAccumulateT4")
    private Double avgdayaccumulatet4;

    @TableField("AvgDayAccumulateT5")
    private Double avgdayaccumulatet5;

    @TableField("AvgDayAccumulateT6")
    private Double avgdayaccumulatet6;

    @TableField("AvgDayAccumulateT7")
    private Double avgdayaccumulatet7;

    @TableField("AvgDayAccumulateT8")
    private Double avgdayaccumulatet8;

    @TableField("AvgDayAccumulateT9")
    private Double avgdayaccumulatet9;

    @TableField("AvgDayAccumulateT10")
    private Double avgdayaccumulatet10;

    @TableField("AvgDayAccumulateT11")
    private Double avgdayaccumulatet11;

    @TableField("AvgDayAccumulateT12")
    private Double avgdayaccumulatet12;

    @TableField("AvgDayAccumulateT13")
    private Double avgdayaccumulatet13;

    @TableField("AvgDayAccumulateT14")
    private Double avgdayaccumulatet14;

    @TableField("AvgDayAccumulateT15")
    private Double avgdayaccumulatet15;

    @TableField("AvgDayAccumulateT16")
    private Double avgdayaccumulatet16;

    @TableField("AvgDayAccumulateT17")
    private Double avgdayaccumulatet17;

    @TableField("AvgDayAccumulateT18")
    private Double avgdayaccumulatet18;

    @TableField("AvgDayAccumulateT19")
    private Double avgdayaccumulatet19;

    @TableField("AvgDayAccumulateT20")
    private Double avgdayaccumulatet20;

    @TableField("AvgDayAccumulateT21")
    private Double avgdayaccumulatet21;

    @TableField("AvgDayAccumulateT22")
    private Double avgdayaccumulatet22;

    @TableField("AvgDayAccumulateT23")
    private Double avgdayaccumulatet23;

    @TableField("AvgDayAccumulateT24")
    private Double avgdayaccumulatet24;

    @TableField("AvgDayAccumulateT25")
    private Double avgdayaccumulatet25;

    @TableField("AvgDayAccumulateT26")
    private Double avgdayaccumulatet26;

    @TableField("AvgDayAccumulateT27")
    private Double avgdayaccumulatet27;

    @TableField("AvgDayAccumulateT28")
    private Double avgdayaccumulatet28;

    @TableField("AvgDayAccumulateT29")
    private Double avgdayaccumulatet29;

    @TableField("AvgDayAccumulateT30")
    private Double avgdayaccumulatet30;

    @TableField("AvgDayAccumulateT31")
    private Double avgdayaccumulatet31;

    @TableField("AvgDayNumT1")
    private Double avgdaynumt1;

    @TableField("AvgDayNumT2")
    private Double avgdaynumt2;

    @TableField("AvgDayNumT3")
    private Double avgdaynumt3;

    @TableField("AvgDayNumT4")
    private Double avgdaynumt4;

    @TableField("AvgDayNumT5")
    private Double avgdaynumt5;

    @TableField("AvgDayNumT6")
    private Double avgdaynumt6;

    @TableField("AvgDayNumT7")
    private Double avgdaynumt7;

    @TableField("AvgDayNumT8")
    private Double avgdaynumt8;

    @TableField("AvgDayNumT9")
    private Double avgdaynumt9;

    @TableField("AvgDayNumT10")
    private Double avgdaynumt10;

    @TableField("AvgDayNumT11")
    private Double avgdaynumt11;

    @TableField("AvgDayNumT12")
    private Double avgdaynumt12;

    @TableField("AvgDayNumT13")
    private Double avgdaynumt13;

    @TableField("AvgDayNumT14")
    private Double avgdaynumt14;

    @TableField("AvgDayNumT15")
    private Double avgdaynumt15;

    @TableField("AvgDayNumT16")
    private Double avgdaynumt16;

    @TableField("AvgDayNumT17")
    private Double avgdaynumt17;

    @TableField("AvgDayNumT18")
    private Double avgdaynumt18;

    @TableField("AvgDayNumT19")
    private Double avgdaynumt19;

    @TableField("AvgDayNumT20")
    private Double avgdaynumt20;

    @TableField("AvgDayNumT21")
    private Double avgdaynumt21;

    @TableField("AvgDayNumT22")
    private Double avgdaynumt22;

    @TableField("AvgDayNumT23")
    private Double avgdaynumt23;

    @TableField("AvgDayNumT24")
    private Double avgdaynumt24;

    @TableField("AvgDayNumT25")
    private Double avgdaynumt25;

    @TableField("AvgDayNumT26")
    private Double avgdaynumt26;

    @TableField("AvgDayNumT27")
    private Double avgdaynumt27;

    @TableField("AvgDayNumT28")
    private Double avgdaynumt28;

    @TableField("AvgDayNumT29")
    private Double avgdaynumt29;

    @TableField("AvgDayNumT30")
    private Double avgdaynumt30;

    @TableField("AvgDayNumT31")
    private Double avgdaynumt31;

    @TableField("Enable")
    private Boolean enable;

    @TableField("LastTime")
    private Date lasttime;

    @TableField("LastPositiveTotalAccumulate")
    private Double lastpositivetotalaccumulate;

    @TableField("LastNegativeTotalAccumulate")
    private Double lastnegativetotalaccumulate;

    @TableField("LastHourTime")
    private Date lasthourtime;

    @TableField("LastHourAccumulate")
    private Double lasthouraccumulate;

    @TableField("LastDayTime")
    private Date lastdaytime;

    @TableField("LastDayAccumulate")
    private Double lastdayaccumulate;

    @TableField("LastWeekTime")
    private Date lastweektime;

    @TableField("LastWeekAccumulate")
    private Double lastweekaccumulate;

    @TableField("LastMonthTime")
    private Date lastmonthtime;

    @TableField("LastMonthAccumulate")
    private Double lastmonthaccumulate;


}
