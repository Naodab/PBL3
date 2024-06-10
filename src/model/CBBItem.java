package model;

import java.util.Objects;

public class CBBItem {
	private int value;
	private String text;

	public CBBItem(int value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CBBItem other = (CBBItem) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
