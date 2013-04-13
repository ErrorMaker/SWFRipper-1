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

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

public class Configuration
{
    private Properties properties = new Properties();
    private boolean jar;

    public Configuration(boolean jar) throws IOException
    {
        this.jar = jar;
        try
        {
            properties.load(new InputStreamReader(new FileInputStream(System.getProperty("user.dir").replace("\\", "/") + "/config.properties")));
        }
        catch (IOException e)
        {
            Main.getLogging().writeLine("PropertiesNotFound");
            writeProperties();
        }
    }

    public String getValue(String key)
    {
        return properties.getProperty(key);
    }

    private void writeProperties() throws IOException
    {
        if (jar)
        {
            InputStream inputStream = getClass().getResourceAsStream("/config.properties");
            File file = new File(System.getProperty("user.dir").replace("\\", "/") + "/config.properties");
            FileUtils.copyInputStreamToFile(inputStream, file);
            inputStream.close();
            Main.getLogging().writeUsedRAM();
            System.exit(0);
        }
    }
}
