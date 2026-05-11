package main.java.r1_collection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

// утилита с графическим интерфейсом, позволяет заменять символы в csv
public class CSVConverterApp extends JFrame {
    private JTextField inputFileField;
    private JTextField outputFileField;
    private JComboBox<String> sourceEncodingBox;
    private JComboBox<String> targetEncodingBox;
    private JTable replacementTable;
    private DefaultTableModel tableModel;
    private JTextArea logArea;

    // Стандартный список кодировок для выпадающих списков
    private static final String[] ENCODINGS = {
            "UTF-8",
            "UTF-16",
            "windows-1251",
            "windows-1252",
            "ISO-8859-1",
            "KOI8-R",
            "Cp866"
    };

    public CSVConverterApp() {
        setTitle("CSV Конвертер с заменой символов и перекодировкой");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Панель выбора файлов и кодировок
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Входной файл
        gbc.gridx = 0; gbc.gridy = 0;
        topPanel.add(new JLabel("Входной CSV:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        inputFileField = new JTextField();
        topPanel.add(inputFileField, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        JButton inputBrowseButton = new JButton("Обзор...");
        inputBrowseButton.addActionListener(this::browseInputFile);
        topPanel.add(inputBrowseButton, gbc);

        // Выходной файл
        gbc.gridx = 0; gbc.gridy = 1;
        topPanel.add(new JLabel("Выходной файл:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        outputFileField = new JTextField();
        topPanel.add(outputFileField, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        JButton outputBrowseButton = new JButton("Обзор...");
        outputBrowseButton.addActionListener(this::browseOutputFile);
        topPanel.add(outputBrowseButton, gbc);

        // Исходная кодировка
        gbc.gridx = 0; gbc.gridy = 2;
        topPanel.add(new JLabel("Исходная кодировка:"), gbc);
        gbc.gridx = 1;
        sourceEncodingBox = new JComboBox<>(ENCODINGS);
        sourceEncodingBox.setEditable(true);
        sourceEncodingBox.setSelectedItem("UTF-8");
        topPanel.add(sourceEncodingBox, gbc);

        // Целевая кодировка
        gbc.gridx = 0; gbc.gridy = 3;
        topPanel.add(new JLabel("Целевая кодировка:"), gbc);
        gbc.gridx = 1;
        targetEncodingBox = new JComboBox<>(ENCODINGS);
        targetEncodingBox.setEditable(true);
        targetEncodingBox.setSelectedItem("windows-1251");
        topPanel.add(targetEncodingBox, gbc);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Панель правил замены
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Правила замены текста (применяются последовательно)"));

        // Таблица с правилами
        tableModel = new DefaultTableModel(new Object[]{"Искать", "Заменить на"}, 0);
        replacementTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(replacementTable);
        centerPanel.add(tableScroll, BorderLayout.CENTER);

        // Кнопки управления правилами
        JPanel ruleButtonPanel = getJPanel();
        centerPanel.add(ruleButtonPanel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Нижняя панель с кнопкой конвертации и логами
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));

        JButton convertButton = new JButton("Конвертировать");
        convertButton.addActionListener(this::performConversion);
        bottomPanel.add(convertButton, BorderLayout.NORTH);

        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(logArea);
        bottomPanel.add(logScroll, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JPanel getJPanel() {
        JPanel ruleButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addRuleButton = new JButton("Добавить правило");
        addRuleButton.addActionListener(e -> tableModel.addRow(new Object[]{"", ""}));
        JButton removeRuleButton = new JButton("Удалить правило");
        removeRuleButton.addActionListener(e -> {
            int selectedRow = replacementTable.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Выберите правило для удаления");
            }
        });
        ruleButtonPanel.add(addRuleButton);
        ruleButtonPanel.add(removeRuleButton);
        return ruleButtonPanel;
    }

    private void browseInputFile(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputFileField.setText(chooser.getSelectedFile().getAbsolutePath());
            // По умолчанию предложить выходной файл в той же папке с суффиксом _converted
            File input = chooser.getSelectedFile();
            String name = input.getName();
            int dot = name.lastIndexOf('.');
            String base = (dot > 0) ? name.substring(0, dot) : name;
            String ext = (dot > 0) ? name.substring(dot) : ".csv";
            File output = new File(input.getParent(), base + "_converted" + ext);
            outputFileField.setText(output.getAbsolutePath());
        }
    }

    private void browseOutputFile(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            outputFileField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void performConversion(ActionEvent e) {
        String inputPath = inputFileField.getText().trim();
        String outputPath = outputFileField.getText().trim();
        if (inputPath.isEmpty() || outputPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Укажите входной и выходной файлы");
            return;
        }

        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        if (!inputFile.exists()) {
            JOptionPane.showMessageDialog(this, "Входной файл не существует");
            return;
        }

        // Проверка кодировок
        String sourceEnc = (String) sourceEncodingBox.getSelectedItem();
        String targetEnc = (String) targetEncodingBox.getSelectedItem();
        try {
            Charset.forName(sourceEnc);
            Charset.forName(targetEnc);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Некорректное имя кодировки: " + ex.getMessage());
            return;
        }

        // Сбор правил замены
        List<ReplacementRule> rules = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String search = (String) tableModel.getValueAt(i, 0);
            String replace = (String) tableModel.getValueAt(i, 1);
            if (search != null && !search.isEmpty()) {
                rules.add(new ReplacementRule(search, replace != null ? replace : ""));
            }
        }

        logArea.setText("");
        appendLog("Начинаем конвертацию...");
        appendLog("Входной файл: " + inputPath);
        appendLog("Выходной файл: " + outputPath);
        appendLog("Исходная кодировка: " + sourceEnc);
        appendLog("Целевая кодировка: " + targetEnc);
        appendLog("Правил замены: " + rules.size());

        // Выполнение конвертации в отдельном потоке, чтобы не блокировать GUI
        SwingWorker<Void, String> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(inputFile), sourceEnc));
                     BufferedWriter writer = new BufferedWriter(
                             new OutputStreamWriter(new FileOutputStream(outputFile), targetEnc))) {

                    String line;
                    long lineCount = 0;
                    while ((line = reader.readLine()) != null) {
                        String processedLine = applyReplacements(line, rules);
                        writer.write(processedLine);
                        writer.newLine();               // Сохраняем перевод строки
                        lineCount++;
                        if (lineCount % 1000 == 0) {
                            publish("Обработано строк: " + lineCount);
                        }
                    }
                    publish("Конвертация завершена. Всего строк: " + lineCount);
                } catch (IOException ex) {
                    publish("ОШИБКА: " + ex.getMessage());
                    throw ex;
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String msg : chunks) {
                    appendLog(msg);
                }
            }

            @Override
            protected void done() {
                try {
                    get();
                    appendLog("Готово.");
                } catch (Exception ex) {
                    appendLog("Сбой при выполнении: " + ex.getMessage());
                    JOptionPane.showMessageDialog(CSVConverterApp.this,
                            "Ошибка конвертации: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private String applyReplacements(String text, List<ReplacementRule> rules) {
        for (ReplacementRule rule : rules) {
            text = text.replace(rule.search, rule.replace);
        }
        return text;
    }

    private void appendLog(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    // Вспомогательный класс для правила замены
        private record ReplacementRule(String search, String replace) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new CSVConverterApp().setVisible(true);
        });
    }
}
