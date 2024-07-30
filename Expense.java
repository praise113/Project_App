public class Expense {
    private String BudgetType;
    private double amount;

    public Expense(String BudgetType, double amount) {
        this.BudgetType = BudgetType;
        this.amount = amount;
    }

    public String getBudgetType() {
        return BudgetType;
    }

    public double getAmount() {
        return amount;
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }
}
