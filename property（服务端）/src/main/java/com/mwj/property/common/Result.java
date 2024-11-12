////定义了一个通用的API响应类，用于封装API请求的响应结果
package com.mwj.property.common;
import lombok.Data;
//注解提供了自动生成getter和setter方法、toString方法、equals方法和hashCode方法的功能。
@Data
//被注解为 @Data，Lombok 会自动生成该类的 getter 和 setter 方法等。Result 类用于封装 API 响应结果。
public class Result {

    private int code;//200成功,400失败
    private String msg;//成功、失败
    private  Long total;//总记录数
    private  Object data;//数据

    public static Result fail(){
        return result(400,"失败",0L,null);
    }

    public static Result suc(){
        return result(200,"成功",0L,null);
    }

    public static Result fail(String msg) {
        return result(400, msg, 0L, null);
    }
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }

    public static Result suc(Object data,Long total){
        return result(200,"成功",total,data);
    }

    private static Result result(int code,String msg,Long total,Object data){
        Result res = new Result();
        res.setData(data);
        res.setMsg(msg);
        res.setCode(code);
        res.setTotal(total);
        return res;
    }
}
