package model;

/**
 * Data model representing a Student.
 * 
 * ASSIGNED TO: Student 1 (Data Model Owner)
 * 
 * TODO for Student 1:
 * - Add more fields as needed (e.g., email, course, yearLevel, contactNumber)
 * - Add validation logic (e.g., age must be positive, name not empty)
 * - Add a toString() method for display purposes
 * - Add a method to return data as an Object[] array for table display
 */
public class Student {
  private String id;
  private String name;
  private int age;
  private String email, course, contactNum;
  private int yearLvl;

  public Student(String id, String name, int age, String email, String course, int yearLvl, String contactNum) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
    this.course = course;
    this.yearLvl = yearLvl;
    this.contactNum = contactNum;
  }

  // --- Getters ---
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  // --- Setters ---
  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYearLvl() {
        return yearLvl;
    }

    public void setYearLvl(int yearLvl) {
        this.yearLvl = yearLvl;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    @Override
  public String toString() {
    return id + " - " + name + " (Age: " + age + ")";
  }

  /**
   * Returns student data as an Object array, useful for JTable rows.
   */
  public Object[] toTableRow() {
    return new Object[] { id, name, age };
  }
}
