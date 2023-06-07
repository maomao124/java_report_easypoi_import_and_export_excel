package mao.java_report_easypoi_import_and_export_excel.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import mao.java_report_easypoi_import_and_export_excel.entity.User;
import mao.java_report_easypoi_import_and_export_excel.mapper.UserMapper;
import mao.java_report_easypoi_import_and_export_excel.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Project name(项目名称)：java_report_easypoi_import_and_export_excel
 * Package(包名): mao.java_report_easypoi_import_and_export_excel.service.impl
 * Class(类名): UserServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/6/6
 * Time(创建时间)： 22:30
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService
{

    @Override
    public void download()
    {
        log.info("开始导出");
        //只导出前5万条
        IPage<User> page = new Page<>(0, 50000);
        List<User> userList = this.page(page).getRecords();
        //指定导出的格式是高版本的格式
        ExportParams exportParams = new ExportParams("员工信息", "数据", ExcelType.XSSF);
        //直接使用EasyPOI提供的方法
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, userList);
        try (FileOutputStream fileOutputStream = new FileOutputStream("./out.xlsx"))
        {
            workbook.write(fileOutputStream);
            workbook.close();
            log.info("导出完成");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void importExcel()
    {
        log.info("开始导入excel");
        try (FileInputStream fileInputStream = new FileInputStream("./out.xlsx"))
        {
            ImportParams importParams = new ImportParams();
            //有多少行的标题
            importParams.setTitleRows(1);
            //有多少行的头
            importParams.setHeadRows(1);
            //导入
            List<User> userList = ExcelImportUtil.importExcel(fileInputStream, User.class, importParams);
            //打印
            userList.forEach(user -> log.info(user.toString()));
            log.info("导入完成");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void downLoadWithTemplate()
    {
        log.info("开始导出");

        try (FileOutputStream fileOutputStream = new FileOutputStream("./out3.xlsx"))
        {
            TemplateExportParams params = new TemplateExportParams("./template.xlsx", true);

            User user = this.getById(1);
            //hutools工具类
            Map<String, Object> map = BeanUtil.beanToMap(user);
            //转换
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            workbook.write(fileOutputStream);
            workbook.close();
            log.info("导出完成");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
