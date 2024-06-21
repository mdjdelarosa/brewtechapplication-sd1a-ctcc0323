package brewtech;
public class OrderItemData {
    private String itemName;
    private String size;
    private String addons;
    private double totalPrice;

    public OrderItemData(String itemName, String size, String addons, double totalPrice) {
        this.itemName = itemName;
        this.size = size;
        this.addons = addons;
        this.totalPrice = totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSize() {
        return size;
    }

    public String getAddons() {
        return addons;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

