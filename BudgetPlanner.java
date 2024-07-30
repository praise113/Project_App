import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class BudgetPlanner {
    private BudgetManager budgetManager;

    public BudgetPlanner() {
        this.budgetManager = new BudgetManager();
    }

    public static void main(String[] args) {
        BudgetPlanner planner = new BudgetPlanner();
        planner.run();
    }

    public void run() {
        while (true) {
            String[] options = {"Set Budget", "Add Expense", "Edit Budget", "Remove BudgetType",
                    "View Summary", "View Expense Breakdown", "Set Savings Goal", "Export Summary", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Budget Planner",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    setBudget();
                    break;
                case 1:
                    addExpense();
                    break;
                case 2:
                    editBudget();
                    break;
                case 3:
                    removeBudgetType();
                    break;
                case 4:
                    viewSummary();
                    break;
                case 5:
                    viewExpenseBreakdown();
                    break;
                case 6:
                    setSavingsGoal();
                    break;
                case 7:
                    exportSummary();
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Exiting Budget Planner");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }

    private void setBudget() {
        String BudgetType = JOptionPane.showInputDialog("Enter BudgetType:");
        if (BudgetType != null && !BudgetType.isEmpty()) {
            String budgetStr = JOptionPane.showInputDialog("Enter budget for " + BudgetType + ":");
            try {
                double budgetAmount = Double.parseDouble(budgetStr);
                budgetManager.setBudget(BudgetType, budgetAmount);
                JOptionPane.showMessageDialog(null, "Budget set for " + BudgetType);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid budget amount. Enter a number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "BudgetType cannot be empty.");
        }
    }

    private void addExpense() {
        String BudgetType = JOptionPane.showInputDialog("Enter BudgetType:");
        if (BudgetType != null && !BudgetType.isEmpty() && budgetManager.getBudgets().containsKey(BudgetType)) {
            String expenseStr = JOptionPane.showInputDialog("Enter expense amount for " + BudgetType + ":");
            try {
                double expenseAmount = Double.parseDouble(expenseStr);
                budgetManager.addExpense(BudgetType, expenseAmount);
                JOptionPane.showMessageDialog(null, "Expense added for " + BudgetType);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid expense amount. Enter a number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "BudgetType does not exist or is empty.");
        }
    }

    private void editBudget() {
        String BudgetType = JOptionPane.showInputDialog("Enter BudgetType to edit:");
        if (BudgetType != null && !BudgetType.isEmpty() && budgetManager.getBudgets().containsKey(BudgetType)) {
            String newBudgetStr = JOptionPane.showInputDialog("Enter new budget for " + BudgetType + ":");
            try {
                double newBudgetAmount = Double.parseDouble(newBudgetStr);
                budgetManager.editBudget(BudgetType, newBudgetAmount);
                JOptionPane.showMessageDialog(null, "Budget updated for " + BudgetType);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid budget amount. Enter a number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "BudgetType does not exist or is empty.");
        }
    }

    private void removeBudgetType() {
        String BudgetType = JOptionPane.showInputDialog("Enter BudgetType to remove:");
        if (BudgetType != null && !BudgetType.isEmpty() && budgetManager.getBudgets().containsKey(BudgetType)) {
            budgetManager.removeBudgetType(BudgetType);
            JOptionPane.showMessageDialog(null, "BudgetType removed: " + BudgetType);
        } else {
            JOptionPane.showMessageDialog(null, "BudgetType does not exist or is empty.");
        }
    }

    private void viewSummary() {
        StringBuilder summary = new StringBuilder("Budget Summary: ");
        for (String BudgetType : budgetManager.getBudgets().keySet()) {
            Budget budget = budgetManager.getBudgets().get(BudgetType);
            Expense expense = budgetManager.getExpenses().get(BudgetType);
            double budgetAmount = budget.getAmount();
            double spentAmount = expense.getAmount();
            summary.append(String.format("BudgetType: %s, Budget: %.2f, Spent: %.2f, Remaining: %.2f ",
                    BudgetType, budgetAmount, spentAmount, budgetAmount - spentAmount));
        }
        JOptionPane.showMessageDialog(null, summary.toString());
    }

    private void viewExpenseBreakdown() {
        StringBuilder breakdown = new StringBuilder("Expense Breakdown: ");
        for (String BudgetType : budgetManager.getExpenses().keySet()) {
            Expense expense = budgetManager.getExpenses().get(BudgetType);
            breakdown.append(String.format("BudgetType: %s, Spent: %.2f ", BudgetType, expense.getAmount()));
        }
        JOptionPane.showMessageDialog(null, breakdown.toString());
    }

    private void setSavingsGoal() {
        String goalStr = JOptionPane.showInputDialog("Enter savings goal:");
        try {
            double goalAmount = Double.parseDouble(goalStr);
            budgetManager.setSavingsGoal(goalAmount);
            JOptionPane.showMessageDialog(null, "Savings goal set: " + goalAmount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a number.");
        }
    }

    private void exportSummary() {
        try (FileWriter writer = new FileWriter("budget_summary.txt")) {
            StringBuilder summary = new StringBuilder("Budget Summary: ");
            for (String BudgetType : budgetManager.getBudgets().keySet()) {
                Budget budget = budgetManager.getBudgets().get(BudgetType);
                Expense expense = budgetManager.getExpenses().get(BudgetType);
                double budgetAmount = budget.getAmount();
                double spentAmount = expense.getAmount();
                summary.append(String.format("BudgetType: %s, Budget: %.2f, Spent: %.2f, Remaining: %.2f ",
                        BudgetType, budgetAmount, spentAmount, budgetAmount - spentAmount));
            }
            writer.write(summary.toString());
            JOptionPane.showMessageDialog(null, "Summary exported to budget_summary.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error exporting summary: " + e.getMessage());
        }
    }
}
