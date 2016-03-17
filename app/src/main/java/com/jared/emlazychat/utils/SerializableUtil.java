package com.jared.emlazychat.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by jared on 16/3/16.
 */
public class SerializableUtil {
    public static <T extends Serializable> void write(T t, String outPath)
        throws Exception{
        ObjectOutputStream oos = null;
        try {
            File file = new File(outPath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(t);
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }

    public static Serializable read(String inpath) throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(inpath));
            Object object = ois.readObject();
            if(object != null) {
                return (Serializable) object;
            }
        } finally {
            if(ois != null) {
                ois.close();
            }
        }
        return null;
    }
}
