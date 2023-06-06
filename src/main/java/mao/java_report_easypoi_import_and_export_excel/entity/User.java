package mao.java_report_easypoi_import_and_export_excel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Project name(项目名称)：java_report_poi_million_data_export
 * Package(包名): mao.java_report_poi_million_data_export.entity
 * Class(类名): User
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/5
 * Time(创建时间)： 13:42
 * Version(版本): 1.0
 * Description(描述)： 无
 */


@Data
public class User
{
    @TableId
    @Excel(name = "用户编号", orderNum = "0", width = 9, type = 10)
    private Long id;

    @TableField(value = "user_name")
    @Excel(name = "名字", orderNum = "1", width = 20, type = 1)
    private String userName;

    @Excel(name = "手机号", orderNum = "2", width = 15, type = 10)
    private String phone;

    @Excel(name = "入职日期", format = "yyyy-MM-dd", orderNum = "3", width = 15)
    private LocalDateTime hireDate;

    @Excel(name = "家庭住址", orderNum = "4", width = 30, type = 1)
    private String address;
}
