package com.airtonmiranda.administracaocompras.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String value){
		return Arrays.asList(value.split(",")).stream()
				.map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
