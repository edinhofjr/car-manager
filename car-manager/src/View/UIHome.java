package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Entities.Car;
import Window.DialogWindow;
import Window.Database;

import java.util.ArrayList;

public class UIHome implements UiDesign {
    private JPanel contentPane;
    private JPanel view;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel innerPanel;
    private JToolBar toolBar;

    private JFrame parent;

    private String title = "Janela";

    private Database conn = Database.getInstance();
    ArrayList<Car> cars;

    public UIHome() {
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
    }

    public JComponent getContent() {
        return contentPane;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    public JMenuBar getMenuBar() {
        JMenu insertMenu = new JMenu("Inserir");
        JMenuItem insertMake = new JMenuItem("Marca");
        JMenuItem insertCar = new JMenuItem("Carro");

        insertMake.addActionListener( e -> {
            DialogWindow dw = new DialogWindow(parent, new UIMake());
            }
        );

        insertMenu.add(insertMake);
        insertMenu.add(insertCar);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(insertMenu);
        return menuBar;
    }

    private void createUIComponents() {
        String[] columnNames = {"Modelo","Ano de Fabricação","Quilometragem Rodada", "Valor","Marca","Pais de origem"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        conn = Database.getInstance();
        ArrayList<Car> cars = conn.getCars();

        cars.forEach(c -> {
            Object[] rowData = {c.getModel(), c.getModelYear(), c.getDistance(), c.getPrice(), c.getMake().getName(), c.getMake().getCountry().getName(), c.getId()};
            model.addRow(rowData);
        });

        table = new JTable(model);
    }
}


