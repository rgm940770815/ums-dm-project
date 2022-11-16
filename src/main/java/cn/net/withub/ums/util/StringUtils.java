package cn.net.withub.ums.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class StringUtils {
	
	public static final String STRING_0 = "0";
	public static final String STRING_1 = "1";
	public static final String STRING_2 = "2";
	public static final String STRING_3 = "3";
	public static final String STRING_4 = "4";
	public static final String STRING_5 = "5";
	public static final String STRING_6 = "6";
	public static final String STRING_7 = "7";
	public static final String STRING_8 = "8";
	public static final String STRING_9 = "9";
	public static final String STRING_10 = "10";
	public static final String STRING_7000 = "7000";
	public static final String STRING_8000 = "8000";
	public static final String STRING_9999 = "9999";
	public static final String STRING_EMPTY = "";
	public static final String STRING_DESC = "DESC";
	public static final String STRING_ASC = "ASC";
	
	private static Log log = LogFactory.getLog(StringUtils.class);
	private static MessageDigest digest = null;

	/**
	 * 
	 * @param str
	 * @param args
	 * @return
	 */
	public static String format(String str, Object... args) {
		if (str == null || STRING_EMPTY.equals(str)) {
			return STRING_EMPTY;
		}
		if (args.length == 0) {
			return str;
		}

		String result = str;

		Pattern p = Pattern.compile("\\{(\\d+)\\}");
		Matcher m = p.matcher(str);
		while (m.find()) {
			int index = Integer.parseInt(m.group(1));
			if (index < args.length) {
				result = result.replace(m.group(), args[index].toString());
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static Integer strFormatInt(String str) {
		return strFormatInt(str, null);
	}
	
	/**
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static Integer strFormatInt(String str, Integer defaultValue) {
		Integer returnInt = null;
		if (isNotEmpty(str)) {
			try {
				returnInt = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				returnInt = defaultValue;
				log.error("String format to Integer error. String Value = " + str);
			}
		} else {
			returnInt = defaultValue;
		}
		return returnInt;
	}

	public static String hash(String data) {
		if (digest == null)
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err
						.println("Failed to load the MD5 MessageDigest. We will be unable to function normally.");
				nsae.printStackTrace();
			}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	private static String encodeHex(byte bytes[]) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}


	public static List getMatches(List list, String prefix) {
		String prefix_upper = prefix.toUpperCase();
		List matches = new ArrayList();
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			String name_upper = name.toUpperCase();
			if (name_upper.startsWith(prefix_upper)) {
				matches.add(name);
			}
		}
		return matches;
	}

	public static Map<Character, Integer> getStatistics(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int i, size = s.length();
		for (i = 0; i < size; i++) {
			char c = s.charAt(i);
			map.put(c, map.containsKey(c) ? ((Integer) map.get(c) + 1) : 1);
		}
		return map;
	}

	
	public static String addSplit(String[] array, String split) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length - 1; i++)
			sb.append(array[i] + split);
		sb.append(array[array.length - 1]);
		return sb.toString();
	}

	
	public static String hexEncode(String str) {
		String hexString = null;
		if (str != null && str.length() > 0) {
			char[] digital = "0123456789ABCDEF".toCharArray();
			StringBuffer sb = new StringBuffer("");
			try {
				byte[] bs = str.getBytes("utf-8");
				int bit;
				for (int i = 0; i < bs.length; i++) {
					bit = (bs[i] & 0x0f0) >> 4;
					sb.append(digital[bit]);
					bit = bs[i] & 0x0f;
					sb.append(digital[bit]);
				}
			} catch (Exception e) {
			}
			hexString = sb.toString();
		}

		return hexString;
	}


	public static String HexDecode(String hexString) {
		String str = null;
		if (hexString != null && hexString.length() > 0) {
			String digital = "0123456789ABCDEF";
			char[] hex2char = hexString.toCharArray();
			byte[] bytes = new byte[hexString.length() / 2];
			int temp;
			for (int i = 0; i < bytes.length; i++) {
				temp = digital.indexOf(hex2char[2 * i]) * 16;
				temp += digital.indexOf(hex2char[2 * i + 1]);
				bytes[i] = (byte) (temp & 0xff);
			}
			try {
				str = new String(bytes, "utf-8");
			} catch (Exception e) {
			}
		}
		return str;
	}

	
	public static boolean isEmpty(String str) {
		return (null == str || str.trim().length() == 0 || "null"
				.equalsIgnoreCase(str.trim()));
	}
	
	public static boolean isEmpty(Object obj) {
		return isEmpty(obj == null ? "" : String.valueOf(obj));
	}
	
	/**
	 * 字符串是否不等于NULL, NULL 返回false
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj == null ? "" : String.valueOf(obj));
	}
	
	public static String getLimitLengthString(String str, int length) {
		String view = null;
		int counterOfDoubleByte = 0;
		byte b[];
		if (str == null) {
			return "";
		} else {
			try {
				b = str.getBytes("GBK");
				if (b.length <= length)
					return str;
				for (int i = 0; i < length; i++) {
					if (b[i] > 0)
						counterOfDoubleByte++;
				}
				if (counterOfDoubleByte % 2 == 0)
					view = new String(b, 0, length, "GBK") + "...";
				else
					view = new String(b, 0, length - 1, "GBK") + "...";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return view;
		}
	}

	
	public static boolean isBlankObject(Object o) {
		if (null == o || "".equals(o)) {
			return false;
		}
		String s = o.toString();
		if ("".equals(s) || 0 == s.length()) {
			return false;
		}
		if ("".equals(s.trim())) {
			return false;
		}
		return true;
	}
	public static String getNull2EmptyString(Object str) {
		String string = "";
		try {
			string = str == null ? "" : ((String[]) str)[0];
		} catch (Exception e) {
		}
		return string;
	}
	
	
	
	public static String getInStr(String[] strArray){
		StringBuffer returnStr = new StringBuffer();
		
		if(strArray != null)
		for(String s : strArray){
			returnStr.append("'"+s+"',");
		}
		
		return returnStr.deleteCharAt(returnStr.length()-1).toString();
	}


	
}
