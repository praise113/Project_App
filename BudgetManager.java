import java.util.HashMap;
import java.util.Map;

public class BudgetManager {
    private Map<String, Budget> budgets;
    private Map<String, Expense> expenses;
    private double savingsGoal;

    public BudgetManager() {
        this.budgets = new HashMap<>();
        this.expenses = new HashMap<>();
        this.savingsGoal = 0.0;
    }

    public void setBudget(String BudgetType, double amount) {
        budgets.put(BudgetType, new Budget(BudgetType, amount));
        expenses.putIfAbsent(BudgetType, new Expense(BudgetType, 0.0));
    }

    public void addExpense(String BudgetType, double amount) {
        if (expenses.containsKey(BudgetType)) {
            expenses.get(BudgetType).addAmount(amount);
        }
    }

    public void editBudget(String BudgetType, double newAmount) {
        if (budgets.containsKey(BudgetType)) {
            budgets.get(BudgetType).setAmount(newAmount);
        }
    }

    public void removeBudgetType(String BudgetType) {
        budgets.remove(BudgetType);
        expenses.remove(BudgetType);
    }

    public Map<String, Budget> getBudgets() {
        return budgets;
    }

    public Map<String, Expense> getExpenses() {
        return expenses;
    }

    public void setSavingsGoal(double amount) {
        this.savingsGoal = amount;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }
}
