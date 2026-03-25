package panels;

import model.DataStore;
import model.Student;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Dashboard panel showing summary/statistics.
 * 
 * ASSIGNED TO: Student 2 (Main Frame / Dashboard Owner)
 * 
 * TODO for Student 2:
 * - Display total number of students -
 * - Add a welcome message or app logo -
 * - Show summary statistics (e.g., average age, total count)
 * - Add a refresh button to update the stats
 * - Make it visually appealing (use colors, larger fonts, icons)
 */
public class DashboardPanel extends JPanel {
  private JLabel countLabel;
  private JLabel averageLabel;

  public DashboardPanel() {
    setLayout(new BorderLayout());

    //Background


    // Title
    JLabel title = new JLabel("Dashboard", SwingConstants.LEFT);
    title.setFont(new Font("MONOSPACED", Font.BOLD, 25));
    title.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 0));
    add(title, BorderLayout.NORTH);

    // Center content
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    JLabel welcome = new JLabel("WELCOME!");
    welcome.setFont(new Font("Monospaced", Font.PLAIN, 40));
    welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(welcome);

    centerPanel.add(Box.createVerticalStrut(50));

    countLabel = new JLabel("Total Students: " + DataStore.getInstance().getCount());
    countLabel.setFont(new Font("MONOSPACED", Font.PLAIN, 18));
    countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(countLabel);


    averageLabel = new JLabel("Average Age: " + averageData());
    averageLabel.setFont(new Font("MONOSPACED", Font.PLAIN,18));
    averageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(averageLabel);

    centerPanel.add(Box.createVerticalStrut(20));

    JButton refreshBtn = new JButton("Refresh");
    refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    refreshBtn.addActionListener(e -> refreshData());
    centerPanel.add(refreshBtn);

    add(centerPanel, BorderLayout.CENTER);


  }

  private void refreshData() {
    countLabel.setText("Total Students: " + DataStore.getInstance().getCount());
  }
  private int averageData(){
      ArrayList<Student> students;
      int sum = 0;
      for(int i = 0; i < DataStore.getInstance().getCount();i++){
          sum += DataStore.getInstance().getAllStudents().get(i).getAge();
      }
      int average = sum/DataStore.getInstance().getCount();
      return average;
  }
}
