package com.duotin.util;

public final class BitUtils {

	private final static int[] INTEGER_MASKS = new int[32];
	static {
		for (int i = 0; i < 32; i++) {
			INTEGER_MASKS[i] = (1 << i);
		}
	}

	private final static long[] LONG_MASKS = new long[64];
	static {
		for (int i = 0; i < 64; i++) {
			LONG_MASKS[i] = (1l << i);
		}
	}

	public static boolean is(int value, int index) {
		return index > -1 && index < 32 && (value & INTEGER_MASKS[index]) != 0;
	}

	public static int set(int value, int index) {
		return index > -1 && index < 32 ? value | INTEGER_MASKS[index] : value;
	}

	public static int clear(int value, int index) {
		return index > -1 && index < 32 ? value & (-1 ^ INTEGER_MASKS[index]) : value;
	}

	public static boolean is(long value, int index) {
		return index > -1 && index < 64 && (value & LONG_MASKS[index]) != 0;
	}

	public static long set(long value, int index) {
		return index > -1 && index < 64 ? value | LONG_MASKS[index] : value;
	}

	public static long clear(long value, int index) {
		return index > -1 && index < 64 ? value & (-1l ^ LONG_MASKS[index]) : value;
	}
}
