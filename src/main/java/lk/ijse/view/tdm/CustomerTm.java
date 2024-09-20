package lk.ijse.view.tdm;

public class CustomerTm {
    private int id;
    private String name;
    private String address;
    private String tele;

    public CustomerTm() {
    }

    public CustomerTm(int id, String name, String address, String tele) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tele = tele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }
}
