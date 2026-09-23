

// ### Approach — 3 lines

// 1. Store every value's index in `inorder` using a `HashMap` for `O(1)` average lookup.
// 2. Process `postorder` from right to left; each value is the current root, and its `inorder` position divides left and right subtrees.
// 3. Build the **right subtree first**, then the left subtree, because postorder is being traversed backwards.

// **Time Complexity:** O(n)
// **Space Complexity:** O(n) — HashMap + recursion stack.
import java.util.HashMap;
import java.util.Map;

class BinaryTreeInPost {

    private int postorderIndex;
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        inorderMap = new HashMap<>();

        // Store each value's index in inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Start from the last element of postorder
        postorderIndex = postorder.length - 1;

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {

        if (left > right) {
            return null;
        }

        // Last element in postorder is the root
        int rootValue = postorder[postorderIndex--];

        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int rootIndex = inorderMap.get(rootValue);

        // Build RIGHT first because we are traversing
        // postorder from right to left
        root.right = build(postorder, rootIndex + 1, right);

        // Build LEFT subtree
        root.left = build(postorder, left, rootIndex - 1);

        return root;
    }
}

