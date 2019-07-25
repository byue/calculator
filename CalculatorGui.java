import java.util.List;
import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ShuntingParser.ReversePolishEvaluator;
import ShuntingParser.ShuntingYard;

public class CalculatorGui {

    private static final int WIDTH = 750;
    private static final int HEIGHT = 1000;
    private static final int COLUMNS = 4;
    private static final String[] SYMBOLS = {"7", "8", "9", "/",
                                             "4", "5", "6", "*",
                                             "1", "2", "3", "-",
                                             "0", "(", ")", "+",
                                             ".","BSpace", "Clear", "%",
                                             "MC", "MR", "MS", "="};
    private JTextField enterTextField;
    private List<JButton> buttons;
    private String lastResult;
 
    public CalculatorGui() {
        this.lastResult = "";
        this.buttons = new ArrayList<JButton>();
        JFrame frame = new JFrame("Calculator");
        frame.setTitle("Calculator");
        frame.setSize(WIDTH, HEIGHT);
        JPanel buttonPanel = new JPanel(new GridLayout(SYMBOLS.length / COLUMNS, COLUMNS));        
        enterTextField = new JTextField();
        enterTextField.setPreferredSize(new Dimension(100, 100));
        Font inputFont = new Font("Serif", Font.BOLD,50);
        Font buttonFont = new Font("monospaced", Font.BOLD, 32);
        enterTextField.setFont(inputFont);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(enterTextField);
        frame.add(inputPanel, BorderLayout.NORTH);
        for (int i = 0; i < SYMBOLS.length; i++) {
				String symbol = SYMBOLS[i];
            JButton button = new JButton(symbol);
            button.setFont(buttonFont);
				if (button.getText().equals("Clear")) {
	             button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enterTextField.setText("");
                    }
                });
            } else if (button.getText().equals("BSpace")) {
 	             button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String prev = enterTextField.getText();
                        if (prev.length() > 0 && !prev.equals("ERROR")) {
                            prev = prev.substring(0, prev.length() - 1);
                            enterTextField.setText(prev);
                        }
                    }
                });
           } else if (button.getText().equals("MC")) {
	             button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CalculatorGui.this.lastResult = "";
                    }
                });
				} else if (button.getText().equals("MR")) {
	             button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enterTextField.setText(enterTextField.getText().concat(CalculatorGui.this.lastResult));
                    }
                });
            } else if (button.getText().equals("MS")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
								try {
								    double result = ReversePolishEvaluator.evaluate(ShuntingYard.getPostfix(enterTextField.getText()));
                            CalculatorGui.this.lastResult = Double.toString(result);
                        } catch (Exception ex) {}
                    }
                });	
				} else if (button.getText().equals("=")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
								try {
								    double result = ReversePolishEvaluator.evaluate(ShuntingYard.getPostfix(enterTextField.getText()));
                            enterTextField.setText(Double.toString(result));
                        } catch (Exception ex) {
                            enterTextField.setText("ERROR");
                        }
                    }
                });
				} else {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String currentInput = enterTextField.getText();
                        if (currentInput.equals("ERROR")) {
                            currentInput = "";
                        }
                        enterTextField.setText(currentInput.concat(button.getText()));
                    }
                });
            }
            buttonPanel.add(button);
            this.buttons.add(button);
        }
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorGui();
    }
}
