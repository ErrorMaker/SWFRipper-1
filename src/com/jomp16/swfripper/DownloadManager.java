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

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownloadManager implements Runnable
{
    private String url, path;

    public DownloadManager(Object... params)
    {
        if (params.length == 2)
        {
            url = (String) params[0];
            path = (String) params[1];
        }
    }

    @Override
    public void run()
    {
        File file = new File(path);
        if (!file.exists())
        {
            try
            {
                Main.getLogging().writeLine("DownloadingFile", url);
                FileUtils.copyURLToFile(new URL(url), file);
                Main.getLogging().writeLine("DownloadedFile", url);
            }
            catch (IOException e)
            {
                System.err.println(e.getLocalizedMessage());
            }
        }
        else
        {
            try
            {
                Main.getLogging().writeLine("BypassingFile", url);
            }
            catch (IOException e)
            {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
}
