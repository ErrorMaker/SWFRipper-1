/*
 * Copyright (C) 2013 jomp16 <joseoliviopedrosa@gmail.com>
 *
 * This file is part of SWFRipper.
 *
 * SWFRipper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SWFRipper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SWFRipper. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jomp16.swfripper;

import com.jomp16.swfripper.methods.DownloadFurni;
import com.jomp16.swfripper.methods.DownloadGamedata;
import com.jomp16.swfripper.methods.DownloadGordon;

import java.io.IOException;

public class Main
{
    private static Configuration configuration;
    private static Logging logging;
    private static String DirectoryName;
    private static String RELEASE;
    private static boolean jar;

    public static void main(String[] args) throws IOException
    {
        jar = (Main.class.getResource("Main.class").toString().startsWith("jar:")) ? true : false;

        logging = new Logging(jar);

        logging.writeLine("WelcomeToSWFRipper");
        logging.writeLine("Version", "0.1.0");
        logging.writeLine();
        logging.writeLine("LoadingPropertiesFile");

        configuration = new Configuration(jar);

        logging.writeLine("PropertiesLoaded");

        int index1 = configuration.getValue("GordonURL").indexOf("RELEASE");
        RELEASE = Main.getConfiguration().getValue("GordonURL").substring(index1, (Main.getConfiguration().getValue("GordonURL").length()));
        DirectoryName = System.getProperty("user.dir").replace("\\", "/") + "/swf";

        logging.writeLine("DirectoryName", DirectoryName);
        logging.writeLine("DownloadingGamedata");

        executeTask(new DownloadGamedata());

        if (configuration.getValue("DownloadGordon").equals("true"))
        {
            logging.writeLine("DownloadingGordon", RELEASE);
            executeTask(new DownloadGordon(RELEASE));
        }

        if (configuration.getValue("DownloadFurni").equals("true"))
        {
            logging.writeLine("DownloadingFurni");
            executeTask(new DownloadFurni());
        }

        logging.writeLine();
        logging.writeLine("GoodBye");
        logging.writeLine();
        logging.writeUsedRAM();
    }

    public static Configuration getConfiguration()
    {
        return configuration;
    }

    public static Logging getLogging()
    {
        return logging;
    }

    public static String getDirectoryName(String morePath)
    {
        return DirectoryName + "/" + morePath;
    }

    public static String formatURL(String moreURL)
    {
        return configuration.getValue("HotelURL") + "/" + moreURL;
    }

    public static void executeTask(Runnable runnable)
    {
        Thread thread = new Thread(runnable);
        thread.run();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            try
            {
                logging.writeLine("InterruptedExceptionError");
            }
            catch (IOException e1)
            {
                System.err.println(e1.getLocalizedMessage());
            }
        }
    }
}
