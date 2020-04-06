package com.daloji.cachegrid.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.util.Objects.nonNull;

import java.awt.RenderingHints.Key;

public class Utils {

	public static <T> String  generateKey(AspectParam<T> param){
		String key = null;
		Object[] paramvalue = param.getParamValue();
		byte[] data = null;
		try {
			for(Object obj:paramvalue) {
				System.out.println(obj);
				byte[] dataArgs;
				dataArgs = Utils.toByteArray(obj);
				if(nonNull(data)) {
					byte[] tmp =  new byte[data.length + dataArgs.length];
					System.arraycopy(data, 0, tmp, 0, data.length);
					System.arraycopy(dataArgs, 0, tmp, data.length, dataArgs.length);
					data=tmp;
				}else {
					data =dataArgs;
				}
			}
			if(nonNull(data)) {
				data = sha256(data);	
			}

		} catch (IOException e) {
			data = null;
		}
		return bytesToHex(data);
	}


	public static byte[] sha256(byte[] input) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.update(input, 0, input.length);
			return digest.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static  byte[] toByteArray(Object obj) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		} finally {
			if (oos != null) {
				oos.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return bytes;
	}


	public static String bytesToHex(byte[] bytes) {
		final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

}
