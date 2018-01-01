package com.innovify.innofyapp;

import android.content.Context;
import android.os.Environment;

import com.innovify.innofyapp.R;

import java.io.File;
import java.io.FileWriter;

public class Logger {
    public static void appendLog(Context context, String strGeneralEvent) {
        try {
            File logDir = new File(getProjectDirectory(context) + "/Logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            File file = new File(logDir.getAbsolutePath(), "Log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

//			Date date = new Date();
            FileWriter fr = new FileWriter(file, true);
            fr.write( "\n" + strGeneralEvent);
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will return the root folder for the project on Storage directory
     * @param c context instance for local reference
     * @return File object containing project root folder
     */
    public static File getProjectDirectory(Context c){
        // Find the SD Card path
        File projRootPath = new File(Environment.getExternalStorageDirectory() + "/" + c.getString(R.string.app_name));
        if(!projRootPath.exists()){
            projRootPath.mkdirs();
        }
        return projRootPath;
    }
}
