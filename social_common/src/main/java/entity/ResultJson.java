package entity;

/**
 * @Author: xiangwenqin
 * @DateTime: 2020/3/17 10:03
 */
public class ResultJson<T> {
    private Boolean flag;
    private Integer code;
    private String message;
    private T data;

    public ResultJson() {
    }

    public ResultJson(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public ResultJson(Boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 操作成功返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultJson<T> success(T data){
        return new ResultJson<T>(Boolean.TRUE,ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }
    /**
     * 操作成功返回结果
     * @param <T>
     * @return
     */
    public static <T> ResultJson<T> success(String message,T data){
        return new ResultJson<T>(Boolean.TRUE,ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 操作失败
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> ResultJson<T> failed(IErrorCode errorCode){
        return new ResultJson<T>(Boolean.FALSE,errorCode.getCode(),errorCode.getMessage());
    }
    /**
     * 操作失败
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> ResultJson<T> failed(IErrorCode errorCode,String message){
        return new ResultJson<T>(Boolean.FALSE,errorCode.getCode(),message);
    }
    /**
     * 操作失败
     * @param <T>
     * @return
     */
    public static <T> ResultJson<T> failed(String message){
        return new ResultJson<T>(Boolean.FALSE,ResultCode.FAILED.getCode(),message);
    }



    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
