import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

class JavaLearningProgress {

    // Phases and Topics
    private static final String[] phases = {
            "Phase 1: Basics of Java",
            "Phase 2: Object-Oriented Programming (OOP)",
            "Phase 3: Intermediate Java",
            "Phase 4: Advanced Java Concepts",
            "Phase 5: Java for Advanced Applications",
            "Phase 6: Frameworks and Tools",
            "Final Phase: Project Development"
    };

    private static final String[][] topics = {
            {
                    "1. Introduction to Java",
                    "   - What is Java?",
                    "   - Features of Java (Platform independence, JVM, JDK, JRE)",
                    "   - Installing Java and setting up the environment",
                    "   - Writing your first 'Hello, World!' program",
                    "2. Java Fundamentals",
                    "   - Data types, Variables, and Constants",
                    "   - Keywords and Identifiers",
                    "   - Input/Output (Scanner and System.out)",
                    "   - Operators (Arithmetic, Relational, Logical)",
                    "   - Typecasting and Type conversion",
                    "3. Control Flow Statements",
                    "   - Decision-making: if, else, switch",
                    "   - Loops: for, while, do-while",
                    "   - break and continue statements",
                    "4. Methods and Functions",
                    "   - Defining methods",
                    "   - Method parameters and return types",
                    "   - Method overloading",
                    "5. Arrays",
                    "   - Single-dimensional arrays",
                    "   - Multi-dimensional arrays",
                    "   - Basic operations (iteration, sorting)"
            },
            {
                    "1. Classes and Objects",
                    "   - Defining classes and objects",
                    "   - Constructors (default, parameterized)",
                    "   - this keyword",
                    "2. Inheritance",
                    "   - Types of inheritance",
                    "   - super keyword",
                    "   - Method overriding",
                    "3. Polymorphism",
                    "   - Method overloading vs overriding",
                    "   - Upcasting and downcasting",
                    "4. Encapsulation",
                    "   - Access modifiers (private, protected, public)",
                    "   - Getters and Setters",
                    "5. Abstraction",
                    "   - Abstract classes and methods",
                    "   - Interfaces",
                    "6. Packages and Access Modifiers",
                    "   - Creating and importing packages",
                    "   - Understanding default, private, protected, public access levels"
            },
            {
                    "1. Exception Handling",
                    "   - try, catch, finally, throw, throws",
                    "   - Checked vs unchecked exceptions",
                    "   - Custom exceptions",
                    "2. Collections Framework",
                    "   - List (ArrayList, LinkedList)",
                    "   - Set (HashSet, TreeSet)",
                    "   - Map (HashMap, TreeMap)",
                    "   - Queue (PriorityQueue, Deque)",
                    "   - Iterators and Streams",
                    "3. Generics",
                    "   - Creating generic classes and methods",
                    "   - Bounded type parameters",
                    "4. String Handling",
                    "   - Immutable Strings and StringBuffer/StringBuilder",
                    "   - String methods and regular expressions"
            },
            {
                    "1. File Handling",
                    "   - Reading and writing files (FileReader, FileWriter)",
                    "   - Serialization and Deserialization",
                    "   - Byte and character streams",
                    "2. Multithreading and Concurrency",
                    "   - Thread lifecycle",
                    "   - Creating threads (Runnable vs Thread class)",
                    "   - Synchronization and Locks",
                    "   - Executors and thread pools",
                    "3. Networking",
                    "   - Basics of Sockets and ServerSocket",
                    "   - URL and HTTP communication",
                    "4. Java Database Connectivity (JDBC)",
                    "   - Connecting to a database",
                    "   - Executing SQL queries",
                    "   - Prepared Statements and ResultSets"
            },
            {
                    "1. Java 8 Features",
                    "   - Lambda expressions",
                    "   - Streams API",
                    "   - Functional interfaces and method references",
                    "   - Default and static methods in interfaces",
                    "2. Annotations",
                    "   - Built-in annotations (@Override, @Deprecated)",
                    "   - Custom annotations",
                    "3. Design Patterns",
                    "   - Singleton, Factory, Builder, Observer, etc.",
                    "4. JavaFX or Swing",
                    "   - Basics of GUI programming",
                    "   - Creating windows and handling events"
            },
            {
                    "1. Maven and Gradle",
                    "   - Dependency management",
                    "   - Build automation",
                    "2. Spring Framework",
                    "   - Spring Core (Dependency Injection)",
                    "   - Spring Boot (Microservices)",
                    "   - Spring Data JPA (Database handling)",
                    "3. Hibernate",
                    "   - Object-Relational Mapping (ORM)",
                    "   - Working with Hibernate Query Language (HQL)",
                    "4. RESTful Web Services",
                    "   - Building REST APIs using Spring Boot",
                    "   - Consuming RESTful services"
            },
            {
                    "1. Mini Projects",
                    "   - Console-based projects like a calculator, library management system",
                    "   - Basic file and database handling projects",
                    "2. Intermediate Projects",
                    "   - CRUD applications with JDBC",
                    "   - Multi-threaded chat application",
                    "3. Advanced Projects",
                    "   - E-commerce platform (Spring Boot + Hibernate)",
                    "   - Social media REST API (Spring Boot + Spring Security)"
            }
    };

    private static final String PROGRESS_FILE = "progress_data.txt"; // File to store progress

    private static int totalTopics;
    private static int completedTopics = 0;
    private static JProgressBar progressBar;
    private static JLabel progressLabel;
    private static Map<String, Boolean> topicStatus = new HashMap<>();

    public static void main(String[] args) {
        // Calculate total number of topics
        for (String[] phaseTopics : topics) {
            totalTopics += phaseTopics.length;
        }

        // Load saved progress
        loadProgress();

        // Create the main frame
        JFrame frame = new JFrame("Algo Thnkrz Progress Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Algo Thnkrz");
        titleLabel.setBounds(400, 10, 400, 40);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel);

        // Scrollable Panel
        JPanel topicPanel = new JPanel();
        topicPanel.setLayout(new BoxLayout(topicPanel, BoxLayout.Y_AXIS));
        topicPanel.setBackground(Color.BLACK);

        for (int i = 0; i < phases.length; i++) {
            JLabel phaseLabel = new JLabel(phases[i]);
            phaseLabel.setForeground(Color.RED);
            phaseLabel.setFont(new Font("Arial", Font.BOLD, 18));
            topicPanel.add(phaseLabel);

            for (String topic : topics[i]) {
                JCheckBox topicCheckBox = new JCheckBox(topic);
                topicCheckBox.setForeground(Color.WHITE);
                topicCheckBox.setBackground(Color.BLACK);
                topicCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));

                if (topic.matches("\\d+\\. .*")) {
                    topicCheckBox.setForeground(Color.MAGENTA); // Highlight numbered topics
                }

                boolean isCompleted = topicStatus.getOrDefault(topic, false);
                topicCheckBox.setSelected(isCompleted);

                topicCheckBox.addActionListener(e -> {
                    boolean isSelected = topicCheckBox.isSelected();
                    if (isSelected && !topicStatus.getOrDefault(topic, false)) {
                        completedTopics++;
                        topicStatus.put(topic, true);
                    } else if (!isSelected && topicStatus.getOrDefault(topic, false)) {
                        completedTopics--;
                        topicStatus.put(topic, false);
                    }
                    updateProgress();
                });

                topicPanel.add(topicCheckBox);
            }

            topicPanel.add(Box.createVerticalStrut(20)); // Add spacing
        }

        JScrollPane scrollPane = new JScrollPane(topicPanel);
        scrollPane.setBounds(50, 80, 1100, 600);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(50, 700, 1100, 30);
        progressBar.setForeground(Color.RED);
        progressBar.setBackground(Color.darkGray);
        frame.add(progressBar);

        // Progress Label
        progressLabel = new JLabel("Progress: 0%");
        progressLabel.setBounds(550, 740, 200, 30);
        progressLabel.setForeground(Color.RED);
        progressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(progressLabel);

        // Reset Button
        JButton resetButton = new JButton("Reset Progress");
        resetButton.setBounds(50, 740, 150, 30);
        resetButton.setBackground(Color.RED);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.addActionListener(e -> resetProgress());
        frame.add(resetButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);

        updateProgress();
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveProgress();
            }
        });
    }

    private static void updateProgress() {
        int progress = (int) ((completedTopics / (double) totalTopics) * 100);
        progressBar.setValue(progress);
        progressLabel.setText("Progress: " + progress + "%");
    }

    private static void loadProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROGRESS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String topic = parts[0].trim();
                    boolean isCompleted = Boolean.parseBoolean(parts[1].trim());
                    topicStatus.put(topic, isCompleted);
                    if (isCompleted) {
                        completedTopics++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No previous progress found. Starting fresh.");
        }
    }

    private static void saveProgress() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROGRESS_FILE))) {
            for (Map.Entry<String, Boolean> entry : topicStatus.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Progress saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving progress.");
        }
    }

    private static void resetProgress() {
        // Reset completed topics and topic status
        completedTopics = 0;
        topicStatus.clear();
        updateProgress();
        // Clear the progress file
        File file = new File(PROGRESS_FILE);
        if (file.exists()) {
            file.delete();
            System.out.println("Progress data reset.");
        }
    }
}
