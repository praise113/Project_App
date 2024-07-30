public class Budget {
    private String BudgetType;
    private double amount;

    public Budget(String BudgetType, double amount) {
        this.BudgetType = BudgetType;
        this.amount = amount;
    }

    public String getBudgetType() {
        return BudgetType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
