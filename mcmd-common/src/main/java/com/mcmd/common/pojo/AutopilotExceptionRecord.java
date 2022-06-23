package com.mcmd.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ppliu
 * @version 1.0
 * @date 2022/3/15 16:05
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@TableName("ssc_autopilot_uat.autopilot_exception_record")
public class AutopilotExceptionRecord extends Model<AutopilotExceptionRecord> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("exception_info")
    private String exceptionInfo;
    @TableField("exception_message")
    private String exceptionMessage;
    @TableField("exception_class")
    private String exceptionClass;
    @TableField("exception_engender_date")
    private String exceptionEngenderDate;
    @TableField("project_model")
    private String projectModel;
    @TableField("project_method")
    private String projectMethod;
    @TableField("operation_data")
    private String operationData;
    @TableField("operation_type")
    private String operationType;
    @TableField("operation_flag")
    private String operationFlag;
    @TableField("operation_data_class")
    private String operationDataClass;
}
