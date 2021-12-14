package models;

public class NalogiList {
    private int id_tax;
    private String tax_name;
    private int val_id;

    public NalogiList(int id_tax, String tax_name, int col) {
        this.id_tax = id_tax;
        this.tax_name = tax_name;
        this.val_id = col;
    }

    public int getId_tax() {
        return id_tax;
    }

    public void setId_tax(int id_tax) {
        this.id_tax = id_tax;
    }

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }

    public int getVal_id() {
        return val_id;
    }

    public void setVal_id(int val_id) {
        this.val_id = val_id;
    }
}
