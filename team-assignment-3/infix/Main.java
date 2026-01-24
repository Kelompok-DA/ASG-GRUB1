import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    interface CharStack {
        void push(char c);

        char pop();

        char peek();

        boolean isEmpty();
    }

    static class ArrayCharStack implements CharStack {
        private char[] data;
        private int top;

        ArrayCharStack(int capacity) {
            data = new char[Math.max(4, capacity)];
            top = -1;
        }

        @Override
        public void push(char c) {
            if (top + 1 == data.length) {
                // grow
                char[] nd = new char[data.length * 2];
                System.arraycopy(data, 0, nd, 0, data.length);
                data = nd;
            }
            data[++top] = c;
        }

        @Override
        public char pop() {
            if (isEmpty())
                throw new IllegalStateException("Stack kosong");
            return data[top--];
        }

        @Override
        public char peek() {
            if (isEmpty())
                throw new IllegalStateException("Stack kosong");
            return data[top];
        }

        @Override
        public boolean isEmpty() {
            return top < 0;
        }
    }

    static class LinkedCharStack implements CharStack {
        static class Node {
            char val;
            Node next;

            Node(char v, Node n) {
                val = v;
                next = n;
            }
        }

        private Node head;

        @Override
        public void push(char c) {
            head = new Node(c, head);
        }

        @Override
        public char pop() {
            if (isEmpty())
                throw new IllegalStateException("Stack kosong");
            char v = head.val;
            head = head.next;
            return v;
        }

        @Override
        public char peek() {
            if (isEmpty())
                throw new IllegalStateException("Stack kosong");
            return head.val;
        }

        @Override
        public boolean isEmpty() {
            return head == null;
        }
    }

    enum TokenType {
        OPERAND, OPERATOR, LPAREN, RPAREN
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static boolean isValidInfix(String input, String stackImpl) {
        if (input == null)
            return false;
        String s = input.trim();
        if (s.isEmpty())
            return false;

        CharStack st = stackImpl.equalsIgnoreCase("linked")
                ? new LinkedCharStack()
                : new ArrayCharStack(s.length());

        TokenType prev = null;
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                if (prev == TokenType.OPERAND || prev == TokenType.RPAREN) {
                    return false;
                }
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    i++;
                prev = TokenType.OPERAND;
                continue;
            }

            if (c == '(') {
                if (prev == TokenType.OPERAND || prev == TokenType.RPAREN) {
                    return false;
                }
                st.push('(');
                prev = TokenType.LPAREN;
                i++;
                continue;
            }

            if (c == ')') {
                if (st.isEmpty())
                    return false;
                if (prev == null || prev == TokenType.OPERATOR || prev == TokenType.LPAREN) {
                    return false;
                }
                st.pop();
                prev = TokenType.RPAREN;
                i++;
                continue;
            }

            if (isOperator(c)) {
                if (prev == null || prev == TokenType.OPERATOR || prev == TokenType.LPAREN) {
                    return false;
                }
                prev = TokenType.OPERATOR;
                i++;
                continue;
            }

            return false;
        }

        if (prev == TokenType.OPERATOR || prev == TokenType.LPAREN)
            return false;
        return st.isEmpty();
    }

    /**
     * Method untuk mengembalikan prioritas operator
     * 
     * @param op
     * @return
     */
    static int getOperatorPriority(char op) {
        if (op == '+' || op == '-')
            return 1; // return 1 for + dan - karena memiliki prioritas lebih rendah

        if (op == '*' || op == '/')
            return 2; // return 2 for * dan / karena memiliki prioritas lebih tinggi

        return 0; // invalid operator

    }

/**
 * Fungsi konversi infix ke postfix
 * 
 * @param infix
 * @return
 */
static String toPostfix(String infix) {
    // buat output string
    StringBuilder output = new StringBuilder();
    // inisialisasi stack
    ArrayCharStack stack = new ArrayCharStack(infix.length());

    // loop tiap karakter
    for (int i = 0; i < infix.length(); i++) {
        char c = infix.charAt(i);
        
        // skip spasi
        if (Character.isWhitespace(c))
            continue;
            
        // kalau angka langsung append
        if (Character.isDigit(c)) {
            output.append(c);
            continue;
        }
        
        // kurung buka push ke stack
        if (c == '(') {
            stack.push(c);
            continue;
        }
        
        // kurung tutup pop sampai ketemu kurung buka
        if (c == ')') {
            while (!stack.isEmpty() && stack.peek() != '(')
                output.append(stack.pop());
            stack.pop(); // buang '('
            continue;
        }
        
        // operator: pop kalo prioritas di stack >= prioritas sekarang
        while (!stack.isEmpty() && stack.peek() != '('
                && getOperatorPriority(stack.peek()) >= getOperatorPriority(c))
            output.append(stack.pop());
        stack.push(c);
    }
    
    // kosongkan sisa stack
    while (!stack.isEmpty())
        output.append(stack.pop());
        
    return output.toString();
}

/**
 * Fungsi konversi infix ke prefix
 * 
 * @param infix
 * @return
 */
static String toPrefix(String infix) {
    // pecah jadi token dulu
    List<String> tokens = tokenize(infix);
    
    // list buat operand
    List<String> operands = new ArrayList<>();
    // list buat operator
    List<String> operators = new ArrayList<>();
    
    // memisahkan operand dan operator
    for (String token : tokens) {
        // kalau angka masuk operand
        if (token.matches("\\d+")) {
            operands.add(token);
        } 
        // kalau operator masuk operator
        else if (isOperator(token.charAt(0))) {
            operators.add(token);
        }
    }
    
    // rotasi: operand pertama pindah ke belakang
    if (operands.size() > 1) {
        operands.add(operands.remove(0));
    }
    
    // menggabungkan hasil
    StringBuilder result = new StringBuilder();
    // masukin operator dulu
    for (String op : operators) {
        result.append(op);
    }
    // baru operand
    for (String num : operands) {
        result.append(num);
    }
    
    return result.toString();
}

/**
 * Tokenize infix jadi list token
 */
static List<String> tokenize(String infix) {
    // list penampung token
    List<String> tokens = new ArrayList<>();
    int i = 0;
    
    // loop sampai habis
    while (i < infix.length()) {
        char c = infix.charAt(i);
        
        // skip spasi
        if (Character.isWhitespace(c)) {
            i++;
            continue;
        }
        
        // kalau digit, baca semua digitnya (supaya support multi-digit)
        if (Character.isDigit(c)) {
            StringBuilder num = new StringBuilder();
            while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                num.append(infix.charAt(i++));
            }
            tokens.add(num.toString());
            continue;
        }
        
        // selain digit (operator/kurung) tambahin langsung
        tokens.add(String.valueOf(c));
        i++;
    }
    
    return tokens;
}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String stackMode = "array";

        String infix;
        while (true) {
            System.out.print("Masukkan notasi infix: ");
            infix = sc.nextLine();

            if (isValidInfix(infix, stackMode)) {
                System.out.println("✅ Infix VALID: " + infix);
                // konversi ke postfix dan prefix
                String postfix = toPostfix(infix);
                String prefix = toPrefix(infix);

                System.out.println("Postfix: " + postfix);
                System.out.println("Prefixn: " + prefix);
                break;
            } else {
                System.out.println("❌ Infix TIDAK valid. Coba lagi.");
                System.out.println("Contoh valid: 5 + 4 / 5");
                System.out.println("Contoh tidak valid: 5 * 5 +");
            }
            System.out.println();
        }

        sc.close();
    }
}
