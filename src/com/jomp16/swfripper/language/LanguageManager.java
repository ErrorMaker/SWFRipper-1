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

package com.jomp16.swfripper.language;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class LanguageManager
{
    private Properties properties = new Properties();

    public LanguageManager(boolean jar) throws IOException
    {
        String format = "/languages/%s_%s.properties";
        String formatted = String.format(format, System.getProperty("user.language"), System.getProperty("user.country"));
        if (jar)
            properties.load(getClass().getResourceAsStream(formatted));
        else
            properties.load(new InputStreamReader(new FileInputStream(new File(System.getProperty("user.dir").replace("\\", "/") + formatted))));
    }

    public String getString(String key)
    {
        return properties.getProperty(key);
    }

    public String getString(String key, Object... params)
    {
        return String.format(properties.getProperty(key), params);
    }
}
