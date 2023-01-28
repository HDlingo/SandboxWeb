package edu.example.demo.results;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangyantian
 * @date 2021/05/12
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    //成功提示码
    SUCCESS(200, "成功"),

    //自定义失败信息
    FAILURE(500, "失败"),

    //通用错误码 50001~50099
    PROGRAM_INSIDE_EXCEPTION(501, "程序内部异常"),
    REQUEST_PARAM_ERROR(502, "请求参数错误");

    //用户模块错误码 50100~50199
    //商品模块错误码 50200~50299
    //订单模块错误码 50300~50399

    private final Integer code;
    private final String message;
}

