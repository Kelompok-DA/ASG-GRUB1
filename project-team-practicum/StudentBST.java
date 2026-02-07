public class StudentBST {

    NodeMahasiswa root;
    /**
     * Insert mahasiswa into BST
     * @param s
     */
    public void insert(Mahasiswa s) {
        root = insertRec(root, s);
    }

    /**
     * Helper method for insert
     * The ordering is based on IPK (higher IPK goes to the right).
     * If IPK is the same, order by NIM.
     * @param current
     * @param s
     * @return
     */
    private NodeMahasiswa insertRec(NodeMahasiswa current, Mahasiswa s) {

        if (current == null) {
            return new NodeMahasiswa(s); // create new node
        }

        // compare based on IPK, then NIM
        if (s.ipk < current.data.ipk ||
            (s.ipk == current.data.ipk &&
             s.nim.compareTo(current.data.nim) < 0)) {

            current.left = insertRec(current.left, s); // go left

        } else {

            current.right = insertRec(current.right, s); // go right
        }

        return current;
    }

    public void displayRanking() {
        reverseInorder(root);
    }

    /**
     * Helper method for displaying ranking in descending order
     * This is done via reverse inorder traversal.
     * @param node
     */
    private void reverseInorder(NodeMahasiswa node) {
        if (node == null) return;

        reverseInorder(node.right); // visit right subtree first

        System.out.println(
            node.data.nim + " | " +
            node.data.nama + " | IPK: " +
            node.data.ipk
        );

        reverseInorder(node.left); // then visit left subtree
    }
}