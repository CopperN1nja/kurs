package models;

public class BusinessmanList {
    private int id_businessman;
    private String firm_name;
    private int firm_unp;
    private String business_branch;
    private int income;
    private double tax;
    private double tax_sum;
    private double profit;
    private int status;

    public BusinessmanList(int id_businessman, String firm_name, int firm_unp, String business_branch, int income, int status) {
        this.id_businessman = id_businessman;
        this.firm_name = firm_name;
        this.firm_unp = firm_unp;
        this.business_branch = business_branch;
        this.income = income;
        this.status = status;
    }

    public BusinessmanList(int id_businessman, String firm_name, int firm_unp, String business_branch, int income, double tax, double tax_sum, double profit, int status) {
        this.id_businessman = id_businessman;
        this.firm_name = firm_name;
        this.firm_unp = firm_unp;
        this.business_branch = business_branch;
        this.income = income;
        this.tax = tax;
        this.tax_sum = tax_sum;
        this.profit = profit;
        this.status = status;
    }

    public int getId_businessman() {
        return id_businessman;
    }

    public void setId_businessman(int id_businessman) {
        this.id_businessman = id_businessman;
    }

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public int getFirm_unp() {
        return firm_unp;
    }

    public void setFirm_unp(int firm_unp) {
        this.firm_unp = firm_unp;
    }

    public String getBusiness_branch() {
        return business_branch;
    }

    public void setBusiness_branch(String business_branch) {
        this.business_branch = business_branch;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTax_sum() {
        return tax_sum;
    }

    public void setTax_sum(double tax_sum) {
        this.tax_sum = tax_sum;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
