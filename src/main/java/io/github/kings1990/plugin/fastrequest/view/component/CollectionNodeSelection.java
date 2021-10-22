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
