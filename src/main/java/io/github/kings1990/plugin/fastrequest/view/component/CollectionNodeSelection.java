/*
 *
 *  * Copyright (C) 2021 WangSheng darkings1990@gmail.com
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Affero General Public License as published
 *  * by the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Affero General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Affero General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *  
 */

package io.github.kings1990.plugin.fastrequest.view.component;

import io.github.kings1990.plugin.fastrequest.view.model.CollectionCustomNode;
import org.jetbrains.annotations.NotNull;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CollectionNodeSelection implements Transferable {
    public static final DataFlavor CELL_DATA_FLAVOR = new DataFlavor(CollectionCustomNode.class, "application/x-java-celldata");
    private CollectionCustomNode data;

    public CollectionNodeSelection(CollectionCustomNode data){
        this.data = data;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{CELL_DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
        boolean supported = false;
        for (DataFlavor available : getTransferDataFlavors()) {
            if (available.equals(dataFlavor)) {
                supported = true;
            }
        }
        return supported;
    }

    @NotNull
    @Override
    public Object getTransferData(DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
        return data;
    }

    static protected DataFlavor createConstant(Class clazz, String name) {
        try {
            return new DataFlavor(clazz, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
