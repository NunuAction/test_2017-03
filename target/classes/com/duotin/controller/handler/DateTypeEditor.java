package com.duotin.controller.handler;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author ganlv
 */
public class DateTypeEditor extends PropertyEditorSupport {
	public static final DateFormat DF_LONG = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
	private static final List<DateFormat> DATE_FORMAT = new ArrayList<DateFormat>();
	public static final int SHORT_DATE = 10;
	static {
		DATE_FORMAT.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		DATE_FORMAT.add(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
		DATE_FORMAT.add(new SimpleDateFormat("yyyy-MM-dd HH"));
		DATE_FORMAT.add(new SimpleDateFormat("yyyy-MM-dd"));
		DATE_FORMAT.add(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US));
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		for (DateFormat df : DATE_FORMAT) {
			try {
					setValue(df.parse(text));
					break;
			} catch (ParseException ex) {
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat
	 */
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DF_SHORT.format(value) : "");
	}

}
