package com.wangwenjun.juc.atomic;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/2
 * QQ交流群:601980517，463962286
 ***************************************/
public class GetLockException extends Exception {

    public GetLockException() {
        super();
    }

    public GetLockException(String message) {
        super(message);
    }
}
