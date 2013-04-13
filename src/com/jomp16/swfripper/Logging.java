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

import com.jomp16.swfripper.language.LanguageManager;

import java.io.IOException;

public class Logging
{
    private boolean jar;
    private static LanguageManager languageManager;

    public Logging(boolean jar) throws IOException
    {
        this.jar = jar;
        languageManager = new LanguageManager(jar);
    }

    public void writeLine(String key) throws IOException
    {
        if (jar)
            System.out.println(new String(languageManager.getString(key).getBytes("ISO-8859-1")));
        else
            System.out.println(languageManager.getString(key));
    }

    public void writeLine(String key, Object... params) throws IOException
    {
        if (jar)
            System.out.println(new String(languageManager.getString(key, params).getBytes("ISO-8859-1")));
        else
            System.out.println(languageManager.getString(key, params));
    }

    public void writeLine()
    {
        System.out.println();
    }

    public void writeLine(Object string) throws IOException
    {
        if (jar)
            System.out.println(new String(string.toString().getBytes("ISO-8859-1")));
        else
            System.out.println(string);
    }

    public String humanReadableByteCount(long bytes, boolean si) throws IOException
    {
        int unit = si ? 1000 : 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public void writeUsedRAM() throws IOException
    {
        writeLine("UsedMemory", humanReadableByteCount(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), false));
    }
}
