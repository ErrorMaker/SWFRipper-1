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

package com.jomp16.swfripper.methods;

import com.jomp16.swfripper.DownloadManager;
import com.jomp16.swfripper.Main;
import com.jomp16.swfripper.parser.XMLParser;

import java.io.File;

public class DownloadGordon implements Runnable
{
    private String RELEASE;
    private String raw = "gordon/%s/%s";

    public DownloadGordon(String RELEASE)
    {
        this.RELEASE = RELEASE;
    }

    @Override
    public void run()
    {
        ConfigHabbo();
        FigureMap();
        DownloadStuffs();
    }

    private String formatURL(String moreURL)
    {
        return Main.getConfiguration().getValue("GordonURL") + "/" + moreURL;
    }

    private void ConfigHabbo()
    {
        Main.executeTask(new DownloadManager(formatURL("config_habbo.xml"), Main.getDirectoryName(String.format(raw, RELEASE, "config_habbo.xml"))));
        try
        {
            XMLParser xmlParser = new XMLParser(new File(Main.getDirectoryName(String.format(raw, RELEASE, "config_habbo.xml"))), "library", "url");
            for (int i = 0; i < xmlParser.getNodes().size(); i++)
            {
                Main.executeTask(new DownloadManager(formatURL(xmlParser.getNodes().get(i)), Main.getDirectoryName(String.format(raw, RELEASE, xmlParser.getNodes().get(i)))));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private void FigureMap()
    {
        Main.executeTask(new DownloadManager(formatURL("figuremap.xml"), Main.getDirectoryName(String.format(raw, RELEASE, "figuremap.xml"))));
        try
        {
            XMLParser xmlParser = new XMLParser(new File(Main.getDirectoryName(String.format(raw, RELEASE, "figuremap.xml"))), "lib", "id");
            for (int i = 0; i < xmlParser.getNodes().size(); i++)
            {
                Main.executeTask(new DownloadManager(formatURL(xmlParser.getNodes().get(i) + ".swf"), Main.getDirectoryName(String.format(raw, RELEASE, xmlParser.getNodes().get(i) + ".swf"))));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private void DownloadStuffs()
    {
        // TODO: ADD MORE STUFFS LIKE HABBO.SWF, PLACEHOLDER.SWF, ROOMS.SWF, ETC
        Main.executeTask(new DownloadManager(formatURL("Habbo.swf"), Main.getDirectoryName(String.format(raw, RELEASE, "Habbo.swf"))));
    }
}
