package tsi.too.message_dialog;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import tsi.too.message_dialog.ext.StringExt;

/**
 * A simple class for user's input handling.
 * 
 * @author Lucas Cristovam
 *
 */
public abstract class InputDialog {

	/**
	 * Shows a dialog requesting input from the user. Loops until the validation rule is satisfied or the user cancels the
	 * reading.
	 *
	 * @param title     the <code>Object</code> to display in the dialog title bar.
	 * @param message   the <code>Object</code> to display.
	 * @param validator the <code>Object</code> with the validation rule.
	 * @return user's input parsed or null if aborted.
	 */
	public static Integer showIntegerInputDialog(String title, String message, InputValidator<Integer> validator) {
		int input;
		String s;

		var isValid = false;

		do {
			s = showStringInputDialog(title, message);
			if (s == null)
				return null;

			input = StringExt.toInt(s);
			isValid = validator.isValid(input);

			if (!isValid)
				MessageDialog.showAlertDialog(title, validator.getErrorMessage(input));
		} while (!isValid);

		return input;
	}

	/**
	 * Shows a dialog requesting input from the user. Loops until the validation rule is satisfied or the user cancels the
	 * reading.
	 *
	 * @param title     the <code>Object</code> to display in the dialog title bar.
	 * @param message   the <code>Object</code> to display.
	 * @param validator the <code>Object</code> with the validation rule.
	 * @return user's input parsed or null if aborted.
	 */
	public static Long showLongInputDialog(String title, String message, InputValidator<Long> validator) {
		long input;
		String s;
		var isValid = false;

		do {
			s = showStringInputDialog(title, message);
			if (s == null)
				return null;

			input = StringExt.toLong(s);
			isValid = validator.isValid(input);

			if (!isValid)
				MessageDialog.showAlertDialog(title, validator.getErrorMessage(input));
		} while (!isValid);

		return input;
	}

	/**
	 * Shows a dialog requesting input from the user. Loops until the validation rule is satisfied or the user cancels the
	 * reading.
	 *
	 * @param title     the <code>Object</code> to display in the dialog title bar.
	 * @param message   the <code>Object</code> to display.
	 * @param validator the <code>Object</code> with the validation rule.
	 * @return user's input parsed or null if aborted.
	 */
	public static Double showDoubleInputDialog(
			String title,
			String message,
			InputValidator<Double> validator
			) {
		double input;
		String s;
		var isValid = false;

		do {
			s = showStringInputDialog(title, message);
			if (s == null)
				return null;

			input = StringExt.toDouble(s);
			isValid = validator.isValid(input);

			if (!isValid)
				MessageDialog.showAlertDialog(title, validator.getErrorMessage(input));
		} while (!isValid);

		return input;
	}

	/**
	 * Shows a dialog requesting input from the user. Loops until the validation rule is satisfied or the user cancels the
	 * reading.
	 *
	 * @param title     the <code>Object</code> to display in the dialog title bar.
	 * @param message   the <code>Object</code> to display.
	 * @param validator the <code>Object</code> with the validation rule.
	 * @return user's input parsed or null if aborted.
	 */
	public static BigDecimal showBigDecimalInputDialog(
		String title, String message, InputValidator<BigDecimal> validator) {
		BigDecimal input;
		String s;
		var isValid = false;

		do {
			s = showStringInputDialog(title, message);
			if (s == null)
				return null;

			input = StringExt.toBigDecimal(s);
			isValid = validator.isValid(input);

			if (!isValid)
				MessageDialog.showAlertDialog(title, validator.getErrorMessage(input));
		} while (!isValid);

		return input;
	}

	/**
	 * Shows a dialog requesting input from the user.
	 *
	 * @param title   the <code>Object</code> to display in the dialog title bar.
	 * @param message the <code>Object</code> to display.
	 * @return user's input.
	 */
	public static String showStringInputDialog(String title, String message) {
		return showInputDialog(null, message, title, PLAIN_MESSAGE);
	}

	/**
	 * Shows a dialog requesting input from the user.Loops until the validation rule is satisfied or the user cancels the
	 * reading.
	 *
	 * @param title the <code>Object</code> to display in the dialog title bar.
	 * @param message the <code>Object</code> to display.
	 * @param validator the <code>Object</code> with validation rule.
	 * @return user's input.
	 */
	public static String showStringInputDialog(String title, String message, InputValidator<String> validator) {
		String s;
		var isValid = false;

		do {
			s = showStringInputDialog(title, message);
			if (s == null)
				return null;
			isValid = validator.isValid(s);

			if (!isValid)
				MessageDialog.showAlertDialog(title, validator.getErrorMessage(s));
		} while (!isValid);

		return s;
	}

	/**
	 * Shows a dialog box asking for user input with the specified <code>mask</code>. Loops until the validation rule
	 * is satisfied or the user cancels the reading.
	 *
	 * @param title     the <code>Object</code> to display in the dialog title bar.
	 * @param message   the <code>Object</code> to display.
	 * @param mask      the <code>mask</code> to use.
	 * @param validator the <code>Object</code> with validation rule.
	 * @return user's input parsed or null if canceled.
	 */
	public static String showMaskedInputDialog(String title, String message, String mask, InputValidator<String> validator) {
		String input;
		var isValid = false;

		JFormattedTextField inputTextField;
		
		try {
			MaskFormatter maskFormatter = new MaskFormatter(mask);
			maskFormatter.setPlaceholderCharacter('_');
			inputTextField = new JFormattedTextField(maskFormatter);
		} catch (Exception ex) {
			inputTextField = new JFormattedTextField();
		}
		
		do {
			input = CustomInputDialog.showSingleLineInputDialog(title, message, inputTextField);
			
			if (input == null)
				return null;

			isValid = validator.isValid(input);

			if (!isValid)
				MessageDialog.showAlertDialog(title, validator.getErrorMessage(input));
		} while (!isValid);

		return input;
	}

	/**
	 * Shows a dialog box asking for user input. Loops until the validation rule is satisfied or the user cancels the
	 * reading.
	 *
	 * @param title     the <code>Object</code> to display in the dialog title bar.
	 * @param message   the <code>Object</code> to display.
	 * @param validator the <code>Object</code> with validation rule.
	 * @return user's input parsed or null if canceled.
	 */
	public static LocalDate showBrazilianDateInputDialog(
			String title,
			String message,
			InputValidator<LocalDate> validator
			) {
		String input;
		String mask = "##/##/####";
		LocalDate result;
		var isValid = false;

		JFormattedTextField inputTextField;
		
		try {
			MaskFormatter maskFormatter = new MaskFormatter(mask);
			maskFormatter.setPlaceholderCharacter('_');
			inputTextField = new JFormattedTextField(maskFormatter);
		} catch (Exception ex) {
			inputTextField = new JFormattedTextField();
		}
		
		do {
			input = CustomInputDialog.showSingleLineInputDialog(title, message, inputTextField);
			
			if (input == null)
				return null;

			try {
				String[] spl = input.split("/");
				result = LocalDate.of(StringExt.toInt(spl[2]), StringExt.toInt(spl[1]), StringExt.toInt(spl[0]));
			} catch (Exception ex) {
				result = LocalDate.MIN;
			}
			
			isValid = validator.isValid(result);
			if (!isValid)
				MessageDialog.showAlertDialog(title, validator.getErrorMessage(result));
		} while (!isValid);

		return result;
	}

	/**
	 * Shows a multiLine input dialog
	 * 
	 * @param title     the <code>Object</code> to display in the dialog title bar.
	 * @param message   the <code>Object</code> to display.
	 * @param dimension the preferred dialog dimension. 
	 * @return user's input.
	 */
	public static String showMultiLineInputDialog(String title, String message, MultiLineDimension dimension) {
		int rows;
		int columns;
		Dimension dialogDimension;
		
		switch (dimension) {
			case SMALL:
				rows = 15;
				columns = 10;
				dialogDimension = CustomInputDialog.SMALL_MULTILINE_DIALOG;
				break;
			case MEDIUM:
				rows = 22;
				columns = 20;
				dialogDimension = CustomInputDialog.MEDIUM_MULTILINE_DIALOG;
				break;
			default:
				rows = 30;
				columns = 20;
				dialogDimension = CustomInputDialog.LARGE_MULTILINE_DIALOG;			
				break;
		}
		
		return CustomInputDialog.showMultiLineInputDialog(
				title,
				message, 
				new JTextArea(rows, columns),
				dialogDimension
		);
	}
	
	/**
	 * Brings up a choice dialog, where the initial choice is the first one.
	 *
	 * @param title   the title string for the dialog.
	 * @param message the message to display.
	 * @param options the available choices.
	 * @param <E>     the type of the options.
	 * @return user's choice or null if canceled.
	 */
	public static <E> E showOptionDialog(String title, String message, E[] options) {
		int selected = JOptionPane.showOptionDialog(null,
				message,
				title,
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				options[0]
				);

		if (selected != JOptionPane.CLOSED_OPTION)
			return options[selected];

		return null;
	}

	/**
	 * Brings up a choice dialog, where the initial choice is the first one and an option is mandatory.
	 *
	 * @param title   the title string for the dialog.
	 * @param message the message to display.
	 * @param options the available choices.
	 * @param <E>     the type of the options.
	 * @param validator  the <code>Object</code> with validation rule.
	 * 
	 * @return user's choice or null if canceled.
	 */
	public static <E> E showOptionDialog(String title, String message, E[] options, InputValidator<E> validator) {
		E input;
		boolean isValid = false;
		
		do {
			input = showOptionDialog(title, message, options);
			
			if(input == null)
				return null;
			
		}while(!isValid);
	}
	
	/**
	 * Brings up a choice dialog, where the initial choice is the first one and excecutes 
	 * an action based on the selected option.
	 *
	 * @param title   the title string for the dialog.
	 * @param message the message to display.
	 * @param options the available choices.
	 * @param exitOption the default loop cancel option.
	 * @param executor the executor to the selected option.
	 */
	public static void showMenuDialog(
			String title, 
			String message, 
			final List<String> options,
			String exitOption,
			Executor<String> executor
	){
		String selected;
		
		List<String> l = new ArrayList<>();
		l.addAll(options);
		
		if(!l.contains(exitOption))
			l.add(exitOption);
		
		do {
			selected = showOptionDialog(title, message, l.toArray(String[]::new));
			
			executor.execute(selected);
		}while(selected != null && !selected.equals(exitOption));
	}
	
	/**
	 * A processor that checks an input.
	 */
	public interface InputValidator<E> {
		String DEFAULT_SUCCESS_MESSAGE = "";
		String FIELD_CANNOT_BE_EMPTY = "Este campo n�o pode ficar vazio.";

		/**
		 * gets the validation result.
		 *
		 * @param input the <code>Object</code> to be validated.
		 * @return the validation result.
		 */
		String getErrorMessage(E input);

		default boolean isValid(E input) {
			return getErrorMessage(input).equals(DEFAULT_SUCCESS_MESSAGE);
		}
	}

	/**
	 * Creates an default validator for empty strings.
	 * 
	 * @param message the message to return if validation fails.
	 * @return the created validator.
	 */
	public static final InputValidator<String> createEmptyStringValidator(String message) {
		return new InputValidator<String>() {
			@Override
			public String getErrorMessage(String input) {
				return input.isEmpty() || input.isBlank() ? FIELD_CANNOT_BE_EMPTY : DEFAULT_SUCCESS_MESSAGE;
			}
		};
	}
	
	/**
	 * Creates a double range validator
	 * 
	 * @param beginInclusive the first element of the range.
	 * @param endInclusive the last element of the range.
	 * @param validationMessage the message to return if validation fails.
	 * @return
	 */
	public static final InputValidator<Double> createRangeValidator(
			double beginInclusive, 
			double endInclusive, 
			String validationMessage
	) {
		return new InputValidator<Double>() {
			
			@Override
			public String getErrorMessage(Double input) {
				return input <= endInclusive 
						&& input>= beginInclusive ? DEFAULT_SUCCESS_MESSAGE : validationMessage;
			}			
		};
	}
		
	/**
	 * Creates a length validator.
	 * 
	 * @param minLength the minimum length of the input.
	 * @param maxLength the maximum length of the input.
	 * @param validationMessage the message to return if validation fails.
	 * @return
	 */
	public static final InputValidator<String> createLengthValidator(
			int minLength,
			double maxLength, 
			String validationMessage
	) {
		return new InputValidator<String>() {

			@Override
			public String getErrorMessage(String input) {
				return input.length() >= minLength 
						&& input.length() <= maxLength ? DEFAULT_SUCCESS_MESSAGE : validationMessage;
			}
		};
	}
	
	/**
	 * A processor that has the instruction that is based on an object.
	 * @author lucas
	 *
	 * @param <E> the base object for execution
	 */
	public interface Executor<E>{
		void execute(E object);
	}
	
	/**
	 * Represents the multiLine dialog dimensions.
	 * 
	 * @author Lucas Cristovam
	 */
	public enum MultiLineDimension{
		LARGE,
		MEDIUM,
		SMALL
	}
}