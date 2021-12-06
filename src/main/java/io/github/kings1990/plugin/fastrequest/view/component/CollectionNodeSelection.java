/*
 * Copyright 2021 kings1990(darkings1990@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
