package com.jan.wat.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EquParaQuery {

    @ApiModelProperty(value = "参数代码")
    private Integer id;

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "参数组名称")
    private String paraname;

    @ApiModelProperty(value = "参数类型")
    private String type;

    @ApiModelProperty(value = "是否只读")
    private String readonly;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParaname() {
        return paraname;
    }

    public void setParaname(String paraname) {
        this.paraname = paraname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }
}
