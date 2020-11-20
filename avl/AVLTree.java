package avl;

public class AVLTree extends BinarySearchTree{

    public String toTreeView(){
        if(this.isEmpty()) return "Esta arvore eh vazia!";
        return "\n" + root.subTreeString("");
    }

}