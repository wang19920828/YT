package org.fh.general.ecom.common.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ObjectSet implements Serializable {
	private static final long serialVersionUID = 6383016470981438253L;
	private int status;
	private String message;
	private Map<String, Object> datas;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectSet() {
		this.datas = new ConcurrentHashMap();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectSet(int initsize) {
		this.datas = new ConcurrentHashMap(initsize);
	}

	public ObjectSet(Map<String, Object> datas) {
		this.datas = datas;
	}

	public void add(String name, Object value) {
		this.datas.put(name, value);
	}

	public void remove(String name) {
		this.datas.remove(name);
	}

	public void clear() {
		this.datas.clear();
	}

	public int getStatus() {
		return this.status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setStatus(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getInt(String name) {
		return getInt(name, 0);
	}

	public int getInt(String name, int defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return ((Integer) this.datas.get(name)).intValue();
	}

	public long getLong(String name) {
		return getLong(name, 0L);
	}

	public long getLong(String name, long defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return ((Long) this.datas.get(name)).longValue();
	}

	public String getString(String name) {
		return getString(name, "");
	}

	public String getString(String name, String defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return (String) this.datas.get(name);
	}

	public float getFloat(String name) {
		return getFloat(name, 0.0F);
	}

	public float getFloat(String name, float defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return ((Float) this.datas.get(name)).floatValue();
	}

	public double getDouble(String name) {
		return getDouble(name, 0.0D);
	}

	public double getDouble(String name, double defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return ((Double) this.datas.get(name)).doubleValue();
	}

	public boolean getBoolean(String name) {
		return getBoolean(name, false);
	}

	public boolean getBoolean(String name, boolean defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return ((Boolean) this.datas.get(name)).booleanValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> getList(String name) {
		return getList(name, new ArrayList());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> getList(String name, List<T> defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return (List) this.datas.get(name);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <K, V> Map<K, V> getMap(String name) {
		return getMap(name, new ConcurrentHashMap());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <K, V> Map<K, V> getMap(String name, Map<K, V> defaultValue) {
		if (!this.datas.containsKey(name)) {
			return defaultValue;
		}

		return (Map) this.datas.get(name);
	}

	@SuppressWarnings("rawtypes")
	public String toString() {
		StringBuffer buf = new StringBuffer();

		for (Map.Entry entry : this.datas.entrySet()) {
			buf.append((String) entry.getKey()).append("=");
			buf.append(entry.getValue()).append(", ");
		}

		buf.delete(buf.length() - 2, buf.length());

		return buf.toString();
	}

	@SuppressWarnings("rawtypes")
	public String toMYString() {
		StringBuffer buf = new StringBuffer();

		for (Map.Entry entry : this.datas.entrySet()) {
			buf.append((String) entry.getKey()).append("=");
			buf.append(entry.getValue()).append(",");
		}

		buf.deleteCharAt(buf.length() - 1);

		return buf.toString();
	}

	@SuppressWarnings("rawtypes")
	public String toXMLString() {
		StringBuffer buf = new StringBuffer();
		buf.append("<dataset>");

		for (Map.Entry entry : this.datas.entrySet()) {
			buf.append("<").append((String) entry.getKey()).append(">");
			buf.append(entry.getValue());
			buf.append("</").append((String) entry.getKey()).append(">");
		}

		buf.append("</dataset>");

		return buf.toString();
	}

	@SuppressWarnings("rawtypes")
	@Deprecated
	public String toJSONString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");

		for (Map.Entry entry : this.datas.entrySet()) {
			buf.append("\"").append((String) entry.getKey()).append("\":");
			buf.append(entry.getValue()).append(",");
		}

		buf.deleteCharAt(buf.length() - 1);
		buf.append("}");

		return buf.toString();
	}

	public Map<String, Object> toMap() {
		return this.datas;
	}

	public void finalize() throws Throwable {
		super.finalize();

		this.datas = null;
	}
}
