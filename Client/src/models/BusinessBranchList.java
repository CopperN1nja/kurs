package models;

public class BusinessBranchList {
    private int id_branch;
    private String name;

    public BusinessBranchList(int id_branch, String name) {
        this.id_branch = id_branch;
        this.name = name;
    }

    public int getId_branch() {
        return id_branch;
    }

    public void setId_branch(int id_branch) {
        this.id_branch = id_branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
