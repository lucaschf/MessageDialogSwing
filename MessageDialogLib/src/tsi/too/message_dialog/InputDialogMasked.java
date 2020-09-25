package tsi.too.message_dialog;

import javax.swing.JDialog;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InputDialogMasked extends JDialog {
	private static final long serialVersionUID = 1L;

	private JButton buttonOK;
	private JButton buttonCancel;

	private JLabel messageLabel;

	private JFormattedTextField inputTextField;

	private String userInput = null;

	private InputDialogMasked( String title, String message, String mask) {
		setSize(new Dimension(300, 128));
		getContentPane().setLayout(null);
		setModal(true);

		createCancelButton();
		createOkButton();
		createMessageLabel(message);
		createInputField(mask);

		setTitle(title);	
		setResizable(false);
		setLocationRelativeTo(null);
		getRootPane().setDefaultButton(buttonOK);

		setupWindowClosing();
	}

	private void createMessageLabel(String message) {
		messageLabel = new JLabel(message);
		messageLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		messageLabel.setBounds(10, 10, 266, 13);
		getContentPane().add(messageLabel);
	}

	private void createOkButton() {
		buttonOK = new JButton("Ok");
		buttonOK.setBounds(96, 55, 85, 26);
		getContentPane().add(buttonOK);

		buttonOK.addActionListener(e -> onOK());
	}

	private void createCancelButton() {
		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(191, 55, 85, 26);
		getContentPane().add(buttonCancel);

		buttonCancel.addActionListener(e -> onCancel());
	}

	private void createInputField(String mask) {
		try {
			MaskFormatter maskFormatter = new MaskFormatter(mask);
			maskFormatter.setPlaceholderCharacter('_');
			inputTextField = new JFormattedTextField(maskFormatter);
		} catch (Exception ex) {
			inputTextField = new JFormattedTextField();
		}
		
		inputTextField.setBounds(10, 30, 266, 19);
		inputTextField.registerKeyboardAction(
				e -> onOK(),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		getContentPane().add(inputTextField);
	}

	private void onOK() {
		userInput = inputTextField.getText();
		dispose();
	}

	private void onCancel() {
		dispose();
	}

	private void setupWindowClosing() {
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		((JComponent) getContentPane()).registerKeyboardAction(
				e -> onCancel(),
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
				);
	}

	/**
	 * Shows a dialog box asking for user input with the specified <code>mask</code></>.
	 *
	 * @param title   the <code>Object</code> to display in the dialog title bar.
	 * @param message the <code>Object</code> to display.
	 * @param mask    the <code>mask</code> to use.
	 * @return user's input parsed or null if canceled.
	 */
	public static String showInputDialog(String title, String message, String mask) {
		InputDialogMasked dialog = new InputDialogMasked(title, message, mask);
		dialog.setVisible(true);

		return dialog.userInput;
	}
}
