package ua.kenya;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zidd on 3/23/14.
 */
public class Kenya extends JFrame {
    private About about;
    private Products products;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPane1;
    private JTextArea textArea;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private JTable table1;
    private JTable table2;
    private JPanel panel;
    private JPanel computePanel;
    private JButton computeButton;
    private JLabel resultLabel;

    public Kenya() {
        super("Раздельное питание");

        about = new About("About.txt");
        products = new Products("Products4.txt");
        tabbedPane = new JTabbedPane();

        textArea = new JTextArea(about.toString());
        makeMultilineLabel(textArea);
        scrollPane1 = new JScrollPane(textArea);
        tabbedPane.add("О раздельном питании", scrollPane1);

        DefaultTableModel dm1 = new DefaultTableModel() {
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 0: return Integer.class;
                    case 1: return String.class;
                    case 2: return Integer.class;
                    case 3: return String.class;
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dm1.setDataVector(products.getProductsModel(), new Object[]{"№", "Название продукта", "Каллорийность", "Описание"});
        table1 = new JTable(dm1);
        table1.setFillsViewportHeight(true);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table1.setDefaultRenderer(String.class, new MultiLineCellRenderer());
        table1.setAutoCreateRowSorter(true);
        TableColumn column;
        for(int i = 0; i < 4; i++) {
            column = table1.getColumnModel().getColumn(i);
            if(i == 0) column.setPreferredWidth(10);
            if(i == 1) column.setPreferredWidth(200);
            if(i == 2) column.setPreferredWidth(75);
            if(i == 3) column.setPreferredWidth(1000);
            column.setResizable(false);
        }
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.setFont(new Font(table1.getFont().getFontName(), table1.getFont().getStyle(), 1 + table1.getFont().getSize()));
        scrollPane2 = new JScrollPane(table1);
        tabbedPane.add("Таблица продуктов", scrollPane2);

        DefaultTableModel dm2 = new DefaultTableModel() {
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0) return String.class;
                else return Boolean.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 1) return true;
                return false;
            }
        };
        dm2.setDataVector(products.getProductsComputeModel(), new Object[]{"Название продукта", "Включить в расчёт"});
        table2 = new JTable(dm2);
        table2.setFillsViewportHeight(true);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table2.setFont(new Font(table2.getFont().getFontName(), table2.getFont().getStyle(), 1 + table2.getFont().getSize()));
        scrollPane3 = new JScrollPane(table2);
        computeButton = new JButton("Рассчитать каллорийность");
        resultLabel = new JLabel("");
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> selectedList = new ArrayList<Integer>();
                int rowCount = table2.getRowCount();
                for(int i = 0; i < rowCount; i++) {
                    Object valueAt = table2.getModel().getValueAt(i, 1);
                    boolean selected = new Boolean(valueAt.toString());
                    if(selected) selectedList.add(i);
                }
                int result = 0;
                for(Integer i : selectedList) {
                    result += products.get(i+1).getCallories();
                }
                resultLabel.setText("<html><font size=\"+1\">Было выбрано " + selectedList.size() + " продуктов. Каллорийность выбранных продуктов: </font><font color=\"red\" size=\"+2\">" + result + "</font><font size=\"+1\"> Ккал.</font></html>");
            }
        });
        computePanel = new JPanel(new BorderLayout(200,20));
        computePanel.add(computeButton, BorderLayout.WEST);
        computePanel.add(resultLabel, BorderLayout.CENTER);
        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane3, BorderLayout.CENTER);
        panel.add(computePanel, BorderLayout.SOUTH);
        tabbedPane.add("Рассчитать каллорийность продуктов", panel);

        add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800,500));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    public static void makeMultilineLabel(JTextComponent area) {
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setEditable(false);
        area.setOpaque(false);
        if (area instanceof JTextArea) {
            ((JTextArea)area).setWrapStyleWord(true);
            ((JTextArea)area).setLineWrap(true);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Kenya frame = new Kenya();
    }
}
