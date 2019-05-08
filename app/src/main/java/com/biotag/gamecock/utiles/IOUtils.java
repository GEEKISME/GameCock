package com.biotag.gamecock.utiles;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Lxh on 2017/7/11.
 */

public class IOUtils {
    /**
     * 关流
     */
    public static boolean close(Closeable io){
        if(io!=null){
            try {
                io.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return true;
    }
}
