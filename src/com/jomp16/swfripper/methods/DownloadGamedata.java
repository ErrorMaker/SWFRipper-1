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

public class DownloadGamedata implements Runnable
{
    @Override
    public void run()
    {
        Main.executeTask(new DownloadManager(Main.formatURL("gamedata/external_variables"), Main.getDirectoryName("gamedata/external_variables.txt")));
        Main.executeTask(new DownloadManager(Main.formatURL("gamedata/external_flash_texts"), Main.getDirectoryName("gamedata/external_flash_texts.txt")));
        Main.executeTask(new DownloadManager(Main.formatURL("gamedata/productdata"), Main.getDirectoryName("gamedata/productdata.txt")));
        Main.executeTask(new DownloadManager(Main.formatURL("gamedata/furnidata"), Main.getDirectoryName("gamedata/furnidata.txt")));
        Main.executeTask(new DownloadManager(Main.formatURL("gamedata/figuredata"), Main.getDirectoryName("gamedata/figuredata.xml")));
    }
}
