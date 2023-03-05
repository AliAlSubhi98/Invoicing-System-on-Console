package src;

public class Shop {
    private String shopName;
    private String tel;
    private String fax;
    private String email;
    private String website;

    public Shop(String shopName, String tel, String fax, String email, String website) {
        this.shopName = shopName;
        this.tel = tel;
        this.fax = fax;
        this.email = email;
        this.website = website;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}


