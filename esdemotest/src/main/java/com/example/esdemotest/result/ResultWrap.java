package com.example.esdemotest.result;
import com.example.esdemotest.conext.RegexpConstant;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.regex.Matcher;


/**
 * service层传递包装类
 *
 * @author ccq
 * @date 2016年7月23日
 */
@ApiModel(value = "service层传递包装类")
public class ResultWrap<T> implements Serializable {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", example = "200", required = true)
    private int code;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "返回描述", example = "请求成功", required = true, position = 1)
    private String desc;
    /**
     * 数据域
     */
    @ApiModelProperty(value = "数据", position = 2)
    private T data;


    /**
     * 新建成功响应对象
     *
     * @return 响应对象
     */
    public static <T> ResultWrap<T> success() {
        return success(null);
    }

    /**
     * 新建成功响应对象
     *
     * @param response 结果
     * @return 响应对象
     */
    public static <T> ResultWrap<T> success(T response) {
        return new ResultWrap<>(ResponseCode.CODE_200, response);
    }

    /**
     * 新建分页成功响应对象
     *
     * @param pageInfo pagehelper分页插件的pageInfo
     * @return 响应对象
     */
    public static <T> ResultWrap<PageResponse<T>> successPage(PageInfo<T> pageInfo) {
        if (pageInfo == null) {
            return success();
        }
        PageResponse<T> pageResponse = new PageResponse<>();
        BeanUtils.copyProperties(pageInfo, pageResponse);
        return success(pageResponse);
    }


    /**
     * @return 新建失败响应对象
     */
    public static <T> ResultWrap<T> failure() {
        return new ResultWrap<>(ResponseCode.CODE_400, null);
    }

    /**
     * 新建失败响应对象
     *
     * @param code 异常代码  desc {0} {1} 占位符替换
     * @param data 数据域
     * @param args 替换值
     * @param <T>
     * @return 响应对象
     */
    public static <T> ResultWrap<T> failure(ResponseCode code, T data, String... args) {
        String desc = code.getDesc();
        Matcher m = RegexpConstant.CHARACTER_REPLACEMENT.matcher(desc);
        while (m.find()) {
            desc = desc.replace(m.group(), args == null ? "" : args[Integer.parseInt(m.group(1))]);
        }
        ResultWrap<T> resultWrap = new ResultWrap<>(code, data);
        resultWrap.setDesc(desc);
        return resultWrap;
    }

    /**
     * 新建失败响应对象
     *
     * @param code 异常代码key值
     * @param data 数据域
     * @param args 替换值
     * @param <T>
     * @return 响应对象
     */
    public static <T> ResultWrap<T> failure(Integer code, T data, String... args) {
        return getResponseWrap(code, data, args);
    }


    /**
     * 变换返回模型
     *
     * @return
     */
    public ResponseWrap<T> changeMethod() {
        return ResponseWrap.result(code, desc, data);
    }


    /**
     * 新建失败响应对象
     *
     * @param code 编码
     * @return 响应对象
     */
    public static <T> ResultWrap<T> failure(ResponseCode code) {
        return new ResultWrap<>(code, null);
    }

    /**
     * 新建失败响应对象
     *
     * @param code 编码
     * @param data 返回数据
     * @return 响应对象
     */
    public static <T> ResultWrap<T> failure(ResponseCode code, T data) {
        return new ResultWrap<>(code, data);
    }

    /**
     * 校验是否请求成功
     *
     * @return true 请求失败 false 请求成功
     */
    public boolean isFailure() {
        return code != ResponseCode.CODE_200.getCode();
    }

    /**
     * 响应对象
     *
     * @param code 编码
     * @return 响应对象
     */
    public static <T> ResultWrap<T> result(ResponseCode code) {
        return new ResultWrap<>(code, null);
    }


    /**
     * 响应对象
     *
     * @param code 异常代码key值
     * @param data 数据域
     * @param args 替换值
     * @param <T>
     * @return 响应对象
     */
    public static <T> ResultWrap<T> result(Integer code, T data, String... args) {
        return getResponseWrap(code, data, args);
    }

    /**
     * 响应对象
     *
     * @param code 异常代码key值
     * @param date 数据域
     * @param <T>
     * @return 响应对象
     */
    public static <T> ResultWrap<T> result(Integer code, String desc, T date) {
        ResultWrap<T> resultWrap = new ResultWrap<>();
        resultWrap.setDesc(desc);
        resultWrap.setCode(code);
        resultWrap.setData(date);
        return resultWrap;
    }


    private static <T> ResultWrap<T> getResponseWrap(Integer code, T data, String[] args) {
        ResponseCode ResponseCode = Enum.valueOf(ResponseCode.class, "CODE_" + code);
        String desc = ResponseCode.getDesc();
        Matcher m = RegexpConstant.CHARACTER_REPLACEMENT.matcher(desc);
        while (m.find()) {
            desc = desc.replace(m.group(), args == null ? "" : args[Integer.parseInt(m.group(1))]);
        }
        ResultWrap<T> resultWrap = new ResultWrap<>(ResponseCode, data);
        resultWrap.setDesc(desc);
        return resultWrap;
    }


    public ResultWrap() {
        this.code = ResponseCode.CODE_200.getCode();
        this.desc = ResponseCode.CODE_200.getDesc();
    }

    public ResultWrap(ResponseCode code) {
        this.code = code.getCode();
        this.desc = code.getDesc();
    }

    public ResultWrap(ResponseCode ResponseCode, T data) {
        this.code = ResponseCode.getCode();
        this.desc = ResponseCode.getDesc();
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return (T) data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseWrap{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
