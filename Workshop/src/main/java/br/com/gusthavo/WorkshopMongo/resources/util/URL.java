package br.com.gusthavo.WorkshopMongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	public static String decodeParamName(String name) {
		try {
			return URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
