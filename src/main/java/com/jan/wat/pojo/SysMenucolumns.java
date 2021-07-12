package com.jan.wat.pojo;

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
public class SysMenucolumns implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("MenuCode")
    private String menucode;

    @TableField("Sequence")
    private Integer sequence;

    @TableField("Name")
    private String name;

    @TableField("Visible")
    private Boolean visible;

    @TableField("Frozen")
    private Boolean frozen;

    @TableField("Align")
    private String align;

    @TableField("Sort")
    private Boolean sort;

    @TableField("GroupName")
    private String groupname;

    @TableField("DisplayName")
    private String displayname;

    @TableField("Width")
    private Integer width;

    @TableField("Field")
    private String field;

    @TableField("Formatter")
    private String formatter;

    @TableField("Editor")
    private String editor;

    @TableField("Hidden")
    private Boolean hidden;

    @TableField("Required")
    private Boolean required;


}
