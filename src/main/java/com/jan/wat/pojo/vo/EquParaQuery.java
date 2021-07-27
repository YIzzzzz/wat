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
    private Integer type;

    @ApiModelProperty(value = "是否只读")
    private boolean readonly;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }


}
