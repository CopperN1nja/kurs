package models;

public class TaxBranchList {
    private String name_branch;
    private String name_tax;

    public TaxBranchList(String name_branch, String name_tax) {
        this.name_branch = name_branch;
        this.name_tax = name_tax;
    }

    public String getName_branch() {
        return name_branch;
    }

    public void setName_branch(String name_branch) {
        this.name_branch = name_branch;
    }

    public String getName_tax() {
        return name_tax;
    }

    public void setName_tax(String name_tax) {
        this.name_tax = name_tax;
    }
}
