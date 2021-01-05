package org.example.birthdayRestaurant.model.dto;

public class BaseDTO <T>{
    private boolean success;

    private String message;

    private T data;

    public BaseDTO success(T data){
        success = true;
        this.data = data;
        return this;
    }

    public BaseDTO fail(){
        success = false;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
