package org.fh.general.ecom.common.enumeration.basics;

public class MongoEnum {


    public enum MongoKey{
        MONGODB_SHOUQUAN("shouquan_invest","管理员授权key");

		private String value;
		private String name;

		public static MongoKey codeOf(String value) {
			for (MongoKey c : MongoKey.values()) {
				if (c.getValue().equals(value)) {
					return c;
				}
			}
			return null;
		}

		MongoKey(String value, String name) {
			this.name = name;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getName() {
			return this.name;
		}

	}
}
