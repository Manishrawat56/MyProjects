package com.orixes.expensemanager.bean;

public class ItemInfo {

	private String itemName;
	private String category;
	private String quantity;
	private String toatlPrice;
	
	
	
	
	public ItemInfo(String itemName, String category, String quantity,
			String toatlPrice) {
		super();
		this.itemName = itemName;
		this.category = category;
		this.quantity = quantity;
		this.toatlPrice = toatlPrice;
		
	}
	@Override
	public String toString() {
		return "ItemInfo [itemName=" + itemName + ", category=" + category
				+ ", quantity=" + quantity + ", toatlPrice=" + toatlPrice + "]";
	}
	public String getItemName() {
		return itemName;
	}
	public String getCategory() {
		return category;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getToatlPrice() {
		return toatlPrice;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public void setToatlPrice(String toatlPrice) {
		this.toatlPrice = toatlPrice;
	}
	
}
