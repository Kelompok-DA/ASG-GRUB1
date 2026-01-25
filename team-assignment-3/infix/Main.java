import java.util.Scanner;

public class Main {

    // interface untuk stack yang menyimpan karakter
    interface CharStack {
        void push(char c);

        char pop();

        char peek();

        boolean isEmpty();
    }

    // implementasi stack menggunakan array
    static class ArrayCharStack implements CharStack {
        private char[] data;
        private int top; // indeks elemen teratas stack

        ArrayCharStack(int capacity) {
            // inisialisasi array dengan kapasitas minimal 4
            data = new char[Math.max(4, capacity)];
            top = -1; // stack dimulai dalam keadaan kosong
        }

        @Override
        public void push(char c) {
            // jika array penuh, perbesar kapasitas menjadi 2 kali lipat
            if (top + 1 == data.length) {
                char[] nd = new char[data.length * 2];
                System.arraycopy(data, 0, nd, 0, data.length);
                data = nd;
            }
            // masukkan elemen ke posisi top yang baru
            data[++top] = c;
        }

        @Override
        public char pop() {
            // lempar exception jika stack kosong
            if (isEmpty())
                throw new IllegalStateException("Stack kosong");
            // ambil elemen teratas dan kurangi nilai top
            return data[top--];
        }

        @Override
        public char peek() {
            // lihat elemen teratas tanpa menghapusnya
            if (isEmpty())
                throw new IllegalStateException("Stack kosong");
            return data[top];
        }

        @Override
        public boolean isEmpty() {
            // stack kosong jika top bernilai negatif
            return top < 0;
        }
    }

    // stack khusus untuk menyimpan nilai bertipe double
    static class DoubleStack {
        private double[] data;
        private int top = -1;

        DoubleStack(int cap) {
            data = new double[cap];
        }

        void push(double v) {
            // tambahkan nilai ke stack
            data[++top] = v;
        }

        double pop() {
            // ambil dan hapus nilai teratas
            return data[top--];
        }
    }

    // enum untuk menandai tipe token dalam ekspresi
    enum TokenType {
        OPERAND, OPERATOR, LPAREN, RPAREN
    }

    // method untuk memeriksa apakah karakter adalah operator
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // validasi ekspresi infix untuk memastikan formatnya benar
    static boolean isValidInfix(String input, String stackImpl) {
        if (input == null)
            return false;
        String s = input.trim();
        if (s.isEmpty())
            return false;

        ArrayCharStack st = new ArrayCharStack(s.length());
        TokenType prev = null; // menyimpan tipe token sebelumnya
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            // lewati whitespace
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // proses token operand (angka)
            if (Character.isDigit(c)) {
                // operand tidak boleh langsung setelah operand atau kurung tutup
                if (prev == TokenType.OPERAND || prev == TokenType.RPAREN)
                    return false;
                // baca semua digit yang berurutan
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    i++;
                prev = TokenType.OPERAND;
                continue;
            }

            // proses kurung buka
            if (c == '(') {
                // kurung buka tidak boleh setelah operand atau kurung tutup
                if (prev == TokenType.OPERAND || prev == TokenType.RPAREN)
                    return false;
                st.push(c);
                prev = TokenType.LPAREN;
                i++;
                continue;
            }

            // proses kurung tutup
            if (c == ')') {
                // validasi keseimbangan kurung dan posisi yang tepat
                if (st.isEmpty() || prev == TokenType.OPERATOR || prev == TokenType.LPAREN)
                    return false;
                st.pop(); // hapus kurung buka yang sesuai
                prev = TokenType.RPAREN;
                i++;
                continue;
            }

            // proses operator
            if (isOperator(c)) {
                // operator tidak boleh di awal atau setelah operator/kurung buka
                if (prev == null || prev == TokenType.OPERATOR || prev == TokenType.LPAREN)
                    return false;
                prev = TokenType.OPERATOR;
                i++;
                continue;
            }
            // karakter tidak valid
            return false;
        }
        // ekspresi valid jika tidak berakhir dengan operator dan semua kurung seimbang
        return prev != TokenType.OPERATOR && st.isEmpty();
    }

    // menentukan prioritas operator untuk evaluasi ekspresi
    static int getOperatorPriority(char op) {
        if (op == '+' || op == '-')
            return 1; // prioritas terendah
        if (op == '*' || op == '/')
            return 2; // prioritas lebih tinggi
        return 0;
    }

    // konversi ekspresi infix ke notasi postfix
    static String toPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        ArrayCharStack stack = new ArrayCharStack(infix.length());

        for (char c : infix.toCharArray()) {
            // abaikan whitespace
            if (Character.isWhitespace(c))
                continue;

            // operand langsung ditambahkan ke output
            if (Character.isDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                // kurung buka masuk ke stack
                stack.push(c);
            } else if (c == ')') {
                // keluarkan semua operator sampai menemukan kurung buka
                while (!stack.isEmpty() && stack.peek() != '(')
                    output.append(stack.pop());
                stack.pop(); // buang kurung buka
            } else {
                // keluarkan operator dengan prioritas lebih tinggi atau sama
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        getOperatorPriority(stack.peek()) >= getOperatorPriority(c))
                    output.append(stack.pop());
                stack.push(c);
            }
        }

        // keluarkan semua operator yang tersisa di stack
        while (!stack.isEmpty())
            output.append(stack.pop());

        return output.toString();
    }

    // konversi ekspresi infix ke notasi prefix
    static String toPrefix(String infix) {
        // balik urutan string infix
        String rev = new StringBuilder(infix).reverse().toString();
        // tukar posisi kurung buka dan tutup
        rev = rev.replace('(', '#').replace(')', '(').replace('#', ')');
        // konversi ke postfix lalu balik hasilnya
        return new StringBuilder(toPostfix(rev)).reverse().toString();
    }

    // evaluasi ekspresi postfix dengan menampilkan langkah-langkahnya
    static double evaluatePostfixWithSteps(String postfix) {
        DoubleStack stack = new DoubleStack(postfix.length());

        System.out.println("\nProses evaluasi postfix:");
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                // push operand ke stack
                stack.push(c - '0');
                System.out.println("Push operand: " + (c - '0'));
            } else {
                // ambil dua operand untuk operasi
                double b = stack.pop(); // operand kedua
                double a = stack.pop(); // operand pertama
                double r = switch (c) {
                    case '+' -> a + b;
                    case '-' -> a - b;
                    case '*' -> a * b;
                    case '/' -> a / b;
                    default -> 0;
                };
                System.out.println(a + " " + c + " " + b + " = " + r);
                stack.push(r); // simpan hasil ke stack
            }
        }
        double result = stack.pop();
        System.out.println("Hasil akhir postfix: " + result);
        return result;
    }

    // evaluasi ekspresi prefix dengan menampilkan langkah-langkahnya
    static double evaluatePrefixWithSteps(String prefix) {
        DoubleStack stack = new DoubleStack(prefix.length());

        System.out.println("\nProses evaluasi prefix:");
        // baca ekspresi dari kanan ke kiri
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (Character.isDigit(c)) {
                // push operand ke stack
                stack.push(c - '0');
                System.out.println("Push operand: " + (c - '0'));
            } else {
                // ambil dua operand untuk operasi
                double a = stack.pop(); // operand pertama
                double b = stack.pop(); // operand kedua
                double r = switch (c) {
                    case '+' -> a + b;
                    case '-' -> a - b;
                    case '*' -> a * b;
                    case '/' -> a / b;
                    default -> 0;
                };
                System.out.println(a + " " + c + " " + b + " = " + r);
                stack.push(r); // simpan hasil ke stack
            }
        }
        double result = stack.pop();
        System.out.println("Hasil akhir prefix: " + result);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Masukkan notasi infix: ");
            String infix = sc.nextLine();

            // validasi ekspresi infix
            if (isValidInfix(infix, "array")) {
                System.out.println("✅ Infix VALID");

                // lakukan konversi ke postfix dan prefix
                String postfix = toPostfix(infix);
                String prefix = toPrefix(infix);

                System.out.println("Postfix : " + postfix);
                System.out.println("Prefix  : " + prefix);

                // evaluasi kedua notasi dengan menampilkan prosesnya
                evaluatePostfixWithSteps(postfix);
                evaluatePrefixWithSteps(prefix);
                break; // keluar dari loop setelah berhasil
            } else {
                // tampilkan pesan error dan contoh input
                System.out.println("❌ Infix TIDAK valid\n");
                System.out.println("Contoh valid: 5 + 4 / 5");
                System.out.println("Contoh tidak valid: 5 * 5 +");
            }
        }
        sc.close(); // tutup scanner
    }
}