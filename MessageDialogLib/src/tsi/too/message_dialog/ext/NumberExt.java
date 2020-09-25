package tsi.too.message_dialog.ext;

import tsi.too.message_dialog.util.LocaleUtils;

import java.text.NumberFormat;

public abstract class NumberExt {

    public static String toBrazilianCurrency(final Number number) {
        return NumberFormat.getCurrencyInstance(LocaleUtils.getBrazilianLocale()).format(number);
    }
}