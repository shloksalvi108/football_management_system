package com.fmrs.ui.awt;

import com.fmrs.app.SearchUseCase;
import com.fmrs.domain.Player;
import com.fmrs.search.SearchResult;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private final SearchUseCase searchUseCase;
    private final JTextField queryField;
    private final JTextArea resultArea;

    public MainFrame(SearchUseCase searchUseCase) {
        super("Football Management Retrieval System");
        this.searchUseCase = searchUseCase;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        queryField = new JTextField();
        JButton searchButton = new JButton("Search");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Enter query or player name:"), BorderLayout.WEST);
        topPanel.add(queryField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchButton.addActionListener(this::onSearch);
    }

    private void onSearch(ActionEvent e) {
        String query = queryField.getText();
        SearchResult result = searchUseCase.execute(query);
        renderResult(result);
    }

    private void renderResult(SearchResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(result.getMessage()).append("\n\n");
        for (Player p : result.getPlayers()) {
            sb.append("Name: ").append(p.getName()).append("\n");
            sb.append("Country: ").append(p.getCountry()).append("\n");
            sb.append("Club: ").append(p.getClub()).append("\n");
            sb.append("Total Goals: ").append(p.getTotalGoals()).append("\n");
            sb.append("-----------------------------\n");
        }
        resultArea.setText(sb.toString());
    }

    public static void launch(SearchUseCase searchUseCase) {
        SwingUtilities.invokeLater(() -> new MainFrame(searchUseCase).setVisible(true));
    }
}

