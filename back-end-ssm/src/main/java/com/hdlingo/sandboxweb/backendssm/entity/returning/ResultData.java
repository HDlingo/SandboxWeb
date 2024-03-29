package com.hdlingo.sandboxweb.backendssm.entity.returning;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * description : 接口返回类型
 *
 * @author : Pikachudy
 * @date : 2023/1/29 19:26
 */

@Data
@Schema(name = "返回数据对象")
public class ResultData<T> {
    @Schema(description = "请求数据主体")
    private T obj;
    @Schema(description = "请求是否成功")
    private boolean status;
    @Schema(description = "提示信息")
    private String message;

    /**
     * 返回成功提示，不含请求体
     * @return msg及status
     */
    public ResultData<T> OK(){
        this.message="OK";
        this.status=true;
        return this;
    }

    /**
     * 返回失败提示，不含请求体
     * @return msg及status
     */
    public ResultData<T> FAILED(){
        this.message="FAILED";
        this.status=false;
        return this;
    }

    /**
     * 返回含有请求对象
     * @param status 是否成功
     * @param obj 请求对象
     */
    public ResultData<T> sendObj(boolean status,T obj){
        if(status){
            this.message="OK";
            this.status=true;
        }
        else{
            this.message="FAILED";
            this.status=false;
        }
        this.obj=obj;
        return this;
    }

}

