package com.java.test.functional;

import java.util.Date;
import java.util.function.Supplier;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/11/16 2:42 下午
 */
public class SupplierDemo {

    public static void main(String[] args) {
        System.out.println(get(() -> new Date(System.currentTimeMillis())));
    }

    static Date get(Supplier<Date> supplier){
        return supplier.get();
    }

}
