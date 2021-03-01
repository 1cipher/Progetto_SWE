import javax.swing.*;
import java.awt.*;

public class CustomRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){

        if (value instanceof Calendar){
            value = ((Calendar)value).name;
        }

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

    }

}
