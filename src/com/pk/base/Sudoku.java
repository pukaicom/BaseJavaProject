package com.pk.base;

import java.awt.*;
import java.awt.event.*;  
  
import javax.swing.*;  
import javax.swing.border.LineBorder;  
import javax.swing.border.TitledBorder;  
import javax.swing.plaf.basic.BasicArrowButton;  
import javax.swing.text.*;  
  
public class Sudoku extends JPanel  
{  
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
    private static final int width = 9;  
    private static final int height = 9;  
    private int data[][] = new int[height][width];  
    private String infoStr = "";  
  
    JButton solve = new JButton("计算"), reset = new JButton("重置"), clean = new JButton("清空");  
    BasicArrowButton right = new BasicArrowButton(BasicArrowButton.EAST), left = new BasicArrowButton(BasicArrowButton.WEST);  
    JTextField sudokus[][] = new JTextField[height][width];  
    JTextPane info = new JTextPane();  
  
    java.util.List<int[][]> solutions;  
    private int solutionIdx = 0;  
  
    Sudoku()  
    {  
        setLayout(null);  
        for (int r = 0; r < 3; ++r)  
        {  
            for (int c = 0; c < 3; ++c)  
            {  
                JPanel jp = new JPanel();  
                jp.setLayout(null);  
                jp.setBounds(r * 95 + 5, c * 95 + 5, 95, 95);  
                jp.setBorder(new LineBorder(Color.blue));  
                  
                for (int x = 0; x < 3; ++x)  
                    for (int y = 0; y < 3; ++y)  
                    {  
                        int j = r * 3 + x, i = c * 3 + y;  
                        sudokus[i][j] = new JTextField();  
                        sudokus[i][j].setBounds(30 * x + 5, 30 * y + 5, 25, 25);  
                        sudokus[i][j].setDocument(new NumberLenghtLimitedDmt(1));  
                        jp.add(sudokus[i][j]);  
                    }  
                add(jp);  
            }  
        }  
  
        solve.setBounds(20, 300, 75, 25);  
        solve.addActionListener(new SolveAL());  
        add(solve);  
  
        reset.setBounds(110, 300, 75, 25);  
        reset.addActionListener(new ResetAL());  
        add(reset);  
  
        clean.setBounds(200, 300, 75, 25);  
        clean.addActionListener(new cleanAL());  
        add(clean);  
  
        left.setVisible(false);  
        left.setBounds(170, 330, 50, 25);  
        left.addActionListener(new prevAL());  
        add(left);  
          
        right.setVisible(false);  
        right.setBounds(230, 330, 50, 25);  
        right.addActionListener(new nextAL());  
        add(right);  
  
/*        JScrollPane jsp = new JScrollPane(info);  
        info.setEditable(false);  
        jsp.setBounds(300, 5, 140, 285);  
        jsp.setBorder(new TitledBorder("Info"));  
        add(jsp);  */
    }  
  
    public boolean solve()  
    {  
        int i, j;  
        for (i = 0; i < height; ++i)  
        {  
            for (j = 0; j < width; ++j)  
            {  
                try  
                {  
                    data[i][j] = Integer.parseInt(sudokus[i][j].getText());  
                    sudokus[i][j].setEditable(false);  
                } catch (NumberFormatException e)  
                {  
                    data[i][j] = 0;  
                    sudokus[i][j].setEnabled(false);  
                }  
            }  
        }  
  
        int n = 9;  
        long startTime = System.currentTimeMillis();  
        DLX dlx = new DLX(n * n * n + 1, 4 * n * n);  
        dlx.setNum(5);  
        dlx.solve(data);  
        solutions = dlx.getSolutions();  
  
        if (solutions.size() > 1)  
            infoStr += "Find multi solutions.\n";  
        else if (solutions.size() > 0)  
            infoStr += "Find one solution.\n";  
        else  
            infoStr += "Cannot find a solution.\n";  
        infoStr += "Used time: " + (System.currentTimeMillis() - startTime) + " ms\n";  
  
        return update();  
    }  
  
    public void reset(int data[][])  
    {  
        solve.setEnabled(true);  
        int i, j;  
        for (i = 0; i < height; ++i)  
        {  
            for (j = 0; j < width; ++j)  
            {  
                sudokus[i][j].setEnabled(true);  
                sudokus[i][j].setEditable(true);  
                if (data[i][j] == 0)  
                {  
                    sudokus[i][j].setText("");  
                } else  
                {  
                    sudokus[i][j].setText(Integer.toString(data[i][j]));  
                }  
            }  
        }  
    }  
  
    boolean update()  
    {  
        info.setText(infoStr);  
        if (solutionIdx >= solutions.size())  
            return false;  
  
        left.setVisible(true);  
        right.setVisible(true);  
        int i, j;  
        int solution[][] = solutions.get(solutionIdx);  
        for (i = 0; i < height; ++i)  
        {  
            for (j = 0; j < width; ++j)  
            {  
                sudokus[i][j].setText(Integer.toString(solution[i][j]));  
            }  
        }  
        return true;  
    }  
  
    public static void inFrame(JPanel jp, int width, int height)  
    {  
        String title = "数独";  
        JFrame frame = new JFrame(title);  
        frame.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e)  
            {  
                System.exit(0);  
            }  
        });  
        frame.getContentPane().add(jp, BorderLayout.CENTER);  
        frame.setLocationByPlatform(true);  
        frame.setSize(width, height);  
        frame.setResizable(false);  
        frame.setVisible(true);  
    }  
  
    public static void main(String[] args)  
    {  
        int data[][] = {   
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },   
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },   
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },  
  
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },   
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },   
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },  
          
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },   
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },   
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 },   
            };  
        Sudoku sudoku = new Sudoku();  
        sudoku.reset(data);  
        inFrame(sudoku, 300, 400);  
    }  
  
    class SolveAL implements ActionListener  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            infoStr += "Start solve this sudoku.\n";  
            info.setText(infoStr);  
            solve.setEnabled(false);  
            solve();  
        }  
    }  
  
    class ResetAL implements ActionListener  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            infoStr += "Reset sudoku data.\n";  
            info.setText(infoStr);  
            left.setVisible(false);  
            right.setVisible(false);  
            reset(data);  
        }  
    }  
  
    class cleanAL implements ActionListener  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            infoStr = "Clean sudoku data.\n";  
            info.setText(infoStr);  
            solve.setEnabled(true);  
            left.setVisible(false);  
            right.setVisible(false);  
            int i, j;  
            for (i = 0; i < height; ++i)  
            {  
                for (j = 0; j < width; ++j)  
                {  
                    sudokus[i][j].setText("");  
                    sudokus[i][j].setEnabled(true);  
                    sudokus[i][j].setEditable(true);  
                }  
            }  
        }  
    }  
      
    class prevAL implements ActionListener  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            int size = solutions.size();  
            if(size > 0)  
            {  
                solutionIdx = (solutionIdx - 1 + size) % size;  
                infoStr += "The " + (solutionIdx+1) + "th solution.\n";  
                update();  
            }  
        }  
    }  
  
    class nextAL implements ActionListener  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            int size = solutions.size();  
            if(size > 0)  
            {  
                solutionIdx = (solutionIdx + 1) % size;  
                infoStr += "The " + (solutionIdx+1) + "th solution.\n";  
                update();  
            }  
        }  
    }  
  
    class NumberLenghtLimitedDmt extends PlainDocument  
    {  
        /** 
         *  
         */  
        private static final long serialVersionUID = 1L;  
        private int limit;  
  
        public NumberLenghtLimitedDmt(int limit)  
        {  
            super();  
            this.limit = limit;  
        }  
  
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException  
        {  
            if (str == null)  
            {  
                return;  
            }  
            if ((getLength() + str.length()) <= limit)  
            {  
                char[] upper = str.toCharArray();  
                int length = 0;  
                for (int i = 0; i < upper.length; i++)  
                {  
                    if (upper[i] >= '0' && upper[i] <= '9')  
                    {  
                        upper[length++] = upper[i];  
                    }  
                }  
                super.insertString(offset, new String(upper, 0, length), attr);  
            }  
        }  
    }  
  
} 