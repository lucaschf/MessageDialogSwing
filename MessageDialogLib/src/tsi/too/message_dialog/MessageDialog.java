package tsi.too.message_dialog;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public abstract class MessageDialog {
    /**
     * Brings up an information-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     */
    public static void showInformationDialog(String title, Object message) {
        showMessageDialog(null, message, title, INFORMATION_MESSAGE);
    }

    /**
     * Brings up a plain-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     */
    public static void showPlainMessageDialog(String title, Object message) {
        showMessageDialog(null, message, title, PLAIN_MESSAGE);
    }

    /**
     * Brings up an alert-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     */
    public static void showAlertDialog(String title, Object message) {
        showMessageDialog(null, message, title, WARNING_MESSAGE);
    }

    /**
     * Brings up a confirmation dialog with <code>YES_NO_OPTION</code> options.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * @return true if user clicked on <code>YES_OPTION</code>
     */
    public static boolean showConfirmationDialog(String title, String message) {
        return showConfirmDialog(
                null,
                message,
                title,
                YES_NO_OPTION,
                PLAIN_MESSAGE
        ) == YES_OPTION;
    }

    /**
     * Brings up an information-message dialog written in a <code>{@link JTextArea}</code> inside a
     * <code>{@link JScrollPane}</code> showing scrollbars when is needed.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     */
    public static void showTextMessage(String title, String message) {
        var textArea = new JTextArea(10, 50);
        int margin = 5;

        textArea.setEditable(false);
        textArea.setText(message);
        textArea.setBorder(BorderFactory.createCompoundBorder(
                textArea.getBorder(),
                BorderFactory.createEmptyBorder(margin, margin, margin, margin)));

        showPlainMessageDialog(title, new JScrollPane(textArea));
    }
    
    /**
     * Displays a table in a message box based on  TableModel
     * 
     * @param title the dialog title
     * @param tableModel the table model
     * @param colunsWidth the columns width
     * @param tableDimension the preferred table dimension
     */
    public static void showDataTable(String title, TableModel tableModel, int[] colunsWidth, Dimension tableDimension) {
		JTable table = new JTable(tableModel);
		
		TableColumnModel taColumnModel = table.getColumnModel();
		
		if(colunsWidth != null) {
			for(int i = 0; i < colunsWidth.length; i++)
				taColumnModel.getColumn(i).setPreferredWidth(colunsWidth[i]);
		}
		
		table.setPreferredScrollableViewportSize(tableDimension);
		showPlainMessageDialog(title, new JScrollPane(table));
	}
}