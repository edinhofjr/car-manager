package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import Entities.Country;
import Entities.Make;
import Window.Database;

public class UIMake implements UiDesign {
    private JPanel contentPane;
    private JTextField nameText;
    private JComboBox countryComboBox;
    private JButton confirmButton;
    private JLabel nameLabel;
    private JLabel countryLabel;

    private String title = "Inserir Marca";

    Database conn;
    ArrayList<Country> countries;

    public UIMake() {
        conn = Database.getInstance();
        refresh();
    }

    @Override
    public JComponent getContent() {
        return this.contentPane;
    }

    @Override
    public String getTitle() {
        return title;
    }

    Window parent;
    public void setParent(Window parent) {
        this.parent = parent;
    }

    private void refresh() {
        countries = conn.getCountries();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        countries.forEach( c -> {
            model.addElement(c.getName());
        });

        countryComboBox.setModel(model);
    }

    private void createUIComponents() {
        countryComboBox = new JComboBox();
        countryComboBox.setEditable(true);

        confirmButton = new JButton("Confirmar");

        confirmButton.addActionListener(e -> addMake());
    }

    private void addMake() {
        //Adiciona pais no banco de dados se não conter.
        String nameInput = nameText.getText();
        String countryInput = countryComboBox.getSelectedItem().toString();

        Country country = new Country(countryInput);

        // Verificar se o pais inserido no ComboBox está no banco de dados.
        Optional<Country> res = countries.stream().filter(c -> c.equals(country)).findFirst();

        if (!res.isPresent()) {
            conn.addCountry(country);
            refresh();
            addMake();
        } else {
            Make m = new Make(nameInput, res.get());
            conn.addMake(m);
        }
    }
}
