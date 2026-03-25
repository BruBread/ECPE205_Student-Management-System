package panels;

import model.DataStore;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

/**
 * Panel for viewing all students in a table.
 * 
 * ASSIGNED TO: Student 4 (View / List Feature Owner)
 * 
 * TODO for Student 4:
 * - Add more columns to match all Student fields
 * - Add a refresh button to reload data
 * - Add sorting functionality (click column headers)
 * - Improve table styling (row colors, column widths)
 * - Show "No records found" when the list is empty
 * - Optionally add pagination if the list is long
 */
public class ViewStudentsPanel extends JPanel {
  private DefaultTableModel tableModel;
  private JTable table;
  //t

  public ViewStudentsPanel() {
    setLayout(new BorderLayout());

    // Title
    JLabel title = new JLabel("All Students", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
    add(title, BorderLayout.NORTH);

    // Table
      String[] columns = { "Student ID", "Name", "Age", "Email", "Course", "Year Level", "Contact" };
    tableModel = new DefaultTableModel(columns, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Read-only table
      }


    };
      table = new JTable(tableModel) {
          @Override
          public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
              Component c = super.prepareRenderer(renderer, row, column);

              if (isRowSelected(row)) {

                  c.setBackground(getSelectionBackground());
              } else if (row % 2 == 0) {
                  c.setBackground(new Color(180, 217, 200));
              } else {
                  c.setBackground(Color.WHITE);
              }

              return c;
          }
      };

    table.setRowHeight(25);
    table.getTableHeader().setReorderingAllowed(false);

    table.setAutoCreateRowSorter(true);


    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    add(scrollPane, BorderLayout.CENTER);

    // Refresh button
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
    JButton refreshBtn = new JButton("Refresh List");
    refreshBtn.addActionListener(e -> loadData());
    buttonPanel.add(refreshBtn);
    add(buttonPanel, BorderLayout.SOUTH);

    // Load initial data
    loadData();
  }

  private void loadData() {
    tableModel.setRowCount(0); // Clear table
    List<Student> students = DataStore.getInstance().getAllStudents();

    if (students.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No records found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    for (Student s : students) {
      tableModel.addRow(new Object[] {
              s.getId(),
              s.getName(),
              s.getAge(),
              s.getEmail(),
              s.getCourse(),
              s.getYearLvl(),
              s.getContactNum()
      });
    }
  }
}
