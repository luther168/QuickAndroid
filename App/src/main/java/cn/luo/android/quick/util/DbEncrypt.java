package cn.luo.android.quick.util;

import java.io.File;

import net.sqlcipher.database.SQLiteDatabase;

import android.content.Context;

import com.blankj.utilcode.util.FileUtils;

public class DbEncrypt {

    /**
     * 加密数据库
     *
     * @param encryptedPath 加密后的数据库绝对路径
     * @param decryptedPath 要加密的数据库绝对路径
     * @param key           密码
     */
    public static boolean encrypt(String decryptedPath, String encryptedPath, String key) {
        try {
            File databaseFile = new File(decryptedPath);
            if (!FileUtils.isFileExists(databaseFile)) {
                return false;
            }
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "", null);//打开要加密的数据库

            /*String passwordString = "1234"; //只能对已加密的数据库修改密码，且无法直接修改为“”或null的密码
            database.changePassword(passwordString.toCharArray());*/

            File encryptedDatabaseFile = new File(encryptedPath);//新建加密后的数据库文件
            //deleteDatabase(SDcardPath + encryptedName);

            //连接到加密后的数据库，并设置密码
            String sql = String.format("ATTACH DATABASE '%s' as encrypt_db KEY '" + key + "';",
                    encryptedDatabaseFile.getAbsolutePath());
            database.rawExecSQL(sql);
            //输出要加密的数据库表和数据到加密后的数据库文件中
            sql = "SELECT sqlcipher_export('encrypt_db');";
            database.rawExecSQL(sql);
            //断开同加密后的数据库的连接
            sql = "DETACH DATABASE encrypt_db;";
            database.rawExecSQL(sql);

            //打开加密后的数据库，测试数据库是否加密成功
            SQLiteDatabase encrypteddatabase = SQLiteDatabase.openOrCreateDatabase(encryptedDatabaseFile, key, null);
            //encrypteddatabase.setVersion(database.getVersion());
            encrypteddatabase.close();//关闭数据库

            database.close();
            return true;
        } catch (Exception e) {
            FileUtils.deleteFile(encryptedPath);
            e.printStackTrace();
        }
        return false;
    }

    public static boolean decrypt(String encryptedPath, String decryptedPath, String key) {
        try {
            File databaseFile = new File(encryptedPath);
            if (!FileUtils.isFileExists(databaseFile)) {
                return false;
            }
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, key, null);
            File decryptedDatabaseFile = new File(decryptedPath);
            database.rawExecSQL(String.format("ATTACH DATABASE '%s' as decrypted_db KEY '';",
                    decryptedDatabaseFile.getAbsolutePath()));
            database.rawExecSQL("SELECT sqlcipher_export('decrypted_db');");
            database.rawExecSQL("DETACH DATABASE decrypted_db");

            SQLiteDatabase decryptedDatabase = SQLiteDatabase.openOrCreateDatabase(decryptedPath, "", null);
            decryptedDatabase.close();

            database.close();
            return true;
        } catch (Exception e) {
            FileUtils.deleteFile(decryptedPath);
            e.printStackTrace();
        }
        return false;
    }
}
