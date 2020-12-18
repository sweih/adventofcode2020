package de.beachboys.aoc2020;

// Aglo source: https://www.sanfoundry.com/java-program-shunting-yard-algorithm/

public class ShuntingYard
{
    /** enum **/
    private enum Precedence
    {
        lparen(0), rparen(1), plus(2), minus(3), divide(4), times(5), mod(6), eos(7), operand(8);

        private int index;
        Precedence(int index)
        {
            this.index = index;
        }
        public int getIndex()
        {
            return index;
        }
    }

    // Advent of Code: Changed precedence for +-/* !!!!!!  12->13, 13->12

    /** in stack precedence **/
    private static final int[] isp = {0, 19, 13, 13, 12, 12, 13, 0};
    /** incoming character precedence **/
    private static final int[] icp = {20, 19, 13, 13, 12, 12, 13, 0};
    /** operators **/
    private static final char[] operators = {'{', '}', '+', '-', '/', '*', '%', ' '};
    /** precedence stack **/
    private Precedence[] stack;
    /** stack top pointer **/
    private int top;

    /** pop element from stack **/
    private Precedence pop()
    {
        return stack[top--];
    }
    /** push element onto stack **/
    private void push(Precedence ele)
    {
        stack[++top] = ele;
    }
    /** get precedence token for symbol **/
    public Precedence getToken(char symbol)
    {
        switch (symbol)
        {
            case '('  : return Precedence.lparen;
            case ')'  : return Precedence.rparen;
            case '+'  : return Precedence.plus;
            case '-'  : return Precedence.minus;
            case '/'  : return Precedence.divide;
            case '*'  : return Precedence.times;
            case '%'  : return Precedence.mod;
            case ' '  : return Precedence.eos;
            default   : return Precedence.operand;
        }
    }

    /** Function to convert infix to postfix **/
    public String postfix(String infix)
    {
        String postfix = "";
        top = 0;
        stack = new Precedence[infix.length()];
        stack[0] = Precedence.eos;
        Precedence token;
        for (int i = 0; i < infix.length(); i++)
        {
            token = getToken(infix.charAt(i));
            /** if token is operand append to postfix **/
            if (token == Precedence.operand)
                postfix = postfix + infix.charAt(i);
            /** if token is right parenthesis pop till matching left parenthesis **/
            else if (token == Precedence.rparen)
            {
                while (stack[top] != Precedence.lparen)
                    postfix = postfix + operators[pop().getIndex()];
                /** discard left parenthesis **/
                pop();
            }
            /** else pop stack elements whose precedence is greater than that of token **/
            else
            {
                while (isp[stack[top].getIndex()] >= icp[token.getIndex()])
                    postfix = postfix + operators[pop().getIndex()];
                push(token);
            }
        }
        /** pop any remaining elements in stack **/
        while ((token = pop()) != Precedence.eos)
            postfix = postfix + operators[token.getIndex()];

        return postfix;
    }

}
