package panels;

import model.DataStore;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Panel for searching students by ID or name.
 * 
 * ASSIGNED TO: Student 5 (Search Feature Owner)
 * 
 * TODO for Student 5:
 * - Implement search by name (partial match / contains)
 * - Add search filter options (search by ID, by name, by course, etc.)
 * - Display results in a table or formatted list
 * - Handle case-insensitive search
 * - Show "No results found" message when appropriate
 * - Add a "Clear Search" button
 */
public class SearchStudentPanel extends JPanel {
  private JTextField searchField;
  private DefaultTableModel tableModel;
  private JTable table;
  private List<Student> results = new ArrayList<>();

  public SearchStudentPanel() {
    setLayout(new BorderLayout());

    // Title
    JLabel title = new JLabel("Search Student", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
    add(title, BorderLayout.NORTH);

    // Search bar
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    searchPanel.add(new JLabel("Search by ID/AGE/NAME/COURSE/EMAIL:"));
    searchField = new JTextField(20);
    searchPanel.add(searchField);

    JButton searchBtn = new JButton("Search");
    searchBtn.addActionListener(e -> performSearch());
    searchPanel.add(searchBtn);

    JButton clearBtn = new JButton("Clear");
    clearBtn.addActionListener(e -> {
      results.clear();
      tableModel.setRowCount(0);
      searchField.setText("");

    });
    searchPanel.add(clearBtn);

    add(searchPanel, BorderLayout.NORTH);

    // Results area
    JPanel resultsWrapper = new JPanel(new BorderLayout());
    resultsWrapper.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

    JLabel resultsLabel = new JLabel("Results:");
    resultsLabel.setFont(new Font("Arial", Font.BOLD, 14));
    resultsWrapper.add(resultsLabel, BorderLayout.NORTH);

    String[] columns = { "Student ID", "Name", "Age", "Email", "Course", "Year level","Contact #" };
    tableModel = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
              return false; // Read-only table
          }
    };
    table = new JTable(tableModel);
    table.setRowHeight(25);
    table.getTableHeader().setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    resultsWrapper.add(scrollPane, BorderLayout.CENTER);



//    resultArea = new JTextArea();
//    resultArea.setEditable(false);
//    resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//    JScrollPane scrollPane = new JScrollPane(resultArea);
//    resultsWrapper.add(scrollPane, BorderLayout.CENTER);

    add(resultsWrapper, BorderLayout.CENTER);
  }


  private void performSearch() {
    String query = searchField.getText().trim().toLowerCase();

    if (query.isEmpty()) {
      JOptionPane.showMessageDialog(this,
          "Please enter a search term.", "Info", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

        List<Student> allStudents = DataStore.getInstance().getAllStudents();
        results = new ArrayList<>();

    for (Student s : allStudents) {
        String ageconvert = String.valueOf(s.getAge());
        String yearlvlconvert = String.valueOf(s.getYearLvl());
      if (s.getId().toLowerCase().contains(query)
          || s.getName().toLowerCase().contains(query) || s.getCourse().contains(query) || s.getEmail().contains(query) || ageconvert.contains(query)
              || yearlvlconvert.contains(query) || s.getContactNum().contains(query)) {
        results.add(s);
      }
    }
    if (results.size() == 0){
        JOptionPane.showMessageDialog(null,"No students found matching: \" " + searchField.getText().trim() + "\"");
    }

//    if (results.isEmpty()) {
//      resultArea.setText("No students found matching: \"" + searchField.getText().trim() + "\"");
//    } else {
//      StringBuilder sb = new StringBuilder();
//      sb.append(String.format("%-15s %-25s %-5s %-30s %-10s %-5s %-20s%n ", "ID", "Name", "Age", "Email", "Course", "Year level","Contact #"));
//      sb.append("-".repeat(110)).append("\n");
//      for (Student s : results) {
//        sb.append(String.format("%-15s %-25s %-5s %-30s %-10s %-10s %-20s%n", s.getId(), s.getName(), s.getAge(), s.getEmail(), s.getCourse(), s.getYearLvl(), s.getContactNum()));
//      }
//      sb.append("\nFound ").append(results.size()).append(" result(s).");
//      resultArea.setText(sb.toString());
//    }
      loadData(results);
  }
  private void loadData(List<Student> results) {

      tableModel.setRowCount(0); // Clear table
      for (Student s : results) {
          tableModel.addRow(s.toTableRow());
      }
  }

}
