package com.pk.base;

import java.util.ArrayList;
import java.util.List;  
  
public class DLX  
{  
    private static final int ROW = 4096 + 50;  
    private static final int COL = 1024 + 50;  
    private static final int N = 4 * 9 * 9;  
    private static final int m = 3;  
  
    DLXNode row[] = new DLXNode[ROW];  
    DLXNode col[] = new DLXNode[COL];  
    DLXNode head;  
  
    private int n;  
    private int num = 2;  
    private int size[] = new int[COL];  
    int data[][] = new int[9][9];  
    List<int[][]> solutions;  
  
    public DLX(int r, int c)  
    {  
        n = m * m;  
        head = new DLXNode(r, c);  
        head.U = head.D = head.L = head.R = head;  
        for (int i = 0; i < c; ++i)  
        {  
            col[i] = new DLXNode(r, i);  
            col[i].L = head;  
            col[i].R = head.R;  
            col[i].L.R = col[i].R.L = col[i];  
            col[i].U = col[i].D = col[i];  
            size[i] = 0;  
        }  
  
        for (int i = r - 1; i > -1; --i)  
        {  
            row[i] = new DLXNode(i, c);  
            row[i].U = head;  
            row[i].D = head.D;  
            row[i].U.D = row[i].D.U = row[i];  
            row[i].L = row[i].R = row[i];  
        }  
    }  
  
    public void addNode(int r, int c)  
    {  
        DLXNode p = new DLXNode(r, c);  
        p.R = row[r];  
        p.L = row[r].L;  
        p.L.R = p.R.L = p;  
        p.U = col[c];  
        p.D = col[c].D;  
        p.U.D = p.D.U = p;  
        ++size[c];  
    }  
  
    public void addNode(int i, int j, int k)  
    {  
        int r = (i * n + j) * n + k;  
        addNode(r, i * n + k - 1);  
        addNode(r, n * n + j * n + k - 1);  
        addNode(r, 2 * n * n + block(i, j) * n + k - 1);  
        addNode(r, 3 * n * n + i * n + j);  
    }  
  
    int block(int x, int y)  
    {  
        return x / m * m + y / m;  
    }  
  
    public void cover(int c)  
    {  
        if (c == N)  
            return;  
  
        col[c].delLR();  
        DLXNode R, C;  
        for (C = col[c].D; C != col[c]; C = C.D)  
        {  
            if (C.c == N)  
                continue;  
            for (R = C.L; R != C; R = R.L)  
            {  
                if (R.c == N)  
                    continue;  
                --size[R.c];  
                R.delUD();  
            }  
            C.delLR();  
        }  
    }  
  
    public void resume(int c)  
    {  
        if (c == N)  
            return;  
  
        DLXNode R, C;  
        for (C = col[c].U; C != col[c]; C = C.U)  
        {  
            if (C.c == N)  
                continue;  
            C.resumeLR();  
            for (R = C.R; R != C; R = R.R)  
            {  
                if (R.c == N)  
                    continue;  
                ++size[R.c];  
                R.resumeUD();  
            }  
        }  
        col[c].resumeLR();  
    }  
  
    public boolean solve(int depth)  
    {  
        if (head.L == head)  
        {  
            int solution[][] = new int[n][n];  
            for (int i = 0; i < n; ++i)  
                for (int j = 0; j < n; ++j)  
                    solution[i][j] = data[i][j];  
            solutions.add(solution);  
  
            if (solutions.size() == num)  
                return true;  
            return false;  
        }  
        int minSize = 1 << 30;  
        int c = -1;  
        DLXNode p;  
        for (p = head.L; p != head; p = p.L)  
            if (size[p.c] < minSize)  
            {  
                minSize = size[p.c];  
                c = p.c;  
            }  
        cover(c);  
  
        for (p = col[c].D; p != col[c]; p = p.D)  
        {  
            DLXNode cell;  
            p.R.L = p;  
            for (cell = p.L; cell != p; cell = cell.L)  
            {  
                cover(cell.c);  
            }  
            p.R.L = p.L;  
            int rr = p.r - 1;  
            data[rr / (n * n)][rr / n % n] = rr % n + 1;  
            if (solve(depth + 1))  
                return true;  
  
            p.L.R = p;  
            for (cell = p.R; cell != p; cell = cell.R)  
                resume(cell.c);  
            p.L.R = p.R;  
        }  
  
        resume(c);  
        return false;  
    }  
  
    public boolean solve(int data[][])  
    {  
        init(data);  
        return solve(0);  
    }  
  
    public void init(int data[][])  
    {  
        solutions = new ArrayList<int[][]>();  
        int i, j, k;  
        for (i = 0; i < n; ++i)  
            for (j = 0; j < n; ++j)  
            {  
                if (data[i][j] > 0)  
                {  
                    addNode(i, j, data[i][j]);  
                } else  
                {  
                    for (k = 1; k <= n; ++k)  
                        addNode(i, j, k);  
                }  
            }  
    }  
  
    public void setNum(int num)  
    {  
        this.num = num;  
    }  
  
    public int getNum()  
    {  
        return num;  
    }  
  
    public List<int[][]> getSolutions()  
    {  
        return solutions;  
    }  
}  
  
class DLXNode  
{  
    int r,c;  
    DLXNode U,D,L,R;  
      
    DLXNode()  
    {  
        r = c = 0;  
    }  
      
    DLXNode(int r, int c)  
    {  
        this.r = r;  
        this.c = c;  
    }  
  
    DLXNode(int r, int c, DLXNode U, DLXNode D, DLXNode L, DLXNode R)  
    {  
        this.r = r;  
        this.c = c;  
        this.U = U;  
        this.D = D;  
        this.L = L;  
        this.R = R;  
    }  
  
    public void delLR()  
    {  
        L.R = R;  
        R.L = L;  
    }  
      
    public void delUD()  
    {  
        U.D = D;  
        D.U = U;  
    }  
      
    public void resumeLR()  
    {  
        L.R = R.L = this;  
    }  
      
    public void resumeUD()  
    {  
        U.D = D.U = this;  
    }  
}