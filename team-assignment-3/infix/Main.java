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
            if (isEmpty()) throw new IllegalStateException("Stack kosong");
            return data[top--];
        }

        @Override
        public char peek() {
            if (isEmpty()) throw new IllegalStateException("Stack kosong");
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
            Node(char v, Node n) { val = v; next = n; }
        }
        private Node head;

        @Override
        public void push(char c) {
            head = new Node(c, head);
        }

        @Override
        public char pop() {
            if (isEmpty()) throw new IllegalStateException("Stack kosong");
            char v = head.val;
            head = head.next;
            return v;
        }

        @Override
        public char peek() {
            if (isEmpty()) throw new IllegalStateException("Stack kosong");
            return head.val;
        }

        @Override
        public boolean isEmpty() {
            return head == null;
        }
    }

    enum TokenType { OPERAND, OPERATOR, LPAREN, RPAREN }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static boolean isValidInfix(String input, String stackImpl) {
        if (input == null) return false;
        String s = input.trim();
        if (s.isEmpty()) return false;

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
                while (i < s.length() && Character.isDigit(s.charAt(i))) i++;
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
                if (st.isEmpty()) return false;
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

        if (prev == TokenType.OPERATOR || prev == TokenType.LPAREN) return false;
        return st.isEmpty();
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
