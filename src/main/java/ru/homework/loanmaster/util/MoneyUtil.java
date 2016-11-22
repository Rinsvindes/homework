package ru.homework.loanmaster.util;

import com.google.common.base.Strings;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.money.format.MoneyAmountStyle;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

public class MoneyUtil {
    public static final int DEFAULT_CCY = 643;
    public static final String AMOUNT_CCY_SEPARATOR = " ";

    private static MoneyFormatter moneyReadFormatter = new MoneyFormatterBuilder()
            .appendAmount(MoneyAmountStyle.ASCII_DECIMAL_COMMA_NO_GROUPING)
            .appendLiteral(AMOUNT_CCY_SEPARATOR)
            .appendCurrencyNumericCode().toFormatter();

    private static MoneyFormatter moneyAmountFormatterWithDot = new MoneyFormatterBuilder()
            .appendAmount(MoneyAmountStyle.ASCII_DECIMAL_POINT_NO_GROUPING).toFormatter();

    public static Money toMoney(String amount, String numericCcy) {
        if (Strings.isNullOrEmpty(amount)) return null;
        if (Strings.isNullOrEmpty(numericCcy)) {
            numericCcy = String.valueOf(DEFAULT_CCY);
        }
        amount = amount.replace(".", ",");
        String formattedMoney = amount + AMOUNT_CCY_SEPARATOR + numericCcy;
        return moneyReadFormatter.parseMoney(formattedMoney);
    }

    public static Money toMoney(Long amount, Integer ccy) {
        if (amount == null) return null;
        if (ccy == null) ccy = DEFAULT_CCY;
        return Money.ofMinor(CurrencyUnit.ofNumericCode(ccy), amount);
    }

    public static String toFormattedAmountWithDot(Money money) {
        if (money == null) return null;
        return moneyAmountFormatterWithDot.print(money);
    }
}
